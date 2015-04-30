<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <script>
	$(function() {
		$( "#courseService" ).tabs();
		$(".button").button();
	});
	</script>
  </head>
  <body>
  <jsp:include page="navigation.jsp"></jsp:include>
  	<div id="courseService">
	<div id="courseService-1">
   		<a href="courseForm.action"><button class="button">课程信息添加</button></a>&nbsp;&nbsp;
    	<a href="courseAll.action"><button class="button">课程信息删除</button></a>&nbsp;&nbsp;
    </div>
</div>
  </body>
</html>
