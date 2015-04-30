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
		</style>
  </head>
  <body>
  <jsp:include page="navigation.jsp"></jsp:include>
  	<div id="accordion">
  		<h3>成绩查询</h3>
  		<div align="center">
  		<form action="stuScoreSearch.action" method="post" id="form1">
  			<input type="hidden" name="stuXueHao" value='<s:property value="#session['userName']" />' >
  			<table class="datalist">
  				<tr>
  					<th>班级：</th><td><s:property value="grade.year" />届（<s:property value="grade.classID" />）班</td>
  				 
  					<th>学期：</th><td>
  						<select name="xueqi">
  							<option value="1">高一上学期</option>
  							<option value="2">高一下学期</option>
  							<option value="3">高二上学期</option>
  							<option value="4">高二下学期</option>
  							<option value="5">高三上学期</option>
  							<option value="6">高三下学期</option>
  						</select>
  					</td>
  				 
  					<th>类型：</th><td>
  						<select name="examType">
  							<option value="期中">期中</option>
  							<option value="期末">期末</option>
  						</select>
  					</td>
  					<td align="center"><input type="button" name="searchBtn" value="查询" /></td>
  				</tr>
  			</table>
  			</form>
  		</div>
  	</div>
  </body>
</html>
