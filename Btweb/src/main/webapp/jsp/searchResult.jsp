<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- 分页插件样式 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug/paging/css/paging.css"> 
<%-- 分页插件 --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug/paging/js/paging.js"></script>
<%-- 获取详细信息的html --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getDetailMediaInfoHtml.js"></script>
<script type="text/javascript">
	var searchValue = "";
	$(function(){
		searchValue = "<%=request.getParameter("searchValue")%>";
		$("#searchValueInput").val(searchValue);
		setSearchFuzzyMediaAndTurnPage(1,true);
	});
	
	function setSearchFuzzyMediaAndTurnPage(pageNum,ifReloadPage)
	{
		$("#fuzzyMediaTable").empty();
		$.ajax({
			url:"${pageContext.request.contextPath}/searchByFuzzy.do",
			type:"post",
			dataType:"json",
			data:{
				"searchValue":searchValue,
				"pageNum":pageNum
			},
			success:function(data){
				if(data != null)
				{
					setSearchFuzzyMedia(data.mediaList);
					if(ifReloadPage)
					{
						<%-- 分页 --%>
						$("#page").paging({
							totalPage: data.pageCount,
							callback: function(num) {
								setSearchFuzzyMediaAndTurnPage(num,false);
							}
						})
					}
				}
			},
			error:function(){
				alert("获取数据失败");
			}
		});
	}
	
	function setSearchFuzzyMedia(mediaList)
	{
		var html = "";
		if(mediaList != null)
		{
			for(i=0;i<mediaList.length;i++)
			{
				var media = mediaList[i];
				if(media != null)
				{
					html = html + getDetailMediaInfoHtml(media,"searchResult");
				}
			}
		}
		$("#fuzzyMediaTable").append(html);
	}
</script>
</head>
<body>
	<jsp:include page="/jsp/navigate.jsp" flush="true"/>
	<div align="center">
		<div style="width:55%;">
			<table id="fuzzyMediaTable"></table>
			<div id="page" class="page_div"></div>
		</div>
	</div>
</body>
</html>