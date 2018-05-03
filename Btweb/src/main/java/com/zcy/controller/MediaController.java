package com.zcy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcy.bean.Media;
import com.zcy.service.MediaService;
import com.zcy.util.Constant;
import com.zcy.util.Util;

@Controller
public class MediaController {
	
/*	@Resource(name="mediaServiceImpl")
	private MediaServiceImpl mediaServiceImpl;
	public void setMediaServiceImpl(MediaServiceImpl mediaServiceImpl) {
		this.mediaServiceImpl = mediaServiceImpl;
	}*/
	
	@Resource(name="mediaService")
	private MediaService mediaService;
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	//初始化数据
	@RequestMapping("/initMeida.do")
	public @ResponseBody String initMeida()
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String returnString = "";
		try {
			returnString = objectMapper.writeValueAsString(mediaService.initMeida());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return returnString;
	}
	
	@RequestMapping("/searchConditionMedia.do")
	public @ResponseBody String searchConditionMedia(String mediaTypeName, String style, String realeaseYearString, String area,
			String language,String pageNumString)
	{
		int mediaType = 0;
		if(Util.judgeNotNull(mediaTypeName))
		{
			if("电影".equals(mediaTypeName))
			{
				mediaType = Constant.MEIDATYPE_MOVIE;
			}
			else if("电视剧".equals(mediaTypeName))
			{
				mediaType = Constant.MEIDATYPE_TV;
			}else if("动漫".equals(mediaTypeName))
			{
				mediaType = Constant.MEIDATYPE_ANIMATION;
			}
				
		}

		int realeaseYear = 0;
		if(Util.judgeNotNull(realeaseYearString))
		{
			realeaseYear = Integer.parseInt(realeaseYearString);
		}
		
		int pageNum = 1;
		if(Util.judgeNotNull(pageNumString))
		{
			pageNum = Integer.parseInt(pageNumString);
		}
		
		Map mediaDataMap = mediaService.getMediaByCondition(pageNum, mediaType, style, realeaseYear, area, language);
		ObjectMapper objectMapper = new ObjectMapper();
		String returnString = "";
		try {
			returnString = objectMapper.writeValueAsString(mediaDataMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	//根据id来搜索视频
	@RequestMapping("/searchMediaById.do")
	public @ResponseBody String searchMediaById(int mediaId)
	{
		Map mediaDataMap = mediaService.getMeidaAndUrlByMediaId(mediaId);
		ObjectMapper objectMapper = new ObjectMapper();
		String returnString = "";
		try {
			returnString = objectMapper.writeValueAsString(mediaDataMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	//模糊查询
	@RequestMapping("/获取数据失败.do")
	public void searchByFuzzy(String searchValue)
	{
		System.out.println("搜索:" + searchValue);
	}
	
}
