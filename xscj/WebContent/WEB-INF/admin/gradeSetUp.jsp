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
function save(){
	    digit = /^[0-9]*$/;
		$save=$("input[name=save]");
		$save.bind("click", function(){
			if($.trim($("#bianHao").val())=="")
			{
				$(".inputStyle").css({border:""});
				$("#bianHao").css({border:"solid 2px #f00"});
			}else if($.trim($("#year").val())=="")
			{
				$(".inputStyle").css({border:""});
				$("#year").css({border:"solid 2px #f00"});
				 
			}
			else if(!digit.test($("#year").val()))
			{
				$(".inputStyle").css({border:""});
				$("#year").css({border:"solid 2px #f00"});
				$(".ErrorStyle").empty();
				$("#yearError").text("只能是数字");
				 
			}
			else if($.trim($("#classID").val())=="")
			{
				$(".inputStyle").css({border:""});
				$("#classID").css({border:"solid 2px #f00"});
				 
			}
			else if(!digit.test($("#classID").val()))
			{
				$(".inputStyle").css({border:""});
				$("#classID").css({border:"solid 2px #f00"});
				$(".ErrorStyle").empty();
				$("#classIDError").text("只能是数字");
				 
			}
			else
			{
				$("#form1").submit();
			}
		
		});
	}
function autoExec(){
		var $autoExec = $("input.autoExec");
		$autoExec.bind("click", function(){
			$.ajax(
			{
				type:"post",
				url:"autoExecGradeBh.action",
				dataType:"json",
				success : function(data) {
					var d = eval("(" + data + ")");
					$("#bianHao").attr("value", "" + d.bianHao + "");
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}
			}
			);
		});
	}
	$(function() {
		$("#accordion").accordion();
		$(".button").button();
		autoExec();
		save();
		$("#dialog").dialog({
			autoOpen : false,
			width : 400,
			buttons : [ {
				text : "关闭",
				click : function() {
					$(this).dialog("close");
				}
			}]
		});

		// Link to open the dialog
		$("#dialog-link").click(function(event) {
			$("#dialog").dialog("open");
			event.preventDefault();
		});

		// Hover states on the static widgets
		$("#dialog-link").hover(function() {
			$(this).addClass("ui-state-hover");
		}, function() {
			$(this).removeClass("ui-state-hover");
		});

	});
</script>
<style type="text/css">
body {
	font: 72.5% "Trebuchet MS", sans-serif;
	margin: 50px;
	background-color:#7ecef4;
}
#dialog-link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog-link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
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
a{
	text-decoration:none;
}
/* a:link{
	color:green;
}
a:active{
	color:red;
}
a:visited{
	color:black;
}
a:hover{
	color:blue;
} */
/* .frame{
	height:435px;
} */
#accordion{
	height:500px;
}
.ErrorStyle{
	color:red;
	padding-left:6px;
}
</style>

</head>

<body>
<div>
  	<jsp:include page="gradeServiceNavigation.jsp"></jsp:include>
  </div>
	<div id="accordion">
		<h3>班级管理&nbsp;&gt;&nbsp;班级信息添加&nbsp;&nbsp;
			<span style="color:#000;">(目前已有<s:property value="validCount" />条有效班级信息)
			</span>
		</h3>
		<div class="frame">
			目前存在如下班级:
			<br><br>
	 
			<table class="datalist">
			<s:iterator value="validGrades" status="st">
		 <s:if test="#st.index % 8 == 0">
		 	<tr>
		 </s:if><td>
				<a href="#">
					<s:property value="year" />
					届（
					<s:property value="classID" />
					）班
				</a></td>
				  <s:if test="#st.index % 8 == 7">
		 	<tr></tr>
		 </s:if>
			</s:iterator>
			</table>
	 
			<br>
			<br>
			<a href="#" id="dialog-link" class="ui-state-default ui-corner-all"><span
				class="ui-icon ui-icon-newwin"></span>选择删除</a> <br />
				<p style="color:red">（说明：只能删除没有关联学生信息的班级信息）</p>
				<br>
				<hr>
				<div>
			<form id="form1" name="form1" method="post" action="gradeSetUpRegister.action">
				<table class="datalist">
					<tr>
						<th align="right"><strong>班级编号</strong></th>
						<td><input type="text"
							name="grade.bianHao" id="bianHao" readonly="readonly" class="inputStyle" /> <input type="button"
							name="autoExec" id="autoExec" class="autoExec" value="自动激活" /></td>
					</tr>
					<tr>
						<th align="right"><strong>年级、届</strong></th>
						<td><input type="text"
							name="grade.year" id="year" value='<s:property value="maxYear"/>' class="inputStyle" /><span id="yearError" class="ErrorStyle"></span></td>
					</tr>
					<tr>
						<th align="right"><strong>班级序号</strong></th>
						<td><input type="text"
							name="grade.classID" id="classID" value='<s:property value="classesCount"/>' class="inputStyle" /><span id="classIDError" class="ErrorStyle"></span></td>
					</tr>
					<tr>
						<th align="right"><strong>班 &nbsp;主&nbsp; 任</strong></th>
						<td><select name="grade.adviser" id="adviser"
							id="select">
							<s:iterator value="subTeachers">
								<option value="<s:property value="bianHao" />"> <s:property value="name" /> </option>
							</s:iterator>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="button"
							name="save" id="save" value="注册" /> <input type="reset"
							name="button3" id="button3" value="取消" /></td>
					</tr>
				</table>
			</form>
		</div>
		</div>
	</div>

	<div id="dialog" title="选择删除">
		<form id="form1" name="form1" method="post" action="gradeDelete.action">
			<s:iterator value="gradesEnableDel">
				<input type="checkbox" name="gradeDel"
					value="<s:property value="bianHao" />" />&nbsp;<s:property
					value="year" />届（<s:property value="classID" />）班
				<br />
				<hr />
			</s:iterator>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="删除"  />
		</form>
	</div>

</body>
</html>
