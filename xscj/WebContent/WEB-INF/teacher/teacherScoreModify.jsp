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
	 digit = /^\d+(\.\d+)?$/;//匹配非负浮点数
	$saveScore = $("input[name=saveScore]");

	$saveScore.bind("click",function (){
		var resultFlag = true;
		$("input[name=scores]").each(function(index) {
         if( $.trim($(this).val())=="" || !digit.test($(this).val()))
         {
         	$(this).css({border:"solid 2px #f00"});
         	resultFlag = false;
         }
         else if($(this).val()>=150){
         	$(this).css({border:"solid 2px #f00"});
         	resultFlag = false;
         }
         else{
         	$(this).css({border:""});
         }
     });
     if(true == resultFlag)
     {
     	$("#form1").submit();
     }
     else{
     	alert("成绩输入有错误，请核对后再提交");
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
	padding-top:4px; padding-bottom:4px;
	padding-left:10px; padding-right:10px;
}
.datalist tr.altrow{
	background-color:#c7e5ff;	/* 隔行变色 */
}
a:link {  
   color: #00F;  
   text-decoration: none; 
   padding:3px;
   margin:1px;
}  
   a:visited {  
   text-decoration: none;  
   color: #000000;  
}  
   a:hover {  
/*    text-decoration: underline;  
   color: #FF0000;   */
  background-color:white;
}  
   a:active {  
   text-decoration: none;  
   color: #FF0000;  
}  
	</style>
  </head>
  
  <body>
  <jsp:include page="navigation.jsp"></jsp:include>
  <br>
  	<div id="accordion">
  		<h3>教师教学服务平台</h3>
  		<div align="center">
  		<strong><s:property value="grade.year" />届（<s:property value="grade.classID" />）班所有学生第<s:property value="xueqi" />学期<s:property value="course.name" />科目成绩修改界面</strong>
  		<hr/>
  		<form action="teacherScoreModifySuc.action" method="post" id="form1">
  		<table class="datalist">
  			<tr><th>学生学号</th><th>学生姓名</th><th>原始成绩</th><th>新的成绩</th></tr>
  			<s:iterator value="idNameScores" status="st">
  			<input type="hidden" name="stuXueHaos" value='<s:property value="stuXueHao" />' />
  			<input type="hidden" name="stuNames" value='<s:property value="name" />' />
  			<s:if test="#st.Even">
  				<tr class="altrow">
  				<td><s:property value="stuXueHao" /></td><td><s:property value="name" /></td><td><s:property value="score" /></td><td><input type="text" name="scores" value='<s:property value="score" />' /></td>
				</tr>
  			</s:if>
  			<s:else>
  				<tr>
  				<td><s:property value="stuXueHao" /></td><td><s:property value="name" /></td><td><s:property value="score" /></td><td><input type="text" name="scores" value='<s:property value="score" />' /></td>
				</tr>
  			</s:else>
  			</s:iterator>
  			<tr><td colspan="4"><input type="button" name="saveScore" value="保存-修改" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="取消-修改" /></td></tr>
  			</table>
  			<input type="hidden" name="gradeID" value='<s:property value="gradeID" />'/>
  			<input type="hidden" name="xueqi" value='<s:property value="xueqi" />'/>
  			<input type="hidden" name="examType" value='<s:property value="examType" />'/>
  			<input type="hidden" name="courseID" value='<s:property value="courseID" />'/>
  		</form>
  		<hr/>
  		</div>
  	</div>
  </body>
</html>
