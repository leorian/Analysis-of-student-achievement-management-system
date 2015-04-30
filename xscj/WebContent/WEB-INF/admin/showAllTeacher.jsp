<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	text-align: center;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 10px;
	padding-right: 10px;
}

.datalist tr.altrow {
	background-color: #c7e5ff; /* 隔行变色 */
}

.frame {
	height: 435px;
}

a:link {
	text-decoration: none;
}
</style>
</head>

<body>
	<div>
		<jsp:include page="teacherServiceNavigation.jsp"></jsp:include>
	</div>
	<div id="accordion">
		<h3>教师管理&nbsp;&gt;&nbsp;教师信息编辑</h3>
		<div align="center" class="frame">
					<s:if test="#session['teacherDelErrorFlag']">
				<div style="color:red">该教师目前还有关联的教学安排，系统拒绝您的删除请求</div>
			</s:if>
			<s:set name="teacherDelErrorFlag" value="false" scope="session" ></s:set>
			<s:if test="#session['teacherAdviserDelErrorFlag']">
				<div style="color:red">该教师目前仍然还是班级的管理者，系统拒绝您的删除请求</div>
			</s:if>
			<s:set name="teacherAdviserDelErrorFlag" value="false" scope="session" ></s:set>
			<table cellspacing="0" cellpadding="8" class="datalist">
				<tr>
					<th>教师编号</th>
					<th>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</th>
					<th>科&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目</th>
					<th>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</th>
					<th>身份证号</th>
					<th>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族</th>
					<th>政治面貌</th>
					<th>入职时间</th>

					<th>操作</th>
				</tr>
				<s:iterator value="teachers" status="st">
					<tr onmouseover="this.style.backgroundColor='#ffff66';"
						onmouseout="this.style.backgroundColor='#d4e3e5';"
						<s:if test="#st.odd">class="altrow"</s:if> >
						<td><s:property value="bianHao" /></td>
						<td><s:property value="name" /></td>
						<td><s:property value="courseName" /></td>
						<td><s:property value="sex" /></td>
						<td><s:property value="idCard" /></td>
						<td><s:property value="nation" /></td>
						<td><s:property value="polStat" /></td>
						<td><s:property value="jobTime" /></td>
						<td><a
							href="teacherModify.action?teacherBianHao=<s:property value="bianHao"/>"><input
								type="button" value="修改" /></a>&nbsp;&nbsp;<a
							href="teacherSeeing.action?teacherBianHao=<s:property value="bianHao"/>"><input
								type="button" value="查看" /></a>&nbsp;&nbsp;<a
							href='teacherDelete.action?bianHao=<s:property value="bianHao"/>'
							onclick="return confirm('确定删除吗');"><input type="button"
								value="删除" /></a></td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="9"><s:url id="url_first"
							value="showAllTeacher.action">
							<s:param name="pageNow" value="1"></s:param>
						</s:url> <s:url id="url_pre" value="showAllTeacher.action">
							<s:param name="pageNow" value="pageNow-1"></s:param>
						</s:url> <s:url id="url_next" value="showAllTeacher.action">
							<s:param name="pageNow" value="pageNow+1"></s:param>
						</s:url> <s:url id="url_last" value="showAllTeacher.action">
							<s:param name="pageNow" value="pageTotal"></s:param>
						</s:url> 共有【<s:property value="rowTotal" />】条教师记录，当前第【<s:property
							value="pageNow" />】页，总共【<s:property value="pageTotal" />】页 <s:a
							href="%{url_first}">
							<input type="button" value="首页" />
						</s:a> <s:a href="%{url_pre}">
							<input type="button" value="上一页" />
						</s:a> <s:iterator value="teachers" status="status">
							<s:url id="url" value="showAllTeacher.action">
								<s:param name="pageNow" value="pageNow" />
							</s:url>
						</s:iterator> <s:a href="%{url_next}">
							<input type="button" value="下一页" />
						</s:a> <s:a href="%{url_last}">
							<input type="button" value="尾页" />
						</s:a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
