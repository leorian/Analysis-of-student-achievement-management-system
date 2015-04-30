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
	text-align:center;
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
  		<h3>成绩管理——成绩录入成功</h3>
  		<div align="center">
  			  班级：<s:property value="grade.year" />届（<s:property value="grade.classID" />）班&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;
  			  学期：<s:property value="xueqi" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;
  			  类型：<s:property value="examType" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;
  			  学号：<s:property value="stuXueHao" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;
  			  姓名：<s:property value="subStudent.stuName"/>
  			<hr/>
  			<table class="datalist">
  				<tr>
  					<th>课程名称</th>
  					<s:iterator value="courseNames" ><td><s:property /></td></s:iterator>
  					</tr><tr>
  					<th>最终成绩</th>
  					<s:iterator value="scores" ><td><s:property /></td></s:iterator>
  				</tr>
  				<tr>
  					<th>操作结果</th>
  					<s:iterator value="scores" ><td><span style="color:red">已添加</span></td></s:iterator>
  				</tr>
  			</table>
  		</div>
  	</div>
  </body>
</html>
