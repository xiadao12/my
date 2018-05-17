<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/getDetailMediaInfoHtml.js"></script>

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
					<%-- 展示详细信息 --%>
					$("#detailMediaInfoDiv").prepend(getDetailMediaInfoHtml(data.media,"mediaDetail"));
					showMediaUrlDetail(data.mediaUrlList);
				}
			},
			error:function(){
				alert("获取数据失败");
			}
		
		});
		
	});

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
				
				html = html + "<div class='row'><div class='col-12'>"+resolution+"下载地址</div></div>";
				
				var urlList = mediaUrlList[i].urls.split(";");
				if(null != urlList)
				{
					for(j=0;j<urlList.length;j++)
					{
						var urlnameAndUrl = urlList[j];
						if(null != urlnameAndUrl)
						{
							html = html + "<div class='row'>"
											+"<div class='col-9'>";
							var nameurlList = urlnameAndUrl.split(",");
							if(nameurlList[0] != null)
							{
								html = html + nameurlList[0];
							}
							html = html 	+"</div>"
											+"<div class='col-3 text-right'>";
							
							if(nameurlList[1] != null)
							{
								html = html		+ "<a href='"+nameurlList[1]+"'>点击下载</a>"
												+ "<a href='javascript:void(0)' onclick='showUrlA(this)'>展示链接</a>";
							}
							html = html 	+"</div>"
										+ "</div>";
							
							if(nameurlList[1] != null)
							{
								html = html		+ "<div class='row showUrl' style='display:none;'><a class='col-12' href='"+nameurlList[1]+"'>"+nameurlList[1]+"</a></div>";
							}
										
						}
					}
				}
			}
		}
		$("#showUrlDiv").append(html);
	}
	
	<%-- 展示url的超链接 --%>
	function showUrlA(obj)
	{
		$(obj).parents().find("div.showUrl").css("display","none");
		$(obj).parent().parent().next("div.showUrl:first").css("display","inline");
	}
	

</script>

</head>
<body>
	<jsp:include page="/jsp/navigate.jsp" flush="true"/>
	<div id="detailMediaInfoDiv" class="container"></div>
	<div id="showUrlDiv" class="container"></div>
</body>
</html>