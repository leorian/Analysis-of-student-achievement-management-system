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
function save(){
	$save = $("input[name=save]");
	$save.bind("click", function(){
	regidcard=/^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/;
    regdate= /^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$/;
		 if($.trim( $("#name").val())=="")
		 {
		 	$(".ErrorStyle").empty();
		 	$("#nameError").html("学生姓名必填");
		 	return ;
		 }else if($.trim($("#password").val())==""){
		 	$(".ErrorStyle").empty();
		 	$("#passwordError").text("登录密码必填");
		 	return ;
		 }else if($.trim($("#idCard").val())=="")
		 {
		 	$(".ErrorStyle").empty();
		 	$("#idCardError").text("身份证号必填");
		 	return ;
		 }
		 else if(!regidcard.test($("#idCard").val())){
				$(".ErrorStyle").empty();
		 		$("#idCardError").text("身份证号不合法");
		 	return;
		 }
		 else if($.trim($("#address").val())=="")
		 {
		 	$(".ErrorStyle").empty();
		 	$("#addressError").text("必填项");
		 	return ;
		 }else if($.trim($("#schoolTime").val())=="")
		 {
		 	$(".ErrorStyle").empty();
		 	$("#schoolTimeError").text("入学时间必填");
		 	return ;
		 }
		 else if(!regdate.test($("#schoolTime").val()))
		 {
		 	$(".ErrorStyle").empty();
		 	$("#schoolTimeError").text("日期格式不合法yyyy-MM-dd");
		 	return;
		 }
		 else
		 {
		 	$("#form1").submit();
		 }
		
	});
}
	        function btn(){
	            var $btn = $("input.randomPass");
            $btn.bind("click",function(){
                $.ajax({
                    type:"post",
                    url:"execRandomPass.action",
                  	dataType:"json",
                    success:function(data){
                                    var d = eval("("+data+")");                               
 	                      		  /*  $("#password").attr("value",""+d.random+""); */
 	                      		  $("#password").val(d.random);
                     },
 	                    error:function(){
 	                        alert("系统异常，请稍后重试！");
 	                    }
 	                });
 	            });
 	        }
	$(function() {
		$("#accordion").accordion();
		var pickerOpts = {
         dateFormat: "yy-mm-dd"
        };
		$("#schoolTime").datepicker(pickerOpts);
		btn();
		save();
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
	text-align:left;
	padding-top:4px; padding-bottom:4px;
	padding-left:10px; padding-right:10px;
}
.datalist tr.altrow{
	background-color:#c7e5ff;	/* 隔行变色 */
}
.flag{
	color:red;
}
.ErrorStyle{
	color:red;
	padding-left:6px;
}
</style>

</head>

<body>
<div>
  	<jsp:include page="stuServiceNavigation.jsp"></jsp:include>
  </div>
	<div id="accordion">
		<h3>学生管理&nbsp;&gt;&nbsp;学生基本信息修改</h3>
		<div align="center" >
			<form id="form1" name="form1" method="post" action="stuModifySuc.action" >
			<input type="hidden" name="xuehao" value='<s:property value="xuehao" />' />
			<%-- <input type="hidden" name="student.classID" value='<s:property value="student.classID" />' /> --%>
				<table class="datalist">
					<tr>
						<th width="80" align="right" class="labelString"><strong>学生姓名</strong></th>
						<td width="430"><label for="name"></label> <input type="text"
							name="student.name" id="name" value='<s:property value="student.name" />' /><span class="flag">*</span><span id="nameError" class="ErrorStyle"></span></td>
					</tr>
					
					<tr>
						<th align="right" class="labelString"><strong>登录密码</strong></th>
						<td><label for="password"></label> <input type="text"
							name="student.password" id="password" value='<s:property value="student.password" />' /><span class="flag">*</span> <input type="button"
							name="randomPass" id="randomPass" class="randomPass" value="产生随机密码" /><span id="passwordError" class="ErrorStyle"></span></td>	 
					</tr>
					
					<tr>
						<th align="right" class="labelString"><strong>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</strong></th>
						<td><label for="sex"></label> <select name="student.sex" id="sex">
								<option value="男" <s:if test='student.sex == "男"'>selected="selected"</s:if> >男</option>
								<option value="女" <s:if test='student.sex == "女"'>selected="selected"</s:if> >女</option>
						</select><span class="flag">*</span></td>
					</tr>
					
					<tr>
						<th align="right" class="labelString"><strong>身份证号</strong></th>
						<td><label for="idCard"></label> <input type="text"
							name="student.idCard" id="idCard" value='<s:property value="student.idCard" />' /><span class="flag">*</span><span id="idCardError" class="ErrorStyle"></span></td>
					</tr>
					
					<tr>
						<th align="right" class="labelString"><strong>家庭住址</strong></th>
						<td><label for="address"></label> <input name="student.address"
							type="text" id="address" size="50" value='<s:property value="student.address" />' /><span class="flag">*</span><span id="addressError" class="ErrorStyle"></span></td>
					</tr>
					
					<tr>
						<th width="80" align="right" class="labelString"><strong>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族</strong></th>
						<td><label for="nation"></label> <select name="student.nation"
							id="nation">
								<option value="汉族" <s:if test="student.nation=='汉族'">selected="selected"</s:if> >汉族</option>
								<option value="少数民族" <s:if test="student.nation=='少数民族'">selected="selected"</s:if> >少数民族</option>
						</select><span class="flag">*</span></td>
					</tr>
					<tr><th  width="80" align="right" class="labelString">
							<strong>分配班级</strong>
						</th>
						<td>
							<select name="student.classID">
								<s:iterator value="grades">
									<option value='<s:property value="bianHao"/>' <s:if test="student.classID==bianHao">selected="selected"</s:if> ><s:property value="year"/>届（<s:property value="classID" />）班</option>
								</s:iterator>
							</select><span class="flag">*</span>
						</td>
						
					</tr>
					<tr>
						<th align="right" class="labelString"><strong>政治面貌</strong></th>
						<td><label for="polStat"></label> <select name="student.polStat"
							id="polStat">
								<option value="中共党员" <s:if test="student.polStat=='中共党员'">selected="selected"</s:if> >中共党员</option>
								<option value="中共预备党员" <s:if test="student.polStat=='中共预备党员'">selected="selected"</s:if> >中共预备党员</option>
								<option value="共青团员" <s:if test="student.polStat=='共青团员'">selected="selected"</s:if> >共青团员</option>
								<option value="无党派人士" <s:if test="student.polStat=='无党派人士'">selected="selected"</s:if> >无党派人士</option>
								<option value="普通公民" <s:if test="student.polStat=='普通公民'">selected="selected"</s:if> >普通公民</option>
								<option value="港澳同胞" <s:if test="student.polStat=='港澳同胞'">selected="selected"</s:if>  >港澳同胞</option>
								<option value="其它" <s:if test="student.polStat=='其它'">selected="selected"</s:if> >其它</option>
						</select><span class="flag">*</span></td>
					</tr>
					<tr>
							<th align="right" class="labelString"><strong>入学时间</strong></th>
						<td><label for="schoolTime"></label> <input type="text"
							name="student.schoolTime" id="schoolTime" value='<s:property value="student.schoolTime" />' /><span class="flag">*</span><span id="schoolTimeError" class="ErrorStyle"></span></td>
					</tr>
					<tr>
						<th align="right" class="labelString"><strong>户口类别</strong></th>
						<td><label for="houseHold"></label> <select name="student.houseHold"
							id="houseHold">
								<option value="农村" <s:if test="student.houseHold=='农村'">selected="selected"</s:if> >农村</option>
								<option value="城市" <s:if test="student.houseHold=='城市'">selected="selected"</s:if> >城市</option>
						</select><span class="flag">*</span></td>
					</tr>
					<tr>
						<th align="right" class="labelString"><strong>就读方式</strong></th>
						<td><label for="schoolMethod"></label> <select
							name="student.schoolMethod" id="schoolMethod">
								<option value="寄宿生" <s:if test="student.schoolMethod=='寄宿生'">selected="selected"</s:if> >寄宿生</option>
								<option value="走读生" <s:if test="student.schoolMethod=='走读生'">selected="selected"</s:if> >走读生</option>
						</select><span class="flag">*</span></td>
						 
					</tr>
					<tr>
						<td colspan="2" style="text-align:center"><input type="button"
							name="save" id="save" value="修改" /> &nbsp;&nbsp; <input
							type="reset" name="cancel" id="cancel" value="取消" /></td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
</body>
</html>
