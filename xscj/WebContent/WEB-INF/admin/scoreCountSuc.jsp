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
.datalist{
	border:1px solid #0058a3;	/* 表格边框 */
	font-family:Arial;
	border-collapse:collapse;	/* 边框重叠 */
	background-color:#eaf5ff;	/* 表格背景色 */
	font-size:14px;
}
.datalist caption{
	padding-bottom:5px;
	font:bold 1.4em;
	text-align:left;
}
.datalist th{
	border:1px solid #0058a3;	/* 行名称边框 */
	background-color:#4bacff;	/* 行名称背景色 */
	color:#FFFFFF;				/* 行名称颜色 */
	font-weight:bold;
	padding-top:4px; padding-bottom:4px;
	padding-left:12px; padding-right:12px;
	text-align:center;
}
.datalist td{
	border:1px solid #0058a3;	/* 单元格边框 */
	text-align:left;
	padding-top:4px; padding-bottom:4px;
	padding-left:10px; padding-right:10px;
}
.datalist tr.altrow{
	background-color:#c7e5ff;	/* 隔行变色 */
}
</style>
  </head>
  <body>
 <jsp:include page="scoreServiceNavigation.jsp"></jsp:include>
  <div id="accordion">
  	<h3>成绩管理——成绩统计结果如下</h3>
  	<div align="center">
  		<table class="datalist">
  			<tr><th>学号</th><th>姓名</th>
  				<s:iterator value="courses"> <th> <s:property value="name" /> </th> </s:iterator><th>总分</th>
  			</tr>
  			<s:iterator value="scoreTotals">
  			<tr><td> <s:property value="xuehao" /> </td><td> <s:property value="name" /> </td>
  				<s:iterator value="scoreSmalls" >
  					<td> <s:property value="score" /> </td>
  				</s:iterator>
  				<td> <s:property value="totalScore" /> </td>
  			</tr>
  			</s:iterator>
  		</table>
  		<hr>
  		<img alt="" src='scoreCountAnalyze.action?gradeID=<s:property value="gradeID" />&xueqi=<s:property value="xueqi" />&examType=<s:property value="examType" />' width="1000" height="500">
  	</div>
  </div>
  </body>
</html>
