package com.zcy.service;

import java.util.List;
import java.util.Map;

import com.zcy.bean.Media;

public interface MediaService {
	//添加视频
	public void addMedia(Media media);
	//首页初始化视频
	public Map<String, List<Media>> initMeida(); 
	//根据条件获取
	public Map getMediaByCondition(int pageNum,String mediaType,String style,int releaseYear,String area,String language);
	//根据id获取meidia和url
	public Map getMeidaAndUrlByMediaId(int mediaId);
	//模糊查询
	public Map getMediaByFuzzy(String searchValue,int pageNum);
	
}
