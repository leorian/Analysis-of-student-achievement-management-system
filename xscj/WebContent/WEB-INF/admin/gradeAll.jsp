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
		function delBtn(){
		var $delBtn = $("input[name=delBtn]");
		$delBtn.bind("click", function(){
			if(window.confirm("确定删除这些班级信息吗，将会一并删除关联这些班级的所有学生的成绩记录以及所有的教学安排？"))
			{
				$("#myForm").submit();
			}
			else{
			};
		});
	}

	$(function(){
		$("#accordion").accordion();
		delBtn();
		
	});
</script>
<style type="text/css">
body {
	font: 72.5% "Trebuchet MS", sans-serif;
	margin: 50px;
	background-color:#7ecef4;
}
#accordion{
	height:500px;
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
  <jsp:include page="gradeServiceNavigation.jsp"></jsp:include>
  	<div id="accordion">
  		<h3>班级管理&nbsp;&gt;&nbsp;班级信息删除</h3>
  		<div>
  		<form action="gradeDelete.action" method="post" id="myForm">
  			<table class="datalist">
  				<tr><th>班级编号</th><th>班级名称</th><th>班主任编号</th><th>班主任姓名</th><th>学生人数</th><th>操作</th></tr>
  				<s:iterator value="gradeInfos">
  					<tr>
  						<td> <s:property value="gradeID" /> </td><td> <s:property value="year"/>届（<s:property value="classID" />）班 </td>
  						<td> <s:property value="adviserID" /> </td><td> <s:property value="adviserName" /> </td>
  						<td> <s:property value="stuCount" /> </td>
  						<s:if test="stuCount == 0">
  							<td> <input type="checkbox" name="gradeDel" value='<s:property value="gradeID" />' /> </td>
  						</s:if>
  						<s:else>
  							<td>&nbsp;</td>
  						</s:else>
  					</tr>
  				</s:iterator>
  				<tr>
  					<td colspan="5"></td>
  					<td> <input type="button" name="delBtn" value="删除" /> </td>
  				</tr>
  			</table>
  			</form>
  		</div>
  	</div>
  </body>
</html>
