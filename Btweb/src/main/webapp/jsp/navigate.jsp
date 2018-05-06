<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
	.navigateMeiaType{
		/* bakcground-color:rgba(255, 255, 255, 0.24); */
		background-color:rgba(255, 255, 255, 0.24);
		border-radius:7px;
	}
</style>
<script type="text/javascript">
	<%-- 移动鼠标切换影视类型时，设置对应的按键class --%>
	$(function(){
		$("#navigateChoosMediaTypeDiv > span").on("mouseover",function(){
			$(this).addClass("navigateMeiaType");
		}).on("mouseout",function(){
			$(this).removeClass("navigateMeiaType");
		});
	});

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
	<div>
		<%--  网站图标以及搜索框   --%>
		<div align="center" style="width:100%;height:10%;">
			<span style="display:inline-block">
				<label style="font-size:35px;">影视库</label>
			</span>
			<span style="margin-top:0.5%;display:inline-block;width:40%;">
					<input type="text" id="searchValueInput" style="width:60%;height:40%;" onkeypress="if(event.keyCode==13) {searchButton.click();return false;}"/>
					<input type="button" id="searchButton" style="height:40%;" value="搜索" onclick="redirectSearchResultJsp()"/>
			</span>
		</div>
		
		<div id="navigateChoosMediaTypeDiv" align="center" style="font-size:20px;height:5%;background-image:linear-gradient(to right, rgb(18, 120, 246), rgb(0, 180, 170));">
			<span style="margin-top:0.5%;width:5%;display:inline-block">
				<a href="javascript:window.location.href = getPath() + '/index.jsp'" style="color:white;text-decoration:none;">首页</a>
			</span>
			<span style="width:5%;display:inline-block;margin-left:2%;">
				<a href="javascript:window.location.href = getPath() + '/jsp/conditionMedia.jsp?mediaType=' + encodeURI(encodeURI('电影'))" style="color:white;text-decoration:none;">电影</a>
			</span>
			<span style="width:5%;display:inline-block;margin-left:2%;">
				<a href="javascript:window.location.href = getPath() + '/jsp/conditionMedia.jsp?mediaType=' + encodeURI(encodeURI('电视剧'))" style="color:white;text-decoration:none;">电视剧</a>
			</span>
			<span style="width:5%;display:inline-block;margin-left:2%;">
				<a href="javascript:window.location.href = getPath() + '/jsp/conditionMedia.jsp?mediaType=' + encodeURI(encodeURI('动漫'))" style="color:white;text-decoration:none;">动漫</a>
			</span>
		</div>
	</div>
</body>
</html>