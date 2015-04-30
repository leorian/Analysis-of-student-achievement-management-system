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
  	<jsp:include page="scoreServiceNavigation.jsp"></jsp:include>
  	<div id="accordion">
  		<h3>成绩管理——成绩录入</h3>
  		<div align="center">
  			 班级：<s:property value="grade.year" />届（<s:property value="grade.classID" />）班&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;
  			 学期：<s:property value="xueqi" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;
  			 类型：<s:property value="examType" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;
  			 学号：<s:property value="stuXueHao" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;
  			 姓名：<s:property value="subStudent.stuName"/>
  			<hr/>
  			<form action="scoreEnterSimpleStuSuc.action" id="form1" method="post">
  			<!-- 班级编号 -->
  			<input type="hidden" name="gradeID" value='<s:property value="grade.bianHao" />'>
  			<!-- 学生学号 -->
  			<input type="hidden" name="stuXueHao" value='<s:property value="stuXueHao" />'>
  			<!-- 学期 -->
  			<input type="hidden" name="xueqi" value='<s:property value="xueqi" />'>
  			<!-- 考试类型 -->
  			<input type="hidden" name="examType" value='<s:property value="examType" />'>
  			<table class="datalist">
  				<tr><th colspan="2">考试时间：<input type="text" name="examTime" value='<s:property value="currentDate"/>' /></th></tr>
  				<tr><th>课程名称</th><th>考试成绩</th></tr>
  				<s:iterator value="courses">
  					<tr>
  						<td> <input type="hidden" name="courseIDs" value='<s:property value="bianHao" />' /> <input type="hidden" name="courseNames" value='<s:property value="name" />' > <s:property value="name" /> </td>
  						<td>	<input type="text" name="scores" value="0" /><span class="ErrorStyle"></span></td>
  					</tr>
  				</s:iterator>
  				<tr><td colspan="2"><input type="button" name="saveScore" value="保存成绩" />&nbsp;&nbsp;&nbsp;<input type="reset" value="取消" /></td></tr>
  			</table>
  			</form>
  		</div>
  	</div>
  </body>
</html>
