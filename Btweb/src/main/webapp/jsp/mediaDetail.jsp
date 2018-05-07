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
					$("#detailMediaInfoDiv").append(getDetailMediaInfoHtml(data.media,"mediaDetail"));
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
				
				html = html + "<div style='font-size:20px;margin-top:3%;'>"+resolution+"下载地址</div>"
				
				var urlList = mediaUrlList[i].urls.split(";");
				if(null != urlList)
				{
					for(j=0;j<urlList.length;j++)
					{
						html = html + "<div style='margin-top:0.4%;border-bottom:1px solid #CFCFCF;'>"
						var urlnameAndUrl = urlList[j];
						if(null != urlnameAndUrl)
						{
							var nameurlList = urlnameAndUrl.split(",");
							if(nameurlList[0] != null)
							{
								html = html + "<label style='margin-left:40px;'>"+nameurlList[0]+"</label>";
							}
							
							if(nameurlList[1] != null)
							{
								html = html + "<a style='margin-left:40px;float:right;' href='"+nameurlList[1]+"'>点击下载</a>"
											+ "<a style='margin-left:40px;float:right;' href='javascript:void(0)' onclick='showUrlA(this)'>展示链接</a>"
											+ "<a href='"+nameurlList[1]+"' class='showUrl' style='margin-left:40px;display:none;'>"+nameurlList[1]+"</a>";
							}
							html = html + "</div>";
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
		$(obj).parents().find("a.showUrl").css("display","none");
		$(obj).siblings("a.showUrl").css("display","inline");
	}
	

</script>

</head>
<body>
	<jsp:include page="/jsp/navigate.jsp" flush="true"/>
	<div align="center">
		<div style="width:885px;">
			<div id="detailMediaInfoDiv"></div>
			<div id="showUrlDiv" align="left" style="margin-top:5%;"></div>
		</div>
	</div>
</body>
</html>