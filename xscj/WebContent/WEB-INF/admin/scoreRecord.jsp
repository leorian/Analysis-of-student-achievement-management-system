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
	$(function() {
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
	<div id="accordion">
		<h3><strong><s:property value="gradeName" /> —— 第<s:property value="xueqi" />学期</strong></h3>
		<div>
		<form action="scoreRecordFinish.action" method="post">
			<input type="hidden" name="courseID" value='<s:property value="courseID" />' />
			<input type="hidden" name="xueqi" value='<s:property value="xueqi" />' />
			<table class="datalist">
			<tr>
				<th colspan="3" align="left">	考试时间 ：<input type="text" name="examTime" value='<s:property value="currentDate" />' /></th>
			</tr>
			<tr>
				<th colspan="3" align="left">考试类型 ：<select name="examType">
				<option>期末</option>
				<option>期中</option>
				<option>月考</option>
				<option>模拟考</option>
				<option>单科</option>
			</select></th>
			</tr>
			<tr>
			<th colspan="2">授课教师 —— <span style="color:blue;"><s:property value="teacherName" /></span></th>
			<th>课程名称 —— <span style="color:blue;"><s:property value="courseName" /></span></th>
			</tr>
				<tr>
					<th><strong>学&nbsp;&nbsp;&nbsp;&nbsp;号</strong></th>
					<th><strong>姓&nbsp;&nbsp;&nbsp;&nbsp;名</strong></th>
					<th><strong>考试成绩</strong></th>
				</tr>
				<s:iterator value="subStudents">
					<tr>
						<td> <s:property value="xuehao" /> </td>
						<td> <s:property value="stuName" /> </td>
						<td> <input type="hidden" name="stuXueHao" value='<s:property value="xuehao" />' /> <input type="text" name="score" value="0" /> </td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="3"  align="center"> <input type="submit" value="确认" /> <input type="reset" value="取消" /> </td>
				</tr>
			</table>
			</form>
		</div>
	</div>
  </body>
</html>
