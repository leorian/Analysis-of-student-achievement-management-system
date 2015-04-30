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
		function searchBtn(){
		var $searchBtn = $("input[name=searchBtn]");
		$searchBtn.bind("click", function(){
			$.ajax(
			{
				type:"post",
				url:"scoreExisitsTest.action",
				dataType:"json",
				data:{
					stuXueHao:$("input[name=stuXueHao]").val(),
					examType:$("select[name=examType]").val(),
					xueqi:$("select[name=xueqi]").val()
				},
				success : function(data) {
					var d = eval("(" + data + ")");
					 if(d.flag > 0)
					 {
					 	$("#form1").submit();
					 }
					 else
					 {
					 	alert("没有找到相关的成绩记录");
					 	window.location.href="stuMain.action";
					 }
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}
			}
			);
		});
	}
			$(function(){
				$("#accordion").accordion();
				searchBtn();
			});
		</script>
		<style type="text/css">
			body {
			font: 72.5% "Trebuchet MS", sans-serif;
			margin: 50px;
			background-color:#7ecef4;
			}
			td{
				padding-right:20px;
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
 <jsp:include page="navigation.jsp"></jsp:include>
  	<div id="accordion">
  		<h3>成绩查询</h3>
  		<div align="center">
  		<form action="stuScoreSearch.action" method="post" id="form1">
  			<input type="hidden" name="stuXueHao" value='<s:property value="#session['userName']" />' >
  			<table>
  				<tr>
  					<th>班级：</th><td><s:property value="grade.year" />届（<s:property value="grade.classID" />）班</td>
  				 
  					<th>学期：</th><td>
  						<select name="xueqi">
  							<option value="1" <s:if test='xueqi==1'>selected="selected"</s:if>>高一上学期</option>
  							<option value="2" <s:if test='xueqi==2'>selected="selected"</s:if>>高一下学期</option>
  							<option value="3" <s:if test='xueqi==3'>selected="selected"</s:if>>高二上学期</option>
  							<option value="4" <s:if test='xueqi==4'>selected="selected"</s:if>>高二下学期</option>
  							<option value="5" <s:if test='xueqi==5'>selected="selected"</s:if>>高三上学期</option>
  							<option value="6" <s:if test='xueqi==6'>selected="selected"</s:if>>高三下学期</option>
  						</select>
  					</td>
  				 
  					<th>类型：</th><td>
  						<select name="examType">
  							<option value="期中" <s:if test='examType=="期中"'>selected="selected"</s:if>>期中</option>
  							<option value="期末" <s:if test='examType=="期末"'>selected="selected"</s:if>>期末</option>
  						</select>
  					</td>
  					<td align="center"><input type="button" name="searchBtn" value="查询" /></td>
  				</tr>
  			</table>
  			</form>
  			<hr>
  			<div align="center">
  			<table class="datalist">
  			<tr>
  				<th>课程编号</th><th>课程名称</th><th>考试时间</th><th>最后成绩</th>
  			</tr>
  			<s:iterator value="scoreBySXTs">
  				<tr>
  					<td> <s:property value="courseID" /> </td>
  					<td> <s:property value="courseName" /> </td>
  					<td> <s:property value="examTime" /> </td>
  					<td> <s:property value="score" /> </td>
  				</tr>
  			</s:iterator>
  			<tr><th colspan="3">总分</th><td><s:property value="scoreSum" /></td></tr>
  			</table>
  		</div>
  		</div>
  	</div>
  </body>
</html>
