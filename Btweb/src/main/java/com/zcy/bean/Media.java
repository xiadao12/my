package com.zcy.bean;

import java.sql.Timestamp;

/**
 * 影视bean
 * @author Chuan
 *
 */
public class Media {
	
	private Integer id;
	private String mediaType;
	private String name;
	private String coverUrl;
	private Integer realeaseYear;
	private String styles;
	private String area;
	private String language;
	private String mainActors;
	private String story;
	private Timestamp updateDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public Integer getRealeaseYear() {
		return realeaseYear;
	}
	public void setRealeaseYear(Integer realeaseYear) {
		this.realeaseYear = realeaseYear;
	}
	public String getStyles() {
		return styles;
	}
	public void setStyles(String styles) {
		this.styles = styles;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getMainActors() {
		return mainActors;
	}
	public void setMainActors(String mainActors) {
		this.mainActors = mainActors;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
}
