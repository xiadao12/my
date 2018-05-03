<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/showMediaTable.js"></script>
	
	<style type="text/css">
		.initMeiaTable{
			background-color:white;
			/* visibility:hidden; */
			border-spacing:10px 10px;
			border-collapse:separate;
		}
	</style>
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			<%-- 悬浮展示 --%>
			$("[data-toggle='tooltip']").tooltip();
			
			<%-- 点击切换影视类型时，设置对应的按键class --%>
	/* 			$("#changeMediaTypeUl").children("li").on("click",function(){
				$(this).addClass("active");
				$(this).siblings().removeClass("active");
			}); */
			
			<%-- 电影电视剧动漫各搜索十部 --%>
			$.ajax({
				type:"post",
				url:"initMeida.do",
				dataType:"json",
				success:function(data){
					//初始化数据
					initMedia(data);
				},
				error:function(data){
					//alert("获取数据错误");
				}
			});
		});
	
		<%-- 初始化电影电视动漫界面 --%>
		function initMedia(data)
		{
			var movieData = data.movie;
			var tvData = data.tv;
			var animationData = data.animation;
	
			setInitMediaTable("initMovieTable",movieData);
			setInitMediaTable("initTvTable",tvData);
			setInitMediaTable("initAnimationTable",animationData);
			
		}
	
		<%-- 具体的将初始化media的table赋值--%>
		function setInitMediaTable(id,mediatData)
		{
			if(mediatData != null)
			{
				var $initMediaTable = $("#"+id);
				showMediaTable($initMediaTable,mediatData);
			}
		}
	
	</script>

</head>
<body>
	<%-- 影视展示  --%>
	<div id="showMediaDiv" style="border:solid blue;background-color:rgb(241, 242, 243);" align="center">
		<table id="initMovieTable" class="initMeiaTable">
			<thead>
				<tr>
					<td colspan="5">电影资源</td>
				</tr>
			</thead>
		</table>
		<table id="initTvTable" class="initMeiaTable">
			<thead>
				<tr>
					<td colspan="5">电视剧资源</td>
				<tr>
			</thead>
		</table>
		<table id="initAnimationTable" class="initMeiaTable">
			<thead>
				<tr>
					<td colspan="5">动漫资源</td>
				<tr>
			</thead>
		</table>
	</div>
</body>
</html>