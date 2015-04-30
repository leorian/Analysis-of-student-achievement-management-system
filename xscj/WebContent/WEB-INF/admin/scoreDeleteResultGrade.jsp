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
function saveScore(){
	$saveScore = $("input[name=saveScore]");
	$saveScore.bind("click",function(){
		 if($("input[name='stuXueHaos']:checked").length > 0)
		 {
		 	$("#form1").submit();
		 }
		 else{
		 	alert("您没有勾选任何复选框，系统将不会进行任何操作");
		 }
	}); 
}
	$(function(){
		$("#accordion").accordion();
		saveScore();
	});
</script>
<style type="text/css">
	body {
	font: 72.5% "Trebuchet MS", sans-serif;
	margin: 50px;
	background-color:#7ecef4;
}
.box{
	padding: 8px;
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

a:link {  
   color: #000000;  
   text-decoration: none;  
}  
   a:visited {  
   text-decoration: none;  
   color: #000000;  
}  
   a:hover {  
   text-decoration: underline;  
   color: #FF0000;  
}  
   a:active {  
   text-decoration: none;  
   color: #FF0000;  
}  


</style>
  </head>
  
  <body>
  	<jsp:include page="scoreServiceNavigation.jsp"></jsp:include>
  	<div id="accordion">
  	<h3>成绩管理——成绩删除</h3>
  	<div align="center">
  		<strong><s:property value="grade.year" />届（<s:property value="grade.classID" />）班所有学生第<s:property value="xueqi" />学期<s:property value="course.name" />科目成绩删除界面</strong>
  		<hr/>
  		<form action="scoreDeleteResultGradeSuc.action" method="post" id="form1">
  		<table class="datalist">
  			<tr><th>学生学号</th><th>学生姓名</th><th>最终成绩</th><th>选择删除</th></tr>
  			<s:iterator value="idNameScores" status="st">
  			<s:if test="#st.Even">
  				<tr class="altrow">
  				<td><s:property value="stuXueHao" /></td><td><s:property value="name" /></td><td><s:property value="score" /></td><td><input type="checkbox" name="stuXueHaos" value='<s:property value="stuXueHao" />'   /></td>
				</tr>
  			</s:if>
  			<s:else>
  				<tr>
  				<td><s:property value="stuXueHao" /></td><td><s:property value="name" /></td><td><s:property value="score" /></td><td><input type="checkbox" name="stuXueHaos" value='<s:property value="stuXueHao" />'   /></td>
				</tr>
  			</s:else>
  			</s:iterator>
  			<tr><td colspan="4"><input type="button" name="saveScore" value="删除选择项" /></td></tr>
  			</table>
  				<input type="hidden" value='<s:property value="xueqi" />' name="xueqi" />
  				<input type="hidden" value='<s:property value="examType" />' name="examType" />
  				<input type="hidden" value='<s:property value="courseID" />' name="courseID" />
  				<input type="hidden" value='<s:property value="grade.bianHao" />' name="gradeID" />
  			</form>
  		<hr/>
  	</div>
  	</div>
  </body>
</html>
