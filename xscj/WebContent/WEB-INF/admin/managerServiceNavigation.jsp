<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <script>
	$(function() {
		$( "#managerService" ).tabs();
		$(".button").button();
	});
	</script>
  </head>
  <body>
  <jsp:include page="navigation.jsp"></jsp:include>
  	<div id="managerService">
	<div id="managerService-1">
   		<button class="button">查看个人信息</button>&nbsp;&nbsp;
    	<button class="button">修改密码</button>&nbsp;&nbsp;
    	<button class="button">上传头像</button>&nbsp;&nbsp;
    </div>
</div>
  </body>
</html>
