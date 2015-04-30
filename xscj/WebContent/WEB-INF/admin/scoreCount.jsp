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
	function btn1(){
		var $btn1 = $("input[name=btn1]");
		$btn1.bind("click", function(){
		$.ajax(
			{
				type:"get",
				url:"scoreCountTest.action",
				data:{
					gradeID:$("#gradeID1").val(),
					xueqi:$("#xueqi1").val(),
					examType:$("#examType1").val()
				},
				dataType:"json",
				success :  login_Result,
				error : function() {
					alert("系统异常，请稍后重试！");
				}
			}
			 
			);
			function login_Result(data){
			  var d = eval("("+data+")");
			    if(d.flag == 0)
			    {
			    	alert("班级成绩录入不完整，暂时不能进行成绩统计工作");
			    	return;
			    } 
			    else{
			    	$("#myForm1").submit();
			    }
			};
		});
	}
	$(function(){
		$("#accordion").accordion();
		btn1();
	 
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
.frame{
	height:435;
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
	border-top: 1px solid #C1DAD7; 
    border-left: 1px solid #C1DAD7; 
	border-right: 1px solid #C1DAD7; 
    border-bottom: 1px solid #C1DAD7; 
    padding: 6px 6px 6px 12px; 
    font: bold 12px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #797268; 
}
.datalist tr.altrow{
	background-color:#c7e5ff;	/* 隔行变色 */
}
 
</style>
  </head>
  <body>
 <jsp:include page="scoreServiceNavigation.jsp"></jsp:include>
  <div id="accordion">
  	<h3>成绩管理——成绩统计</h3>
  	<div class="frame">
  	<form action="scoreCountSuc.action" method="post" id="myForm1">
  	<table class="datalist">
  		<tr><td>班级：<select name="gradeID" id="gradeID1">
  			<s:iterator value="grades">
  				<option value='<s:property value="bianHao" />' > <s:property value="year" />届（<s:property value="classID" />）班 </option>
  			</s:iterator>
  		</select>&nbsp;&nbsp;&nbsp;学期：<select name="xueqi" id="xueqi1">
  		  			<option value="1">1</option>
  		  			<option value="2">2</option>
  		  			<option value="3">3</option>
  		  			<option value="4">4</option>
  		  			<option value="5">5</option>
  		  			<option value="6">6</option>
  		  		</select>&nbsp;&nbsp;&nbsp;类型：<select name="examType" id="examType1">
  		  			<option value="期中">期中</option>
  		  			<option value="期末">期末</option>
  		  		</select>&nbsp;&nbsp;&nbsp;<input type="button" name="btn1" value="查询-统计" /></td>
  		  		</tr>
  	</table>
  	</form>
  	
  	<form action="scoreCountNianJi.action" method="post" id="myForm2">
  		<table class="datalist">
  			<tr>
  				<td>
  					年级（届）：<select name="nianJi" id="nianJiId">
  						<s:iterator value="years">
  							<option value="<s:property />" ><s:property /> 届</option>
  						</s:iterator>
  					</select>&nbsp;&nbsp;&nbsp;学期：<select name="xueqi" id="xueQiId">
  		  			<option value="1">1</option>
  		  			<option value="2">2</option>
  		  			<option value="3">3</option>
  		  			<option value="4">4</option>
  		  			<option value="5">5</option>
  		  			<option value="6">6</option>
  		  		</select>&nbsp;&nbsp;&nbsp;类型：<select name="examType" id="examTypeId">
  		  			<option value="期中">期中</option>
  		  			<option value="期末">期末</option>
  		  		</select>&nbsp;&nbsp;&nbsp;<input type="submit" name="btn2" value="查询-统计" />
  				</td>
  			</tr>
  		</table>
  	</form>
  	</div>
  </div>
  </body>
</html>
