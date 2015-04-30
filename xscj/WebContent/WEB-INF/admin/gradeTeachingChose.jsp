<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>洛天工作室</title>
<link href="css/ui-lightness/jquery-ui-1.10.4.custom.css"
	rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript">
	function device(){
		$device = $("input[name=device]");
		$device.bind("click",function(){
		var form1 = $(this).parent();
		form1.attr("action","teachingArrangeForm.action");
		 form1.submit();
		});
	}
	function seeing(){
		$seesing = $("input[name=seeing]");
		$seesing.bind("click", function(){
			var form1 = $(this).parent();
			form1.attr("action","teachingArrangeSee.action");
			form1.submit();
		});
	}
	function modify(){
		$modify = $("input[name=modify]");
		$modify.bind("click", function(){
			var form1 = $(this).parent();
			form1.attr("action","teachingArrangeModify.action");
			form1.submit();
		});
	}
	$(function() {
		$("#accordion").accordion();
		device();
		seeing();
		modify();
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
 #accordion{
 	height:500px;
 }
</style>
</head>

<body>
<div>
  	<jsp:include page="gradeServiceNavigation.jsp"></jsp:include>
  </div>
	<s:actionerror/>
	<div id="accordion">
		<h3>班级管理&nbsp;&gt;&nbsp;班级教学安排</h3>
		<div class="frame">
		
		<s:if test="#session['teachingArrangeStatus']">
			<div style="color:red">
			相关班级相关学期的教学安排已完成，没有其它课程可供选择
			</div>
		</s:if>
		<s:set name="teachingArrangeStatus" value="false" scope="session">
		</s:set>
		<s:if test="#session['teachingArrangeFlag']">
			<div style="color:red">
				没有相关班级相关学期的教学安排计划
			</div>
		</s:if>
		<s:set name="teachingArrangeFlag" value="false" scope="session"></s:set>
			 <table class="datalist">
			 	<tr>
			 		<th align="center"><strong>有效班级</strong></th>
			 		<th align="center"><strong>学期、操作</strong></th>
			 	</tr>
			 	<s:iterator value="grades">
			 		<tr>		
			 			<td> <s:property value="year"/>届（<s:property value="classID"/>）班</td>
			 			<td>
			 			<s:form action="teachingArrangeForm.action" id="form1"  theme="simple" method="get">
			 				<input type="hidden" value='<s:property value="bianHao"/>' name="gradeChose" />
			 				<select name="xueqiChose">
			 					<option>1</option>
			 					<option>2</option>
			 					<option>3</option>
			 					<option>4</option>
			 					<option>5</option>
			 					<option>6</option>
			 				</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 				<input type="button" name="device"  value="设置" />
			 				<input type="button" name= "seeing" value="查看" />
			 				<input type="button" name= "modify" value="删除" />
			 				</s:form></td>
			 		</tr>
			 	</s:iterator>
			 </table>
		</div>
	</div>
</body>
</html>
