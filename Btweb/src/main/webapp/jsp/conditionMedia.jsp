<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/showMediaTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/setTurnPage.js"></script>

<style type="text/css">
	.conditionLabelMouseover{
		background-color:rgb(18, 120, 246);
		color:#FFF;
	}
	.conditionLabelClick{
		background-color:rgb(18, 120, 246);
		color:#FFF;
	}
</style>
<script type="text/javascript">
	$(function(){
		
		<%-- 设置搜索条件鼠标放上和点击的风格 --%>
		$("#selectConditonDl label").on("mouseover",function(){
			$(this).addClass("conditionLabelMouseover");
		}).on("mouseout",function(){
			$(this).removeClass("conditionLabelMouseover");
		}).on("click",function(){
			$(this).addClass("conditionLabelClick");
			$(this).siblings().removeClass("conditionLabelClick");
			
			<%-- 设置已选的搜索条件 --%>
			var ddTitle = $(this).parent("dd").attr("title");
			var choosedValue = $(this).text();
			if(choosedValue == "全部")
			{
				choosedValue = "";
			}
			<%-- 设置已选的搜索条件 --%>
			setConditionSpan(ddTitle,choosedValue);
			<%--展示条件数据和分页栏--%>
			setConditionMediaTableAndPage(1,true);
		});
		
		//获取传参内容，然后展示对应的视频类型
		var reqMediaType = "<%=request.getParameter("mediaType")%>";
		if(reqMediaType != null)
		{
			if(reqMediaType == "电影")
			{
				$("#mediaTypeMovieLabel").click();
			}else if(reqMediaType == "电视剧")
			{
				$("#mediaTypeTvLabel").click();
			}else if(reqMediaType == "动漫")
			{
				$("#mediaTypeAnimationLabel").click();
			}
		}
	});
	
	<%-- 设置已选的搜索条件 --%>
	function setConditionSpan(ddTitle,choosedValue)
	{
		if(ddTitle == "mediaTypeTitle")
		{
			$("#mediaTypeSpan").text(choosedValue);
		}else if(ddTitle == "styleTitle")
		{
			$("#styleSpan").text(choosedValue);
		}else if(ddTitle == "realeaseYearTitle")
		{
			$("#realeaseYearSpan").text(choosedValue);
		}else if(ddTitle == "areaTitle")
		{
			$("#areaSpan").text(choosedValue);
		}else if(ddTitle == "languageTitle")
		{
			$("#languageSpan").text(choosedValue);
		}
	}
	
	<%--展示条件数据和分页栏--%>
	function setConditionMediaTableAndPage(pageNum,isResetTurnPage)
	{
		$conditionMediaTable = $("#conditionMediaTable");
		<%-- 清除之前的数据 --%>
		$conditionMediaTable.empty();
		<%-- 根据条件搜索 --%>
		searchByCondition($conditionMediaTable,pageNum,isResetTurnPage);
	}
	
	<%-- 根据条件搜索 --%>
	function searchByCondition($conditionMediaTable,pageNum,isResetTurnPage){
		var mediaTypeName = $("#mediaTypeSpan").text();
		var style = $("#styleSpan").text();
		var realeaseYearString = $("#realeaseYearSpan").text();
		var area = $("#areaSpan").text();
		var language = $("#languageSpan").text();
		
		$.ajax({
			type:"post",
			url:'${pageContext.request.contextPath}/searchConditionMedia.do',
	        dataType:"json",
			data:{
				"mediaTypeName":mediaTypeName,
				"style":style,
				"realeaseYearString":realeaseYearString,
				"area":area,
				"language":language,
				"pageNumString":pageNum
			},
			success:function(data){
				if(data != null)
				{
					<%--根据查询结果展示--%>
					showMediaTable($conditionMediaTable,data.mediaData);
					<%--根据总数设置翻页--%>
					setTurnPage(isResetTurnPage,$("#conditionMediaTurnPageUl"),data.pageCount,setConditionMediaTableAndPage);
				}
			},
			error:function(){
				alert("获取数据失败");
			}
		});
	}
