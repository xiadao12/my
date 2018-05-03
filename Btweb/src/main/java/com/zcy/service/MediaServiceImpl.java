package com.zcy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcy.bean.Media;
import com.zcy.bean.MediaUrl;
import com.zcy.dao.MediaDao;
import com.zcy.util.Constant;

@Service("mediaService")
public class MediaServiceImpl implements MediaService{

	@Resource(name="mediaDao")
	private MediaDao mediaDao;
	public void setMediaDao(MediaDao mediaDao) {
		this.mediaDao = mediaDao;
	}

	public void addMedia(Media media)
	{
		mediaDao.insertMedia(media);
	}

	@Transactional
	public Map<String,List<Media>> initMeida() {
		
		int searchTole = Constant.SHOWCOLNUM * Constant.SHOWROWNUM;
		
		List<Media> MovieList = mediaDao.getMediaRandNumByMediaTypeYear(searchTole, "电影");
		List<Media> TvList = mediaDao.getMediaRandNumByMediaTypeYear(searchTole, "电视剧");
		List<Media> AnimationList = mediaDao.getMediaRandNumByMediaTypeYear(searchTole, "动漫");
		
		Map<String,List<Media>> allMediaMap = new HashMap<String,List<Media>>();

		allMediaMap.put("movie", MovieList);
		allMediaMap.put("tv", TvList);
		allMediaMap.put("animation", AnimationList);
		return allMediaMap;
	}

	public Map getMediaByCondition(int pageNum,String mediaType, String style, int releaseYear, String area,
			String language) {
		
		List<Media> mediaData = mediaDao.getMediaByCondition((pageNum-1)*Constant.CONDITIONMEDIAPAGESIZE,Constant.CONDITIONMEDIAPAGESIZE ,mediaType, style, releaseYear, area, language);
		int totle = mediaDao.getMediaByConditionTotle(mediaType, style, releaseYear, area, language);
		Map mediaByConditionMap = new HashMap();
		mediaByConditionMap.put("pageCount", totle/Constant.CONDITIONMEDIAPAGESIZE+1);
		mediaByConditionMap.put("mediaData", mediaData);
		return mediaByConditionMap;
	}

	public Map getMeidaAndUrlByMediaId(int mediaId) {
		Media media = mediaDao.getMediaById(mediaId);
		List<MediaUrl> mediaUrlList =  mediaDao.getMediaUrlById(mediaId);
		Map map = new HashMap();
		map.put("media", media);
		map.put("mediaUrlList", mediaUrlList);
		return map;
	}

	public Map getMediaByFuzzy(String searchValue,int pageNum) {
		List<Media> mediaList = mediaDao.getMediaByFuzzy(searchValue,(pageNum-1)*Constant.FUZZYMEDIAPAGESIZE,Constant.FUZZYMEDIAPAGESIZE);
		int totle = mediaDao.getMediaByFuzzyTotle(searchValue);
		
		Map map = new HashMap();
		map.put("mediaList", mediaList);
		map.put("pageCount", totle/Constant.FUZZYMEDIAPAGESIZE+1);
		return map;
	}
}
