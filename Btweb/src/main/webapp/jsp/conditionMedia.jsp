<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%-- 分页插件样式 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug/paging/css/paging.css"> 

<script type="text/javascript" src="${pageContext.request.contextPath}/js/showMediaDiv.js"></script>
<%-- 分页插件 --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug/paging/js/paging.js"></script>

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
		$("#selectConditonDiv label").on("mouseover",function(){
			$(this).addClass("conditionLabelMouseover");
		}).on("mouseout",function(){
			$(this).removeClass("conditionLabelMouseover");
		}).on("click",function(){
			$(this).addClass("conditionLabelClick");
			$(this).siblings().removeClass("conditionLabelClick");
			
			<%-- 设置已选的搜索条件 --%>
			var ddTitle = $(this).parent("div").attr("title");
			var choosedValue = $(this).text();
			if(choosedValue == "全部")
			{
				choosedValue = "";
			}
			<%-- 设置已选的搜索条件 --%>
			setConditionSpan(ddTitle,choosedValue);
			<%--展示条件数据和分页栏--%>
			setConditionMediaDivAndPage(1,true);
		});
		
		//获取传参内容，然后展示对应的视频类型
		var reqMediaType = decodeURI(decodeURI("<%=request.getParameter("mediaType")%>"));
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
		}else if(ddTitle == "releaseYearTitle")
		{
			$("#releaseYearSpan").text(choosedValue);
		}else if(ddTitle == "areaTitle")
		{
			$("#areaSpan").text(choosedValue);
		}else if(ddTitle == "languageTitle")
		{
			$("#languageSpan").text(choosedValue);
		}
	}
	
	<%--展示条件数据和分页栏--%>
	function setConditionMediaDivAndPage(pageNum,ifReloadPage)
	{
		$conditionMediaDiv = $("#conditionMediaDiv");
		<%-- 清除之前的数据 --%>
		$conditionMediaDiv.empty();
		<%-- 根据条件搜索 --%>
		searchByCondition($conditionMediaDiv,pageNum,ifReloadPage);
	}
	
	<%-- 根据条件搜索 --%>
	function searchByCondition($conditionMediaDiv,pageNum,ifReloadPage){
		
		<%--清空没有数据的警告--%>
		$("#nullWaringDiv").empty();
		
		var mediaType = $("#mediaTypeSpan").text();
		var style = $("#styleSpan").text();
		var releaseYearString = $("#releaseYearSpan").text();
		var area = $("#areaSpan").text();
		var language = $("#languageSpan").text();
		
		$.ajax({
			type:"post",
			url:'${pageContext.request.contextPath}/searchConditionMedia.do',
	        dataType:"json",
			data:{
				"mediaType":mediaType,
				"style":style,
				"releaseYearString":releaseYearString,
				"area":area,
				"language":language,
				"pageNumString":pageNum
			},
			success:function(data){
				if(data != null)
				{
					<%--根据查询结果展示--%>
					if(judgeIsNull(data.mediaData))
					{
						$("#page").empty();
						$("#nullWaringDiv").append("暂无相关数据");
					}else
					{
						showMediaDiv($conditionMediaDiv,data.mediaData);
						if(ifReloadPage)
						{
							<%-- 分页 --%>
							$("#page").paging({
								totalPage: data.pageCount,
								callback: function(num) {
									setConditionMediaDivAndPage(num,false);
								}
							})
						}
					}
				}
			},
			error:function(){
				alert("获取数据失败");
			}
		});
	}
</script>
</head>
<body style="overflow-y:scroll;">
	<jsp:include page="/jsp/navigate.jsp" flush="true"/>
	<div id="selectConditonDiv" class="container" style="margin-top:2%;">
		<div class="row">
			<div class="col-md-1 col-sm-2" style="white-space:nowrap;padding:0;margin:0;">选择类型：</div>
			<div class="col-md-11 col-sm-10" title="mediaTypeTitle">
				<label style="cursor:pointer;" class="conditionLabelClick">全部</label>
				<label style="cursor:pointer;" id="mediaTypeMovieLabel">电影</label>
				<label style="cursor:pointer;" id="mediaTypeTvLabel">电视剧</label>
				<label style="cursor:pointer;" id="mediaTypeAnimationLabel">动漫</label>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-1 col-sm-2" style="white-space:nowrap;padding:0;margin:0;">选择分类：</div>
			<div class="col-md-11 col-sm-10" title="styleTitle">
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
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-1 col-sm-2" style="white-space:nowrap;padding:0;margin:0;">选择年代：</div>
			<div class="col-md-11 col-sm-10" title="releaseYearTitle">
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
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-1 col-sm-2" style="white-space:nowrap;padding:0;margin:0;">选择地区：</div>
			<div class="col-md-11 col-sm-10" title="areaTitle">
				<label style="cursor:pointer;" class="conditionLabelClick">全部</label>
				<label style="cursor:pointer;">大陆</label>
				<label style="cursor:pointer;">香港</label>
				<label style="cursor:pointer;">台湾</label>
				<label style="cursor:pointer;">日本</label>
				<label style="cursor:pointer;">韩国</label>
				<label style="cursor:pointer;">欧美</label>
				<label style="cursor:pointer;">泰国</label>
				<label style="cursor:pointer;">印度</label>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1 col-sm-2" style="white-space:nowrap;padding:0;margin:0;">选择语言：</div>
			<div class="col-md-11 col-sm-10" title="languageTitle" class="col-11">
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
			</div>
		</div>
		<div class="row">
			<div class="col-md-1 col-sm-2" style="white-space:nowrap;padding:0;margin:0;">已选条件：</div>
			<div class="col-md-11 col-sm-10">
				<%-- 电影类型 --%>
				<span id="mediaTypeSpan"></span>
				<%-- 电影分类 --%>
				<span id="styleSpan"></span>
				<%-- 电影年代 --%>
				<span id="releaseYearSpan"></span>
				<%-- 地区 --%>
				<span id="areaSpan"></span>
				<%-- 语言 --%>
				<span id="languageSpan"></span>
			</div>
		</div>
		
		<div id="conditionMediaDiv" class="row"></div>
		<div class="row text-center" style="margin-top:50px;">
			<div id="nullWaringDiv" class="col-12 nullWaring"></div>
		</div>
		<div class="row text-center" style="margin-bottom:20px;">
			<div id="page" class="page_div col-12"></div>
		</div>
		
		
	</div>
</body>
</html>