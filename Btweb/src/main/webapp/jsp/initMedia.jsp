<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/showMediaDiv.js"></script>
	
	<style type="text/css">
		.initMediaTypeDiv{
			font-size:20px;
			font-family:Microsoft YaHei;
			margin-top:2.5%;
			margin-bottom:1%;
		}
	</style>
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			<%-- 悬浮展示 --%>
			/* $("[data-toggle='tooltip']").tooltip(); */
			
			<%-- 电影电视剧动漫各搜索十部 --%>
			$.ajax({
				type:"post",
				url:"initMeida.do",
				dataType:"json",
				success:function(data){
					if(data != null)
					{
						//初始化数据
						initMedia(data);
					}
				},
				error:function(data){
					alert("获取数据错误");
				}
			});
		});
	
		<%-- 初始化电影电视动漫界面 --%>
		function initMedia(data)
		{
			var movieData = data.movie;
			var tvData = data.tv;
			var animationData = data.animation;
	
			if(movieData != null)
			{
				setInitMediaDiv("initMovieDiv",movieData);
			}
			if(tvData != null)
			{
				setInitMediaDiv("initTvDiv",tvData);
			}
			if(animationData != null)
			{
				setInitMediaDiv("initAnimationDiv",animationData);
			}
			
			//先将展示界面隐藏，然后再展示
			$("#showMediaDiv").css("display","inline");
		}
	
		<%-- 具体的将初始化media的table赋值--%>
		function setInitMediaDiv(id,mediatData)
		{
			if(mediatData != null)
			{
				var $initMediaDiv = $("#"+id);
				showMediaDiv($initMediaDiv,mediatData);
			}
		}
	
	</script>

</head>
<body>
	<div id="showMediaDiv" style="display:none">
		<%-- 影视展示  --%>
		<div class="container">
			<div class="row initMediaTypeDiv">电影资源</div>
			<div id="initMovieDiv" class="row"></div>
		</div>
		<div class="container">
			<div class="row initMediaTypeDiv">电视剧资源</div>
			<div id="initTvDiv" class="row"></div>
		</div>
		<div class="container">
			<div class="row initMediaTypeDiv">动漫资源</div>
			<div id="initAnimationDiv" class="row"></div>
		</div>
	</div>
</body>
</html>