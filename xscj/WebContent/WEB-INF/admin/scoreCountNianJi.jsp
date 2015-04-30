<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	background-color: #7ecef4;
}

.datalist {
	border: 1px solid #0058a3; /* 表格边框 */
	font-family: Arial;
	border-collapse: collapse; /* 边框重叠 */
	background-color: #eaf5ff; /* 表格背景色 */
	font-size: 14px;
}

.datalist caption {
	padding-bottom: 5px;
	font: bold 1.4em;
	text-align: left;
}

.datalist th {
	border: 1px solid #0058a3; /* 行名称边框 */
	background-color: #4bacff; /* 行名称背景色 */
	color: #FFFFFF; /* 行名称颜色 */
	font-weight: bold;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 12px;
	padding-right: 12px;
	text-align: center;
}

.datalist td {
	border: 1px solid #0058a3; /* 单元格边框 */
	text-align: left;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 10px;
	padding-right: 10px;
}

.datalist tr.altrow {
	background-color: #c7e5ff; /* 隔行变色 */
}
#accordion{
	height:500px;
}
</style>
</head>
<body>
	<jsp:include page="scoreServiceNavigation.jsp"></jsp:include>
	<div id="accordion">
		<h3>成绩管理——<span style="color:blue"><s:property value="nianJi" />届 第<s:property value="xueqi" />学期 <s:property value="examType"/>考试</span> 成绩统计结果如下</h3>
		<div align="center">

			<s:iterator value="map">
		班级编号：<s:property value="key.gradeID" />&nbsp;&nbsp;
		班级名称：<span style="color:red"><s:property value="key.year" />届（<s:property value="key.classID" />）班</span>&nbsp;&nbsp;
		学生人数：<span style="color:red"><s:property value="key.stuCount" /></span>&nbsp;&nbsp;
		班主任教师编号：<s:property value="key.adviserID" />&nbsp;&nbsp;
		班主任姓名：<span style="color:red"><s:property value="key.adviserName" /></span>
				<br />
				<br />
				<table class="datalist">
					<tr>
						<th>课程编号</th>
						<th>课程名称</th>
						<th>最高分</th>
						<th>最低分</th>
						<th>平均分</th>
						<th>及格人数</th>
						<th>不及格人数</th>
					</tr>
					<s:iterator value="value">
						<tr>
							<td><s:property value="courseId" /></td>
							<td><s:property value="courseName" /></td>
							<td><s:property value="maxScore" /></td>
							<td><s:property value="minScore" /></td>
							<td><s:property value="avgScore" /></td>
							<td><s:property value="goodCount" /></td>
							<td><s:property value="badCount" /></td>
						</tr>
					</s:iterator>
				</table>
					<br/>
				<br/>
				<hr>
			</s:iterator>

		</div>
	</div>

</body>
</html>