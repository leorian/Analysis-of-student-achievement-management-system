<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <script>
	$(function() {
		$( "#stuSerivce" ).tabs();
		$(".button").button();
	});
	</script>
  </head>
  <body>
  <jsp:include page="navigation.jsp"></jsp:include>
  	<div id="stuSerivce">
	<div id="stuSerivce-1">
   		<a href="stuForm.action"><button class="button">学生信息添加</button></a>&nbsp;&nbsp;
    	<a href="showAllStu.action"><button class="button">学生信息编辑</button></a>&nbsp;&nbsp;
    	<a href="stuSearch.action"><button class="button">学生信息查询</button></a>&nbsp;&nbsp;
    	<a href="stuSearchByGrade.action"><button class="button">班级信息查询</button></a>&nbsp;&nbsp;
    </div>
</div>
  </body>
</html>
