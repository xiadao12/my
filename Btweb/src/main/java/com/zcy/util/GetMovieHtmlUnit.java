package com.zcy.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zcy.service.MediaService;


public class GetMovieHtmlUnit {
	
	@Resource(name="movieService")
	private MediaService movieService;
	public MediaService getMovieService() {
		return movieService;
	}
	public void setMovieService(MediaService movieService) {
		this.movieService = movieService;
	}

	@Test
	public void tt()
	{
		/*
		Movie movie = new Movie();
		movie.setName("第一步电影");
		movie.setArea("欧美");
		movie.setInsertDate(new Date());
		movie.setLanguage("英语");
		movie.setMainActors("json,xihua");
		movie.setPhotoUrl("http://ddlld.com");
		movie.setReleaseYear(1992);
		movie.setStory("故事很长，刺客出胜利");
		movie.setTypes("戏剧");
		
		movieService.addMovie(movie);
		*/
	}
	
	//日志
	Logger log = Logger.getLogger("");
	
	/**
	 * 需要获取的内容：
	 * 
	 * 电影名
	 * 照片
	 * 上映时间
	 * 类型
	 * 地区
	 * 语言
	 * 主演
	 * 剧情介绍
	 * 名称+磁力链接
	 */
	public void getMovie()
	{
		WebClient webClient = new WebClient(BrowserVersion.CHROME);//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象

        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
		
		//起止页设置
		int startMoviePage = 3;
		int endMoviePage = 5;
		
		//循环获取网页数据
		for(int i=startMoviePage;i<=endMoviePage;i++)
		{
			
			String urlString = "http://www.btbtdy.com/btdy/dy"+i+".html";
			try {
				
				HtmlPage page = webClient.getPage(urlString);
				
				Document doc = Jsoup.parse(page.asXml());
				
				//如果找不到下载链接直接跳过
				if(doc.selectFirst("#nucms_downlist")==null)
				{
					continue;
				}
				
				Element yearElement = doc.selectFirst(".year");
				
				//获取电影年份
				String movieYear = yearElement.text();
				if(judgeNotNull(movieYear))
				{
					movieYear = movieYear.trim();
					movieYear = movieYear.substring(1, movieYear.length()-1).trim();
				}
				else
				{
					movieYear = "";
				}
				
				//获取电影名
				String movieName = yearElement.parent().text();
				if(judgeNotNull(movieName))
				{
					movieName = movieName.split("\\(")[0].trim();
				}
				else
				{
					movieName = "";
				}
				
				//获取封面地址
				Element coverElement = doc.selectFirst(".vod_img").select("img").first();
				String movieCoverUrlString = coverElement.attr("src");
				if(judgeIsNull(movieCoverUrlString))
				{
					movieCoverUrlString = "";
				}
				
				
				//基础信息节点
				Element baseInfoElement = doc.selectFirst(".vod_intro")
						.select("dl").first();
				
				//获取电影类型
				HashSet<String> movieTypeSet = new HashSet<String>();
				Elements typeElements = baseInfoElement.select("dd").get(2).select("a");
				for(Element typeElement : typeElements)
				{
					String typeTextTemp = typeElement.text();
					if(judgeNotNull(typeTextTemp) && !"电影".equals(typeTextTemp))
					{
						movieTypeSet.add(typeTextTemp);
					}
				}
				
				//获取地区
				Element areaElement = baseInfoElement.select("dd").get(3).selectFirst("a");
				String movieArea = areaElement.text();
				if(judgeIsNull(movieArea)) {
					movieArea = "";
				}
				
				//获取语言
				Element languageElement = baseInfoElement.select("dd").get(4).selectFirst("a");
				String movieLanguage = languageElement.text();
				if(judgeIsNull(movieLanguage)) {
					movieLanguage = "";
				}
				
				//获取主演
				HashSet<String> movieMainActorNameSet = new HashSet<String>();
				Elements movieMainActorElements = baseInfoElement.select("dd").get(6).select("a");
				for(Element movieMainActorElement : movieMainActorElements)
				{
					String movieMainActorNameTemp = movieMainActorElement.text();
					if(judgeNotNull(movieMainActorNameTemp))
					{
						movieMainActorNameSet.add(movieMainActorNameTemp);
					}
				}
				
				//获取剧情介绍
				Element movieStoryElement = doc.selectFirst(".c05").children().get(2);
				String movieStory = movieStoryElement.text();
				if(judgeIsNull(movieStory))
				{
					movieStory = "";
				}
				
				//获取链接
				Element movieUrlHashDownlistElement = doc.selectFirst("#nucms_downlist");
				if(movieUrlHashDownlistElement == null)
				{
					break;
				}
				
				Elements movieUrlHashDivElements = movieUrlHashDownlistElement.select("div");
				if(movieUrlHashDivElements == null)
				{
					break;
				}
				
				Elements movieUrlHashUlElements = doc.select(".p_list_02");
				
				//清晰度和相应链接的键值对
				HashMap<String,HashMap<String,String>> resolutionUrlMap = new HashMap<String,HashMap<String,String>>();
				
				for(Element movieUrlHashUlElement : movieUrlHashUlElements)
				{
					//获取清晰度
					String movieResolutionString = movieUrlHashUlElement.firstElementSibling().text();
					
					if(judgeIsNull(movieResolutionString))
					{
						movieResolutionString = "";
					}
					else
					{
						//清晰度
						movieResolutionString = movieResolutionString.split("下载")[0].toString();
						
						//链接名称和连接的键值对
						HashMap<String,String> nameUrlMap = new HashMap<String,String>();
						
						//获取li节点
						Elements movieUrlHashLiElements = movieUrlHashUlElement.select("li");
						for(Element movieUrlHashLiElement : movieUrlHashLiElements)
						{
							Elements movieUrlHashLiAElements = movieUrlHashLiElement.select("a");
							
							//获取连接名称
							String urlNameTemp = movieUrlHashLiAElements.get(0).text().split("详情")[0].trim();
							String urlStringTemp = movieUrlHashLiAElements.get(1).attr("href");
							nameUrlMap.put(urlNameTemp, urlStringTemp);
						}
						
						resolutionUrlMap.put(movieResolutionString,nameUrlMap);
						
					}
				}
				

				System.out.println("电影名称:" + movieName);
				System.out.println("电影年份:" + movieYear);
				System.out.println("封面路径:" + movieCoverUrlString);
				System.out.println("类型:" + movieTypeSet);
				System.out.println("地区:" + movieArea);
				System.out.println("语言:" + movieLanguage);
				System.out.println("主演:" + movieMainActorNameSet);
				System.out.println("剧情:" + movieStory);
				System.out.println("链接:" + resolutionUrlMap);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		webClient.close();
		
	}
	
	
	//判断非空
	public Boolean judgeNotNull(Object obj)
	{
		//判断字符串是否为空
		if(obj instanceof String)
		{
			if(obj == null || "".equals(obj))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return false;
	}
	
	//判断是空
	public Boolean judgeIsNull(Object obj)
	{
		//判断字符串是否为空
		if(obj instanceof String)
		{
			if(obj == null || "".equals(((String) obj).trim()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
}