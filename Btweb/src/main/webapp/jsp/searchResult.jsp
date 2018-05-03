<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		var searchValue = "<%=request.getParameter("searchValue")%>";
		$("#searchValueInput").val(searchValue);
		
		$.ajax({
			url:"searchByFuzzy",
			type:"post",
			dataType:"json",
			data:{
				"searchValue":searchValue
			},
			success:function(data){
				
			},
			error:function(){
				alert(获取数据失败);
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="/jsp/navigate.jsp" flush="true"/>
	searchMedia.do
</body>
</html>