<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>主页</title>
	
	<script type="text/javascript">
		//alert(1);
		//alert("${pageContext.request.contextPath}");
	</script>
</head>
<body>
	<jsp:include page="/jsp/navigate.jsp" flush="true"/>
	<jsp:include page="/jsp/initMedia.jsp" flush="true"/>
</body>
</html>