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
	color:blue;
	padding-top:4px; padding-bottom:4px;
	padding-left:10px; padding-right:10px;
}
a:link{
	text-decoration:none;
}
a:hover{
color:ORANGE;
}
	</style>
  </head>
  
  <body>
  <jsp:include page="navigation.jsp"></jsp:include>
  <br>
  	<div id="accordion">
  		<h3>教师教学服务平台</h3>
  		<div align="center">
  			<table class="datalist">
  				<caption><s:property value="#session['teacherName']" />教师的教学安排表</caption>
  				<tr>
  					<th>班级编号</th><th>班级名称</th><th>学期</th><th>课程编号</th><th>课程名称</th><th>操作</th>
  				</tr>
  				<s:iterator value="teachingTables">
  					<tr>
  						<td> <s:property value="gradeID" /> </td>
  						<td> <s:property value="year"/>届（<s:property value="classID" />）班 </td>
  						<td> <s:property value="xueqi" /> </td>
  						<td> <s:property value="courseID" /> </td>
  						<td> <s:property value="courseName" /> </td>
  						<td>
  							 成绩添加(<a href="teacherScoreAdd.action?gradeID=<s:property value="gradeID" />&xueqi=<s:property value="xueqi" />&courseID=<s:property value="courseID"/>&examType=期中">期中</a>
  							 <a href="teacherScoreAdd.action?gradeID=<s:property value="gradeID" />&xueqi=<s:property value="xueqi" />&courseID=<s:property value="courseID"/>&examType=期末">期末</a>)
  							  | 成绩删除(<a href="teacherScoreDel.action?gradeID=<s:property value="gradeID" />&xueqi=<s:property value="xueqi" />&courseID=<s:property value="courseID"/>&examType=期中">期中</a>
  							 <a href="teacherScoreDel.action?gradeID=<s:property value="gradeID" />&xueqi=<s:property value="xueqi" />&courseID=<s:property value="courseID"/>&examType=期末">期末</a>)
  							  | 成绩修改(<a href="teacherScoreModify.action?gradeID=<s:property value="gradeID" />&xueqi=<s:property value="xueqi" />&courseID=<s:property value="courseID"/>&examType=期中">期中</a>
  							 <a href="teacherScoreModify.action?gradeID=<s:property value="gradeID" />&xueqi=<s:property value="xueqi" />&courseID=<s:property value="courseID"/>&examType=期末">期末</a>)
  							  | 成绩查看(<a href="teacherScoreSeeing.action?gradeID=<s:property value="gradeID" />&xueqi=<s:property value="xueqi" />&courseID=<s:property value="courseID"/>&examType=期中">期中</a>
  							 <a href="teacherScoreSeeing.action?gradeID=<s:property value="gradeID" />&xueqi=<s:property value="xueqi" />&courseID=<s:property value="courseID"/>&examType=期末">期末</a>)
  						</td>
  					</tr>
  				</s:iterator>
  			</table>
  			<div style="color:red">
  				<s:if test="#session['ErrorFlag']">
  					此项考试成绩已录入完毕,请选择其它选项。
  				</s:if>
  				<s:set value="false" name="ErrorFlag" scope="session"></s:set>
  				<s:if test="#session['DelFlag']">
  					没有找到此项考试成绩相关的成绩记录，不需要删除。
  				</s:if>
  				<s:set value="false" name="DelFlag" scope="session"></s:set>
  				<s:if test="#session['ModifyFlag']">
  					没有找到此项考试成绩相关的成绩记录，不需要修改。
  				</s:if>
  				<s:set value="false" name="ModifyFlag" scope="session"></s:set>
  				<s:if test="#session['SeeFlag']">
  					没有找到此项考试成绩相关的成绩记录，请录入成绩后再查看。
  				</s:if>
  				<s:set value="false" name="SeeFlag" scope="session"></s:set>
  			</div>
  		</div>
  	</div>
  </body>
</html>
