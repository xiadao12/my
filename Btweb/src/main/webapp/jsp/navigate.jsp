<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function redirectSearchResultJsp()
	{
		var searchValue = $.trim($("#searchValueInput").val());
		if(searchValue != "")
		{
			window.location.href = getPath() + "/jsp/searchResult.jsp?searchValue="+searchValue;
		}
	}
</script>
</head>
<body>
	<div style="background-image:linear-gradient(to right, rgb(18, 120, 246), rgb(0, 180, 170));">
		<%--  网站图标以及搜索框   --%>
		<div align="center" style="width:100%;height:6%;">
			<div style="margin-top:0.6%;">
				<span><label style="font-size:20px;">影视库</label></span>
				<%-- <img src="${pageContext.request.contextPath}/img/logo.jpg" style="width:4%;"/> --%>
				<span style="display:inline-block;width:40%;">
						<input type="text" id="searchValueInput" style="width:80%;" onkeypress="if(event.keyCode==13) {searchButton.click();return false;}"/>
						<input type="button" id="searchButton" value="搜索" onclick="redirectSearchResultJsp()"/>
				</span>
			</div>						
		</div>
		
		<div align="center" style="font-size:20px;">
			<a href="${pageContext.request.contextPath}/index.jsp" style="margin-left:2%;color:white;">首页</a>
			<a href="${pageContext.request.contextPath}/jsp/conditionMedia.jsp?mediaType=电影" style="margin-left:5%;color:white;">电影</a>
			<a href="${pageContext.request.contextPath}/jsp/conditionMedia.jsp?mediaType=电视剧" style="margin-left:5%;color:white;">电视剧</a>
			<a href="${pageContext.request.contextPath}/jsp/conditionMedia.jsp?mediaType=动漫" style="margin-left:5%;color:white;">动漫</a>
		</div>
	</div>
</body>
</html>