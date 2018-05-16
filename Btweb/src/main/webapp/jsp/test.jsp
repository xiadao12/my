<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="${pageContext.request.contextPath}/plug/jquery-3.3.1/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plug/paging/js/paging.js"></script>
		
		<script>
			$(function(){
				$("#page").paging({
					pageNo:5,
					totalPage: 9,
					totalSize: 300,
					callback: function(num) {
						alert(num)
					}
				})
			});
		</script>
		
	</head>
	

	<body>
		<div id="page" class="page_div"></div>
	</body>



</html>