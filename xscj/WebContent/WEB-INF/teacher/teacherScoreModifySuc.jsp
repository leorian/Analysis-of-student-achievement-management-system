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
	$(function (){
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
	text-align:center;
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
a:link {  
   color: #00F;  
   text-decoration: none; 
   padding:3px;
   margin:1px;
}  
   a:visited {  
   text-decoration: none;  
   color: #000000;  
}  
   a:hover {  
/*    text-decoration: underline;  
   color: #FF0000;   */
  background-color:white;
}  
   a:active {  
   text-decoration: none;  
   color: #FF0000;  
}  
	</style>
  </head>
  
  <body>
   <jsp:include page="navigation.jsp"></jsp:include>
  <br>
  	<div id="accordion">
  		<h3>教师教学服务平台</h3>
  		<div align="center">
  		<s:property value="grade.year" />届（<s:property value="grade.classID" />）班所有学生第<s:property value="xueqi" />学期<s:property value="examType" />考试成绩<span>修改成功</span>
  			<hr>
  			<table class="datalist">
  				<tr><th>学号</th><th>姓名</th><th>最终成绩</th></tr>
  				<s:iterator value="stuXueHaos" status="st">
  					<tr><td><s:property /></td><td><s:property value="stuNames[#st.index]" /></td><td><s:property value="scores[#st.index]" /></td></tr>
  				</s:iterator>
  			</table>
  			<hr>
  		</div>
  	</div>
  </body>
</html>
