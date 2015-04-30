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

function nextBtn(){
		var $nextBtn = $("input[name=nextBtn]");
		$nextBtn.bind("click", function(){
		$.ajax(
			{
				type:"get",
				url:"scoreEnterTest.action",
				data:{
					gradeID:$("select[name=gradeID]").val(),
					xueqi:$("#xueqi2").val(),
					courseID:$("select[name=courseID]").val(),
					examType:$("#examType2").val()
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
			    	alert(d.gradeName+"没有"+d.courseName+"课程的教学安排");
			    	return;
			    }else if(d.flag == -1)
			    {
			    	alert(d.gradeName+"所有同学本次"+d.courseName+"课程的成绩已录入完毕");
			    	return;
			    }
			    else{
			    	$("#myForm").submit();
			    }
			};
		});
	}
	
	function stuBtn(){
		var $stuBtn = $("input[name=stuBtn]");
		$stuBtn.bind("click", function(){
		$.ajax(
			{
				type:"get",
				url:"scoreEnterStuTest.action",
				data:{
					stuXueHao:$("input[name=stuXueHao]").val(),
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
			    	alert("不存在这个学生或学生所在的班级这个学期还没有进行教学安排！");
			    	return;
			    }else if(d.flag == -1)
			    {
			    	alert("该学号学生此次考试的成绩已录入完毕");
			    	return;
			    }
			    else{
			    	$("#stuForm").submit();
			    }
			};
		});
	}

	$(function(){
		$("#accordion").accordion();
		nextBtn();
		stuBtn();
		
	});
	
</script>
<style type="text/css">
	body {
	font: 72.5% "Trebuchet MS", sans-serif;
	margin: 50px;
	background-color:#7ecef4;
}
.box{
	padding:8px;
}
.frame{
	height:435px;
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
    font: bold 12px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #797268; 
    padding: 6px 6px 6px 12px; 
 
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
  		<div align="left" class="frame">
  			<form action="scoreEnterSimpleStu.action" method="post" id="stuForm">
  			<table class="datalist">
  				<tr><td>学生学号：<input type="text" name="stuXueHao" />&nbsp;&nbsp;&nbsp;选择学期：<select name="xueqi" id="xueqi1"><option value="1" >1</option>
  					<option value="2" >2</option>
  					<option value="3" >3</option>
  					<option value="4" >4</option>
  					<option value="5" >5</option>
  					<option value="6" >6</option></select>&nbsp;&nbsp;&nbsp;考试类型： <select name="examType" id="examType1">
  				<option value="期中">期中</option><option value="期末">期末</option></select>&nbsp;&nbsp;&nbsp;<input type="button" name="stuBtn" value="下一步" /></td>
  				</tr>
  				</table>
  			</form>
  		 
  		 	<form action="scoreEnterForm.action" method="post" id="myForm">
  			<input type="hidden" name="flag" id="flag"/>
  		 	<table class="datalist">
  				<tr><td>选择班级：<select name="gradeID">
  					<s:iterator value="grades">
  						<option value='<s:property value="bianHao"/>'><s:property value="year" />届（<s:property value="classID" />）班</option>
  					</s:iterator>
  				</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选择学期：<select name="xueqi" id="xueqi2">
  					<option value="1" >1</option>
  					<option value="2" >2</option>
  					<option value="3" >3</option>
  					<option value="4" >4</option>
  					<option value="5" >5</option>
  					<option value="6" >6</option>
  				</select>&nbsp;&nbsp;&nbsp; 选择课程： <select name="courseID">
					<s:iterator value="courses">
						<option value='<s:property value="bianHao" />' ><s:property value="name" /></option>
					</s:iterator>
  				</select>&nbsp;&nbsp;&nbsp;考试类型： <select name="examType" id="examType2">
  						<option value="期中">期中</option>
  						<option value="期末">期末</option>
  					</select>&nbsp;&nbsp;&nbsp;<input type="button" value="下一步" name="nextBtn" /></td> </tr>
  				</table>
  			</form>
  		 
  		</div>
  	</div>
  </body>
</html>
