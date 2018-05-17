function getDetailMediaInfoHtml(media,showHtml)
{
	var html = 	"<div class='row' style='margin-top:2%;'>";
	
	if(showHtml == "searchResult")
	{
		html = html+"<a href='"+getPath()+"/jsp/mediaDetail.jsp?mediaId="+media.id+"'>";
	}
	
	html = html				+"<div class='col-lg-2 col-md-3'>"
								+"<img style='width:177px;height:250px;' src='"+media.coverUrl+"'></img>"
							+"</div>";
	
	if(showHtml == "searchResult")
	{
		html = html + "</a>";
	}			
					
							
	html = html				+"<div class='col-lg-9 col-md-9' style='margin-left:1.8%;'>"
								+ "<div>";
	if(showHtml == "searchResult")
	{
		html = html			+"<a href='"+getPath()+"/jsp/mediaDetail.jsp?mediaId="+media.id+"' style='font-size:20px;text-decoration:none;'>";
	}
							
	html = html							+ media.name;
	if(showHtml == "searchResult")
	{
		html = html 		+ "</a>";
	}									
	html = html					+ "</div>"
								+ "<div>"
									+ "<label>类型："+media.styles+"</label>"
								+ "</div>"
								+ "<div>"
									+ "<label>地区："+media.area+"</label>"
								+ "</div>"
								+ "<div>"
									+ "<label>语言："+media.language+"</label>"
								+ "</div>"
								+ "<div>"
									+ "<label>上映："+media.releaseYear+"</label>"
								+ "</div>"
								+ "<div>"
									+ "<label>主演："+media.mainActors+"</label>"
								+ "</div>"
							+"</div>"
						+"</div>";
	
	if(showHtml == "mediaDetail")
	{
		html = html +"<div class='row'><div class='col-12'>剧情介绍：</div></div>"
					+"<div class='row'>"
						+"<div class='col-12'>"+media.story+"</div>"
					+"</div>";
	}
					
	return html;
}