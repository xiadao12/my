<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	$(function(){
		//获取传进来的电影id
		var mediaId = <%=request.getParameter("mediaId")%>;
		
		$.ajax({
			url:"${pageContext.request.contextPath}/searchMediaById.do",
			type:"post",
			dataType:"json",
			data:{
				"mediaId":mediaId
			},
			success:function(data){
				//alert(data);
				if(data != null)
				{
					showMediaDetail(data.media);
					showMediaUrlDetail(data.mediaUrlList);
				}
			},
			error:function(){
				alert("获取数据失败");
			}
		
		});
		
	});

	<%-- 展示详细信息 --%>
	function showMediaDetail(media)
	{
		$("#mediaCoverImg").attr("src",media.coverUrl);
		$("#MediaNameLabel").append(media.name);
		$("#styleLabel").append(media.styles);
		$("#areaLabel").append(media.area);
		$("#releaseYearLabel").append(media.releaseYear);
		$("#languageLabel").append(media.language);
		$("#mainActorLabel").append(media.mainActors);
		$("#storyDiv").append(media.story);
	}

	<%-- 展示下载路径 --%>
	function showMediaUrlDetail(mediaUrlList)
	{
		
		var html = "";
		for(i=0;i<mediaUrlList.length;i++)
		{
			if(null != mediaUrlList[i])
			{
				<%--获取清晰度--%>
				var resolution = mediaUrlList[i].resolution;
				
				html = html + "<div>"+resolution+"下载地址</div>"
				
				var urlList = mediaUrlList[i].urls.split(";");
				if(null != urlList)
				{
					for(j=0;j<urlList.length;j++)
					{
						html = html + "<div>"
						var urlnameAndUrl = urlList[j];
						if(null != urlnameAndUrl)
						{
							var nameurlList = urlnameAndUrl.split(",");
							if(nameurlList[0] != null)
							{
								html = html + "<div><label style='margin-left:40px;'>"+nameurlList[0]+"</label></div>";
							}
							
							if(nameurlList[1] != null)
							{
								html = html + "<div><a style='margin-left:40px;' href='"+nameurlList[1]+"'>"+nameurlList[1]+"</a></div>";
							}
							html = html + "</div>";
						}
					}
				}
			}
		}
		$("#showUrlDiv").append(html);
	}
	

</script>

</head>
<body>
	<jsp:include page="/jsp/navigate.jsp" flush="true"/>
	<div align="center">
		<div style="width:60%;">
			<table>
				<tr>
					<td style="width:233px;height:340px;">
						<img id="mediaCoverImg" style="width:100%;height:100%;"></img>
					</td>
					<td style="height:340px;width:500px;">
						<div>
							<label id="MediaNameLabel"></label>
						</div>
						<div>
							<label>类型：</label>
							<label id="styleLabel"></label>
						</div>
						<div>
							<label>地区：</label>
							<label id="areaLabel"></label>
						</div>
						<div>
							<label>语言：</dt>
							<label id="languageLabel"></label>
						</div>
						<div>
							<label>上映时间：</dt>
							<label id="releaseYearLabel"></label>
						</div>
						<div>
							<label>主演：</dt>
							<label id="mainActorLabel"></label>
						</div>
						<div style="">
							<label>剧情：</dt>
							<div id="storyDiv" style="overflow-y:auto;overflow-x:auto;height:60px;"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<br/><br/>
						<div id="showUrlDiv" align="left"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>