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
	text-align:left;
	padding-top:4px; padding-bottom:4px;
	padding-left:10px; padding-right:10px;
}
.datalist tr.altrow{
	background-color:#c7e5ff;	/* 隔行变色 */
}
.flag{
	color:red;
}
</style>

</head>

<body>
<div>
  	<jsp:include page="stuServiceNavigation.jsp"></jsp:include>
  </div>
	<div id="accordion">
		<h3>学生管理&nbsp;&gt;&nbsp;学生基本信息修改成功</h3>
		<div align="center" >
			 
				<table class="datalist">
					<tr>
						<th width="80" align="right" class="labelString"><strong>学生姓名</strong></th>
						<td width="350"> <s:property value="student.name"/> </td>
					</tr>
					
					<tr>
						<th align="right" class="labelString"><strong>登录密码</strong></th>
						<td> <s:property value="student.password" /> </td>	 
					</tr>
					
					<tr>
						<th align="right" class="labelString"><strong>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</strong></th>
						<td> <s:property value="student.sex" /> </td>
					</tr>
					
					<tr>
						<th align="right" class="labelString"><strong>身份证号</strong></th>
						<td> <s:property value="student.idCard" /> </td>
					</tr>
					
					<tr>
						<th align="right" class="labelString"><strong>家庭住址</strong></th>
						<td> <s:property value="student.address" /> </td>
					</tr>
					
					<tr>
						<th width="80" align="right" class="labelString"><strong>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族</strong></th>
						<td> <s:property value="student.nation" /> </td>
					</tr>
					<tr><th  width="80" align="right" class="labelString">
							<strong>分配班级</strong>
						</th>
						<td> <s:property value="grade.year" />届（<s:property value="grade.classID" />）班
						</td>
						
					</tr>
					<tr>
						<th align="right" class="labelString"><strong>政治面貌</strong></th>
						<td> <s:property value="student.polStat" /> </td>
					</tr>
					<tr>
							<th align="right" class="labelString"><strong>入学时间</strong></th>
						<td> <s:property value="student.schoolTime" /> </td>
					</tr>
					<tr>
						<th align="right" class="labelString"><strong>户口类别</strong></th>
						<td> <s:property value="student.houseHold" /> </td>
					</tr>
					<tr>
						<th align="right" class="labelString"><strong>就读方式</strong></th>
						<td> <s:property value="student.schoolMethod" /> </td>
						 
					</tr>
				</table>
		 
		</div>
	</div>
</body>
</html>
