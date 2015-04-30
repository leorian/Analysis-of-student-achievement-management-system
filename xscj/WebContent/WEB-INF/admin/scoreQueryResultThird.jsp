<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>洛天工作室</title>
     <link href="css/ui-lightness/jquery-ui-1.10.4.custom.css"
	rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript">
	$(function(){
		$("#accordion").accordion();
	});
</script>
<style type="text/css">
	body {
	font: 72.5% "Trebuchet MS", sans-serif;
	margin: 50px;
	background-color:#7ecef4;
}
 
/* table tr{
background-color:#d4e3e5;
}
table td{
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	text-align: center;
} */
</style>
  </head>
  
  <body>
  	<jsp:include page="scoreServiceNavigation.jsp"></jsp:include>
  	<div id="accordion">
  	<h3>成绩管理——成绩查询结果显示如下</h3>
  		<div align="center">
  		<img alt="" src='scoreAnalyzeThird.action?gradeID=<s:property value="gradeID" />&courseID=<s:property value="courseID" />' width="1000" height="500">
  		<hr>
  				<%-- <table>
  					<tr>
  						<td>学号</td><td>姓名</td><td>考试时间</td><td>考试类型</td><td>学期</td><td>最后成绩</td><td>操作</td>
  					</tr>
  					<s:iterator value="scoreByGidCids">
  						<tr>
  							<td> <s:property value="stuXueHao" /> </td>
  							<td> <s:property value="stuName" /> </td>
  							<td> <s:property value="examTime" /> </td>
  							<td> <s:property value="examType" /> </td>
  							<td> 第<s:property value="xueqi" />学期 </td>
  							<td> <s:property value="score" /> </td>
  							<td>&nbsp;&nbsp;<a href="#">编辑</a>&nbsp;&nbsp;&nbsp;<a href="#">删除</a>&nbsp;&nbsp;</td>
  						</tr>
  					</s:iterator>
  				</table> --%>
  		</div>
  	</div>
  </body>
</html>
