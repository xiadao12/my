function getDetailMediaInfoHtml(media,showHtml)
{
	var html = "<div style='height:250px;margin-top:1.5%;'>"
				+ "<span style='width:177px;height:250px;float:left;'>";
	if(showHtml == "searchResult")
	{
		html = html+"<a href='"+getPath()+"/jsp/mediaDetail.jsp?mediaId="+media.id+"'>";
	}
	html = html 	+ "<img style='width:100%;height:100%;' src='"+media.coverUrl+"'></img>";
	if(showHtml == "searchResult")
	{
		html = html + "</a>";
	}
	
	html = html	+ "</span>"
				+ "<span style='width:700px;height:250px;text-align:left;'>"
					+ "<div>";
					
	if(showHtml == "searchResult")
	{
		html = html +	"<a href='"+getPath()+"/jsp/mediaDetail.jsp?mediaId="+media.id+"' style='margin-left:10px;font-size:20px;'>"+media.name+"</a>";
	}
	else
	{
		html = html		+ "<label style='margin-left:10px;font-size:20px;'>"+media.name+"</label>";
	}
	
	html = html		+ "</div>"
					+ "<div>"
						+ "<label style='margin-left:10px;'>类型："+media.styles+"</label>"
					+ "</div>"
					+ "<div>"
						+ "<label style='margin-left:10px;'>地区："+media.area+"</label>"
					+ "</div>"
					+ "<div>"
						+ "<label style='margin-left:10px;'>语言："+media.language+"</dt>"
					+ "</div>"
					+ "<div>"
						+ "<label style='margin-left:10px;'>上映："+media.releaseYear+"</dt>"
					+ "</div>"
					+ "<div style='line-height:24px;white-space:nowrap;text-overflow:ellipsis;word-break:break-all;overflow:hidden;'>"
						+ "<label style='margin-left:10px;'>主演："+media.mainActors+"</dt>"
					+ "</div>"
					+ "<div>"
						+ "<label style='margin-left:10px;'>剧情：</dt>"
						+ "<div style='margin-left:20px;overflow-y:auto;overflow-x:auto;height:90px;'>"+media.story+"</div>"
					+ "</div>"
				+ "</span>"
			+ "</div>";
	
	return html;
}