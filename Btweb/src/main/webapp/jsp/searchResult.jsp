<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/setTurnPage.js"></script>
<script type="text/javascript">
	var searchValue = "";
	$(function(){
		searchValue = "<%=request.getParameter("searchValue")%>";
		$("#searchValueInput").val(searchValue);
		setSearchFuzzyMediaAndTurnPage(1,true);
	});
	
	function setSearchFuzzyMediaAndTurnPage(pageNum,isResetTurnPage)
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
					setSearchFuzzyTurnPage(isResetTurnPage,data.pageCount);
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
					html = html 
					+ "<tr>"
						+"<td><a href='"+getPath()+"/jsp/mediaDetail.jsp?mediaId="+media.id+"'><img src="+media.coverUrl+"></img></a><td>"
						+"<td>"
							+"<div>名称：<a href='"+getPath()+"/jsp/mediaDetail.jsp?mediaId="+media.id+"'>"+media.name+"</a></div>"
							+"<div>上映时间："+media.releaseYear+"</div>"
							+"<div>地区："+media.area+"</div>"
							+"<div>类型："+media.styles+"</div>"
							+"<div>剧情："+media.story+"</div>"
						+"</td>";
					+"</tr>";
				}
			}
		}
		$("#fuzzyMediaTable").append(html);
	}
	
	function setSearchFuzzyTurnPage(isResetTurnPage,pageCount)
	{
		setTurnPage(isResetTurnPage,$("#fuzzyMediaTurnPageUl"),pageCount,setSearchFuzzyMediaAndTurnPage);
	}
	
</script>
</head>
<body>
	<jsp:include page="/jsp/navigate.jsp" flush="true"/>
	<div>
		<table id="fuzzyMediaTable"></table>
		<ul class="pagination" id="fuzzyMediaTurnPageUl">
		</ul>
	</div>
</body>
</html>