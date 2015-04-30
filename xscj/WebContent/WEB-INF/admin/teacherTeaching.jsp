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
		$(".button").button();
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
    	<h3>教师教学管理平台</h3>
    	<div>
    		<table class="datalist">
    			<tr>
    				<th><strong>班&nbsp;&nbsp;&nbsp;&nbsp;级</strong></th>
    				<th><strong>学&nbsp;&nbsp;&nbsp;&nbsp;期</strong></th>
    				<th><strong>班 主 任</strong></th>
    				<th><strong>科&nbsp;&nbsp;&nbsp;&nbsp;目</strong></th>
    				<th><strong>授课老师</strong></th>
    				<th><strong>操&nbsp;&nbsp;&nbsp;&nbsp;作</strong></th>
    			</tr>
    			<s:iterator value="gcs">
    			<s:form theme="simple" method="post" action="scoreRecord.action">
    			<input type="hidden" name="gradeID" value='<s:property value="gradeId" />' />
    			<input type="hidden" name="courseID" value='<s:property value="courseId" />' />
    			<input type="hidden" name="teacherID" value='<s:property value="teacherBianHao" />' />
    			<input type="hidden" name="xueqi" value='<s:property value="xueqi" />' />
    			<input type="hidden" name="courseName" value='<s:property value="courseName" />' />
    			<input type="hidden" name="teacherName" value='<s:property value="teacherNameString" />' />
    			<input type="hidden" name="gradeName" value='<s:property value="gradeYear" />届（<s:property value="gradeClassid" />）班' />
    				<tr>
    					<td><s:property value="gradeYear" />届（<s:property value="gradeClassid" />）班</td>
    					<td><s:property value="xueqi" /></td>
    					<td> <s:property value="gradeAdviser"/> </td>
    					<td><s:property value="courseName"/> </td>
    					<td> <s:property value="teacherNameString"/> </td>
    					<td><s:submit value="成绩统计" /></td>
    				</tr>
    			</s:form>
    			</s:iterator>
    		
    		</table>
    	</div>
    </div>
  </body>
</html>
