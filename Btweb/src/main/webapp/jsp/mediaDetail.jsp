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
								html = html + "<div>"+nameurlList[0]+"</div>";
							}
							
							if(nameurlList[1] != null)
							{
								html = html + "<div><a href='"+nameurlList[1]+"'>"+nameurlList[1]+"</a></div>";
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
	<div>
		<div>
			<span style="float:left">
				<img id="mediaCoverImg" style="width:233px;height:340px;"></img>
			</span>
			<span style="width:30%;">
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
				</div><div>
					<label>上映时间：</dt>
					<label id="releaseYearLabel"></label>
				</div>
				<div>
					<label>主演：</dt>
					<label id="mainActorLabel"></label>
				</div>
				<div>
					<label>剧情：</dt>
					<div id="storyDiv" Style="overflow-y:auto; overflow-x:auto;height:20%;width:500px;"></div>
				</div>
			</span>
		</div>
		<div id="showUrlDiv">
			
		</div>
	</div>
</body>
</html>