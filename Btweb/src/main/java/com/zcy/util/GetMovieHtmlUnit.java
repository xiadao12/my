package com.zcy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class GetMovieHtmlUnit {
	
	//日志
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
	 * @throws InterruptedException 
	 * 
	 */
	
	public static void main(String[] args)
	{
		getMovie();
	}
	
	//@Test
	public static void getMovie()
	{
		WebClient webClient = new WebClient(BrowserVersion.CHROME);//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象

        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        //webClient.getOptions().setUseInsecureSSL(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
		
		//起止页设置
		int startMoviePage = 12152;
		int endMoviePage = 12941;//12941
		
		//循环获取网页数据
		for(int i=startMoviePage;i<=endMoviePage;i++)
		{
			try {
				
				System.out.println("进入循环");
				String urlString = "http://www.btbtdy.com/btdy/dy"+i+".html";
				System.out.println("查找网页：" + urlString);
			
				
				HtmlPage page = webClient.getPage(urlString);
				
				Document doc = Jsoup.parse(page.asXml());
				
				//如果找不到下载链接直接跳过
				if(doc.selectFirst("#nucms_downlist")==null)
				{
					//判断是不是因为影片被删除
					String bodyText = doc.body().html();
					
					//1 影片被删除，2版权方要求被删除（http://www.btbtdy.com/btdy/dy6536.html）
					if(bodyText.indexOf("此影片不存") >= 0 || bodyText.indexOf("已删除本片") >= 0)
					{
						System.out.println("此影片被删除，跳过");
						continue;
					}
					else
					{
						System.out.println("****************未找到页面停止运行****************");
						break;
					}
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
				String movieCoverUrlString = coverElement.attr("src").trim();
				if(judgeIsNull(movieCoverUrlString))
				{
					movieCoverUrlString = "";
				}
				
				
				//基础信息节点
				Element baseInfoElement = doc.selectFirst(".vod_intro")
						.select("dl").first();
				
				//获取电影类型
				//HashSet<String> movieTypeSet = new HashSet<String>();
				//电影类型
				int movieType = 0;
				String movieTypeString = "";
				Elements typeElements = baseInfoElement.select("dd").get(2).select("a");
				for(Element typeElement : typeElements)
				{
					String typeTextTemp = typeElement.text().trim();
					if(judgeNotNull(typeTextTemp))
					{
						if("电影".equals(typeTextTemp))
						{
							movieType = 1;
						}
						else if("电视剧".equals(typeTextTemp))
						{
							movieType = 2;
						}
						else if("动漫".equals(typeTextTemp))
						{
							movieType = 3;
						}
						else
						{
							//movieTypeSet.add(typeTextTemp);
							if(judgeIsNull(movieTypeString))
							{
								movieTypeString = typeTextTemp;
							}
							else
							{
								movieTypeString = movieTypeString + "," + typeTextTemp;
							}
							
						}
					}
				}
				
				//获取地区
				Element areaElement = baseInfoElement.select("dd").get(3).selectFirst("a");
				String movieArea = areaElement.text().trim();
				if(judgeIsNull(movieArea)) {
					movieArea = "";
				}
				
				//获取语言
				Element languageElement = baseInfoElement.select("dd").get(4).selectFirst("a");
				String movieLanguage = languageElement.text().trim();
				if(judgeIsNull(movieLanguage)) {
					movieLanguage = "";
				}
				
				//获取主演
				//HashSet<String> movieMainActorNameSet = new HashSet<String>();
				String movieMainActorNameString = "";
				try
				{
					Elements movieMainActorElements = baseInfoElement.select("dd").get(6).select("a");
					for(Element movieMainActorElement : movieMainActorElements)
					{
						String movieMainActorNameTemp = movieMainActorElement.text().trim();
						if(judgeNotNull(movieMainActorNameTemp))
						{
							//movieMainActorNameSet.add(movieMainActorNameTemp);
							if(judgeIsNull(movieMainActorNameString))
							{
								movieMainActorNameString = movieMainActorNameTemp;
							}
							else
							{
								movieMainActorNameString = movieMainActorNameString + "," + movieMainActorNameTemp;
							}
						}
					}
				}catch(Exception e)
				{
					System.out.println("获取演员失败，继续。。。。。。");
				}
				
				//获取剧情介绍
				Element movieStoryElement = doc.selectFirst(".c05");
				String movieStory = movieStoryElement.text().trim();
				if(judgeIsNull(movieStory))
				{
					movieStory = "";
				}
				else
				{
					if(movieStory.indexOf("剧情介绍：") >= 0)
					{
						String[] movieStoryList = movieStory.split("剧情介绍：");
						
						if(movieStoryList.length >= 2)
						{
							movieStory = movieStoryList[1].trim();
						}
						else
						{
							movieStory = "";
						}
						
					}
						
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
					String movieResolutionString = movieUrlHashUlElement.firstElementSibling().text().trim();
					
					if(judgeIsNull(movieResolutionString))
					{
						movieResolutionString = "";
					}
					else
					{
						//清晰度
						movieResolutionString = movieResolutionString.split("下载")[0].toString();
						
						//如果是网盘下载，直接略过
						if(movieResolutionString.indexOf("网盘")>=0)
						{
							continue;
						}
						
						//链接名称和连接的键值对
						HashMap<String,String> nameUrlMap = new HashMap<String,String>();
						
						//获取li节点
						Elements movieUrlHashLiElements = movieUrlHashUlElement.select("li");
						for(Element movieUrlHashLiElement : movieUrlHashLiElements)
						{
							Elements movieUrlHashLiAElements = movieUrlHashLiElement.select("a");
							
							//获取连接名称
							String urlNameTemp = "";
							String urlNameAText = movieUrlHashLiAElements.get(0).text().trim();
							//有可能存在链接名为空的情况，此种情况下urlNameAText只有“详情”两个字
							if(Util.judgeIsNull(urlNameAText) || "详情".equals(urlNameAText))
							{
								urlNameTemp = movieUrlHashLiAElements.get(0).attr("title").trim();
							}else
							{
								urlNameTemp = urlNameAText.split("详情")[0].trim();
							}
							
							String urlStringTemp = movieUrlHashLiAElements.get(1).attr("href").trim();
							nameUrlMap.put(urlNameTemp, urlStringTemp);
						}
						
						resolutionUrlMap.put(movieResolutionString,nameUrlMap);
						
					}
				}
				

				System.out.println("电影名称:" + movieName);
				System.out.println("电影年份:" + movieYear);
				System.out.println("封面路径:" + movieCoverUrlString);
				System.out.println("类型:" + movieTypeString);
				System.out.println("地区:" + movieArea);
				System.out.println("语言:" + movieLanguage);
				System.out.println("主演:" + movieMainActorNameString);
				System.out.println("剧情:" + movieStory);
				System.out.println("链接:" + resolutionUrlMap);
				
/*				if(1 == 1)
				{
					return;
				}*/
				
				//链接并插入数据库
				System.out.println("开始插入数据库");
				//链接并插入数据库
				
				String mysqlDriver = "com.mysql.cj.jdbc.Driver";
				String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/db_btweb?characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai";
				String mysqlUser = "root";
				String mysqlPassword = "qweqwe";
				
				Class.forName(mysqlDriver);
				Connection conn = DriverManager.getConnection(mysqlUrl,mysqlUser,mysqlPassword);
				String mediaSql = "insert into t_media (mediatype,name,coverurl,releaseyear,styles,area,language,mainactors,story) values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(mediaSql,Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, movieType);
				pstmt.setString(2, movieName);
				pstmt.setString(3, movieCoverUrlString);
				pstmt.setInt(4, Integer.parseInt(movieYear));
				
				pstmt.setString(5, movieTypeString);
				pstmt.setString(6, movieArea);
				pstmt.setString(7, movieLanguage);
				pstmt.setString(8, movieMainActorNameString);
				pstmt.setString(9, movieStory);
				pstmt.execute();
				
				ResultSet rs = pstmt.getGeneratedKeys();  
				int mediaId = -1;
				if(rs !=null && rs.next())
				{
					mediaId = rs.getInt(1);
				}
				System.out.println("movieId = " + mediaId);
				
				
				String mediaSql2 = "";
				//PreparedStatement pstmt2 = conn.prepareStatement(mediaSql2);
				//Statement pstmt2 = conn.createStatement();
				PreparedStatement pstmt2 = null;
				
				Iterator resolutionUrlMapIter = resolutionUrlMap.keySet().iterator();
				while(resolutionUrlMapIter.hasNext())
				{
					String resolutionKey = (String) resolutionUrlMapIter.next();
					HashMap urlValHashMap = resolutionUrlMap.get(resolutionKey);
					
					String urlStrings = "";
					Iterator urlValHashMapIter = urlValHashMap.keySet().iterator();
					{
						while(urlValHashMapIter.hasNext())
						{
							String nameKey = (String) urlValHashMapIter.next();
							String urlStringTemp = (String) urlValHashMap.get(nameKey);
							
							String nameAndUrlString  = nameKey + "," + urlStringTemp;
							
							if(judgeIsNull(urlStrings))
							{
								urlStrings = nameAndUrlString;
							}
							else
							{
								urlStrings = urlStrings + ";" + nameAndUrlString;
							}
						}
					}
					
					//mediaSql2 = "insert into t_media_url (mediaid,resolution,urls) values('"+mediaId+"','"+resolutionKey+"','"+urlStrings+"')";
					mediaSql2 = "insert into t_media_url (mediaid,resolution,urls) values(?,?,?)";
					
					//String mediaSql = "insert into t_media (mediatype,name,coverurl,realeaseyear,styles,area,language,mainactors,story) values(?,?,?,?,?,?,?,?,?)";
					pstmt2 = conn.prepareStatement(mediaSql2);
					pstmt2.setInt(1, mediaId);
					pstmt2.setString(2, resolutionKey);
					pstmt2.setString(3, urlStrings);
					
					pstmt2.addBatch();
				}
				
				if(pstmt2 != null)
				{
					pstmt2.executeBatch();
					pstmt2.close();
				}
				//HashMap<String,HashMap<String,String>> resolutionUrlMap
				
				
				pstmt.close();
				
				
				System.out.println("插入数据库成功");
				
				//睡眠大约10-12秒
				System.out.println("延迟中............");
				try {
					Thread.sleep((long) (Math.random()*2000 + 10000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if(i>=endMoviePage)
				{
					System.out.println("全部插入完毕");
				}
				else
				{
					System.out.println("继续下一个..........");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("****************出现异常停止运行****************");
				break;
			}
		}
		
		webClient.close();
		
	}
	
	//判断非空
	public static Boolean judgeNotNull(Object obj)
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
	public static Boolean judgeIsNull(Object obj)
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