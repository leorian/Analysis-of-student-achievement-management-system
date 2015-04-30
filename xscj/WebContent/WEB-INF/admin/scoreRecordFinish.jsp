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
  <jsp:include page="/WEB-INF/admin/scoreServiceNavigation.jsp"></jsp:include>
  	<div id="accordion">
  		<h3>成绩管理——成绩录入成功</h3>
  		<div align="center">
  		<table class="datalist">
  		<tr>
  		<th colspan="3" align="center">班级 —— <s:property value="grade.year"/>届（<s:property value="grade.classID" />）班</th>
		
  		<td rowspan="7"><img alt="" src='scoreRankPie.action?failCount=<s:property value="failCount" />&sucCount=<s:property value="sucCount" />&goodCount=<s:property value="goodCount" />&secondaryCount=<s:property value="secondaryCount" />&excellentCount=<s:property value="excellentCount" />&year=<s:property value="grade.year" />&classID=<s:property value="grade.classID" />&courseID=<s:property value="course.bianHao" />&xueqi=<s:property value="xueqi" />&examType=<s:property value="examType" />'  width="500" height="375" />
  			</td>
  		</tr><tr>
  		<th>考试类型 —— <s:property value="examType" /></th>
  		<th>学期 —— <s:property value="xueqi" /></th>
  		<th>课程 —— <s:property value="course.name" /></th>
  			</tr>
  			<tr>
  				<th>总分 —— <s:property value="totalScore" /></th>
  				<th>参加考试 —— <s:property value="stuCount" />人</th>
  				<th>平均分 —— <s:property value="avgScore" /></th>
  			</tr>
  		 <tr>
  			<th>最高分 —— <s:property value="maxScore" /></th>
  			<th>最低分 —— <s:property value="minScore" /></th>
  			<th>&nbsp;</th>
  			</tr>
  			<tr>
  			<th>不及格 —— <s:property value="failCount" />人</th>
  			<th>及格 —— <s:property value="sucCount" />人</th>
  			<th>良好 —— <s:property value="goodCount" />人</th>
  			</tr><tr>
  			<th>中等 —— <s:property value="secondaryCount" />人</th>
  			<th>优秀 —— <s:property value="excellentCount" />人</th>
  			<th>&nbsp;</th>
  		</tr>
  		<tr>
  			<td colspan="3" align="center">考试时间 —— <s:property value="examTime" /></td>
  		</tr>
  			</table>
  			<hr>
  			<table class="datalist">
  				<tr>
  					<th><strong>学号</strong></th>
  						<s:iterator value="stuXueHao">
  							<td><s:property/></td>
  						</s:iterator>
  					</tr>
  					<tr>
  					<th><strong>姓名</strong></th>
  					<s:iterator value="stuName">
  							<td><s:property/></td>
  						</s:iterator>
  					</tr>
  					<tr>
  					<th><strong>成绩</strong></th>
  					<s:iterator value="score">
  							<td><s:property/></td>
  						</s:iterator>
  					</tr>
  			</table>
  			<br>
  			<img alt="" src='scoreVariationLine.action?gradeID=<s:property value="grade.bianHao" />&courseID=<s:property value="course.bianHao" />&examTime=<s:property value="examTime" />&examType=<s:property value="examType" />&xueqi=<s:property value="xueqi" />&year=<s:property value="grade.year" />&classID=<s:property value="grade.classID" />'  width="1000" height="500" />
  		</div>
  	</div>
  </body>
</html>
