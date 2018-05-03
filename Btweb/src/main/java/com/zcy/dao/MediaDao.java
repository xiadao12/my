package com.zcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zcy.bean.Media;
import com.zcy.bean.MediaUrl;

public interface MediaDao {
	public void insertMedia(Media media);
	//根据媒体和上映日期类型随机取几条
	public List<Media> getMediaRandNumByMediaTypeYear(@Param("num")int num,@Param("mediaType")String mediaType);
	//根据条件搜索
	public List<Media> getMediaByCondition(@Param("pageStartNum")int pageStartNum,@Param("pageSize")int pageSize,@Param("mediaType")String mediaType,@Param("style")String style,@Param("releaseYear")int releaseYear,@Param("area")String area,@Param("language")String language);
	//根据条件查询获取所有的条数
	public int getMediaByConditionTotle(@Param("mediaType")String mediaType,@Param("style")String style,@Param("releaseYear")int releaseYear,@Param("area")String area,@Param("language")String language);
	//public List<Media> getMediaRandNumByMediaTypeYear();
	//根据id搜索media
	public Media getMediaById(@Param("mediaId")int mediaId);
	//根据id搜索mediaurl
	public List<MediaUrl> getMediaUrlById(@Param("mediaId")int mediaId);
	//模糊查询
	public List<Media> getMediaByFuzzy(@Param("searchValue")String searchValue,@Param("pageStartNum")int pageStartNum,@Param("pageSize")int pageSize);
	//模糊查询总数
	public int getMediaByFuzzyTotle(@Param("searchValue")String searchValue);
}