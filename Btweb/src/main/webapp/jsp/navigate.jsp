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
			window.location.href = getPath() + "/jsp/searchResult.jsp?searchValue="+encodeURI(encodeURI(searchValue));
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row align-items-center" style="margin-top:2.5%;">
			<div class="col-3 col-md-3 col-sm-4">
				<img src="${pageContext.request.contextPath}/img/logo.jpg" style="height:60px;"/>
			</div>
			<div class="col-6 col-md-7 col-sm-7 input-group">
			  	<input type="text" id="searchValueInput" style="width:85%;" placeholder="输入搜索内容" onkeypress="if(event.keyCode==13) {searchButton.click();return false;}"/>
				<div class="input-group-append">
			   		<button id="searchButton" class="btn btn-primary" type="button" onclick="redirectSearchResultJsp()">搜索</button>
			  	</div>
			</div>
			<div class="col-3 col-md-2 col-sm-3 text-right">
				<a href="#">注册</a>
				/
				<a href="#">登陆</a>
			</div>
		</div>
	</div>
	
	<div class="container-fluid" style=";margin-top:1%;height:40px;background-image:linear-gradient(to right, rgb(18, 120, 246), rgb(0, 180, 170))">
		<div class="container" style="height:100%;">
			<div class="row align-items-center" style="height:100%;">
				<a style="color:white;text-decoration:none;" href="javascript:window.location.href = getPath() + '/index.jsp'">首页</a>
				<a style="margin-left:2%;color:white;text-decoration:none;" href="javascript:window.location.href = getPath() + '/jsp/conditionMedia.jsp?mediaType=' + encodeURI(encodeURI('电影'))">电影</a>
				<a style="margin-left:2%;color:white;text-decoration:none;" href="javascript:window.location.href = getPath() + '/jsp/conditionMedia.jsp?mediaType=' + encodeURI(encodeURI('电视剧'))">电视剧</a>
				<a style="margin-left:2%;color:white;text-decoration:none;" href="javascript:window.location.href = getPath() + '/jsp/conditionMedia.jsp?mediaType=' + encodeURI(encodeURI('动漫'))">动漫</a>
			</div>
		</div>
	</div>
	<div id="navigateChoosMediaTypeDiv" align="center" style="font-size:20px;;">

	</div>
</body>
</html>