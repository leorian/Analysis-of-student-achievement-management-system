<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <script>
	$(function() {
		$( "#scoreService" ).tabs();
		$(".button").button();
	});
	</script>
  </head>
  <body>
  <jsp:include page="navigation.jsp"></jsp:include>
  	<div id="scoreService">
	<div id="scoreService-1">
   		<a href="scoreEnterChose.action"><button class="button">成绩录入</button></a>
    <a href="scoreQuery.action"><button class="button">成绩查询和分析</button></a>
    <a href="scoreModify.action"><button class="button">成绩修改</button></a>
    <a href="scoreDelete.action"><button class="button">成绩删除</button></a>
    <a href="scoreCount.action"><button class="button">成绩统计</button></a>
     <a href="scorePrinter.action"><button class="button">成绩打印</button></a>
    </div>
</div>
  </body>
</html>