</script>
</head>
<body>
	<jsp:include page="/jsp/navigate.jsp" flush="true"/>
	<dl id="selectConditonDl">
		<dt style="float:left;">类型：</dt>
		<dd title="mediaTypeTitle">
			<label style="cursor:pointer;" class="conditionLabelClick">全部</label>
			<label style="cursor:pointer;" id="mediaTypeMovieLabel">电影</label>
			<label style="cursor:pointer;" id="mediaTypeTvLabel">电视剧</label>
			<label style="cursor:pointer;" id="mediaTypeAnimationLabel">动漫</label>
		</dd>
		<dt style="float:left;">分类：</dt>
		<dd title="styleTitle">
			<label style="cursor:pointer;" class="conditionLabelClick">全部</label>
			<label style="cursor:pointer;">动作</label>
			<label style="cursor:pointer;">科幻</label>
			<label style="cursor:pointer;">喜剧</label>
			<label style="cursor:pointer;">爱情</label>
			<label style="cursor:pointer;">剧情</label>
			<label style="cursor:pointer;">奇幻</label>
			<label style="cursor:pointer;">动画</label>
			<label style="cursor:pointer;">惊悚</label>
			<label style="cursor:pointer;">恐怖</label>
			<label style="cursor:pointer;">悬疑</label>
			<label style="cursor:pointer;">犯罪</label>
			<label style="cursor:pointer;">战争</label>
			<label style="cursor:pointer;">冒险</label>
			<label style="cursor:pointer;">西部</label>
			<label style="cursor:pointer;">灾难</label>
			<label style="cursor:pointer;">武侠</label>
			<label style="cursor:pointer;">古装</label>
			<label style="cursor:pointer;">谍战</label>
			<label style="cursor:pointer;">传记</label>
			<label style="cursor:pointer;">历史</label>
			<label style="cursor:pointer;">纪录</label>
			<label style="cursor:pointer;">同性</label>
			<label style="cursor:pointer;">音乐</label>
			<label style="cursor:pointer;">歌舞</label>
			<label style="cursor:pointer;">青春</label>
			<label style="cursor:pointer;">家庭</label>
			<label style="cursor:pointer;">儿童</label>
			<label style="cursor:pointer;">校园</label>
			<label style="cursor:pointer;">励志</label>
			<label style="cursor:pointer;">运动</label>
			<label style="cursor:pointer;">体育</label>
			<label style="cursor:pointer;">短片</label>
			<label style="cursor:pointer;">真人秀</label>
			<label style="cursor:pointer;">黑色电影</label>
			<label style="cursor:pointer;">脱口秀</label>
		</dd>
		<dt style="float:left;">年代：</dt>
		<dd title="realeaseYearTitle">
			<label style="cursor:pointer;" class="conditionLabelClick">全部</label>
			<label style="cursor:pointer;">2018</label>
			<label style="cursor:pointer;">2017</label>
			<label style="cursor:pointer;">2016</label>
			<label style="cursor:pointer;">2015</label>
			<label style="cursor:pointer;">2014</label>
			<label style="cursor:pointer;">2013</label>
			<label style="cursor:pointer;">2012</label>
			<label style="cursor:pointer;">2011</label>
			<label style="cursor:pointer;">2010</label>
		</dd>
		<dt style="float:left;">地区：</dt>
		<dd title="areaTitle">
			<label style="cursor:pointer;" class="conditionLabelClick">全部</label>
			<label style="cursor:pointer;">大陆</label>
			<label style="cursor:pointer;">香港</label>
			<label style="cursor:pointer;">台湾</label>
			<label style="cursor:pointer;">日本</label>
			<label style="cursor:pointer;">韩国</label>
			<label style="cursor:pointer;">欧美</label>
			<label style="cursor:pointer;">泰国</label>
			<label style="cursor:pointer;">印度</label>
		</dd>
		<dt style="float:left;">语言：</dt>
		<dd title="languageTitle">
			<label style="cursor:pointer;" class="conditionLabelClick">全部</label>
			<label style="cursor:pointer;">国语</label>
			<label style="cursor:pointer;">粤语</label>
			<label style="cursor:pointer;">英语</label>
			<label style="cursor:pointer;">日语</label>
			<label style="cursor:pointer;">韩语</label>
			<label style="cursor:pointer;">泰语</label>
			<label style="cursor:pointer;">法语</label>
			<label style="cursor:pointer;">德语</label>
			<label style="cursor:pointer;">俄语</label>
			<label style="cursor:pointer;">丹麦语</label>
			<label style="cursor:pointer;">印地语</label>
			<label style="cursor:pointer;">西班牙语</label>
			<label style="cursor:pointer;">葡萄牙语</label>
			<label style="cursor:pointer;">意大利语</label>
			<label style="cursor:pointer;">无对白</label>
		</dd>
		<dt style="float:left;">已选：</dt>
		<dd id="choosedConditionDd">
			<%-- 电影类型 --%>
			<span id="mediaTypeSpan" style="float:left;margin-left:20px"></span>
			<%-- 电影分类--%>
			<span id="styleSpan" style="float:left;margin-left:20px"></span>
			<%-- 电影年代 --%>
			<span id="realeaseYearSpan" style="float:left;margin-left:20px"></span>
			<%-- 地区 --%>
			<span id="areaSpan" style="float:left;margin-left:20px"></span>
			<%-- 语言 --%>
			<span id="languageSpan" style="float:left;margin-left:20px"></span>
			<br/>
		</dd>
		<dt></dt>
		<dd>
			<table id="conditionMediaTable"></table>
			<ul class="pagination" id="conditionMediaTurnPageUl">
<!-- 			    <li><a href="#" title="1">&laquo;</a></li>
			    <li><a href="#" title="1">1</a></li>
			    <li><a href="#" title="1">2</a></li>
			    <li><a href="#" title="1">3</a></li>
			    <li><a href="#" title="1">4</a></li>
			    <li><a href="#" title="1">5</a></li>
			    <li><a href="#">&raquo;</a></li> -->
			</ul>
		</dd>
	</dl>
</body>
</html>