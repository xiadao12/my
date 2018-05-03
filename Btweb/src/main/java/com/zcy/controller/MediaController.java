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
	public @ResponseBody String searchConditionMedia(String mediaType, String style, String releaseYearString, String area,
			String language,String pageNumString)
	{
		int releaseYear = 0;
		if(Util.judgeNotNull(releaseYearString))
		{
			releaseYear = Integer.parseInt(releaseYearString);
		}
		
		int pageNum = 1;
		if(Util.judgeNotNull(pageNumString))
		{
			pageNum = Integer.parseInt(pageNumString);
		}
		
		Map mediaDataMap = mediaService.getMediaByCondition(pageNum, mediaType, style, releaseYear, area, language);
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
	@RequestMapping("/searchByFuzzy.do")
	public @ResponseBody String searchByFuzzy(String searchValue,int pageNum)
	{
		Map mediaDataMap = mediaService.getMediaByFuzzy(searchValue,pageNum);
		ObjectMapper objectMapper = new ObjectMapper();
		String returnString = "";
		try {
			returnString = objectMapper.writeValueAsString(mediaDataMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
}
