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
	regidcard=/^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/;
    regdate= /^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$/;
    regphone= /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    regtel = /^((0\d{2,3})-?)?\d{7,8}$/; 
		$save = $("input[name=save]");
		$save.bind("click",function(){
			if($.trim($("#bianHao").val())==""){
				$(".ErrorStyle").empty();
				$("#bianHaoError").text("教师编号必填");
			}else if($.trim($("#name").val())=="")
			{
				$(".ErrorStyle").empty();
				$("#nameError").text("教师姓名必填");
			}else if($.trim($("#idCard").val())=="")
			{
				$(".ErrorStyle").empty();
				$("#idCardError").text("身份证号必填");
			}
			else if(!regidcard.test($("#idCard").val())){
				$(".ErrorStyle").empty();
		 		$("#idCardError").text("身份证号不合法");
		 	return;
		 }
			else if($.trim($("#jobTime").val())=="")
			{
				$(".ErrorStyle").empty();
				$("#jobTimeError").text("入职时间必填");
			}
			 else if(!regdate.test($("#jobTime").val()))
		 {
		 	$(".ErrorStyle").empty();
		 	$("#jobTimeError").text("日期格式不合法yyyy-MM-dd");
		 	return;
		 }
			else if($.trim($("#password").val())=="")
			{
				$(".ErrorStyle").empty();
				$("#passwordError").text("登录密码必填");
			}else if($.trim($("#address").val())=="")
			{
				$(".ErrorStyle").empty();
				$("#addressError").text("必填");
			}else if($.trim($("#phone").val())=="")
			{
				$(".ErrorStyle").empty();
				$("#phoneError").text("联系电话必填");
			}
			else if(!regphone.test($("#phone").val()) && !regtel.test($("#phone").val()))
			{
				$(".ErrorStyle").empty();
				$("#phoneError").text("联系电话不合法");
			}
			else if($.trim($("#graIns").val())=="")
			{
				$(".ErrorStyle").empty();
				$("#graInsError").text("毕业院校必填");
			}
			else
			{
				$("#form1").submit();
			}
		});
	}
	function btn() {
		var $btn = $("input.randomPass");
		$btn.bind("click", function() {
			$.ajax({
				type : "post",
				url : "execRandomPass.action",
				dataType : "json",
				success : function(data) {
					var d = eval("(" + data + ")");
					/* $("#password").attr("value", "" + d.random + ""); */
					$("#password").val(d.random);
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}
			});
		});
	}
	$(function() {
		$("#accordion").accordion();
		$("#jobTime").datepicker();
		btn(); //产生随机密码
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
  	<jsp:include page="teacherServiceNavigation.jsp"></jsp:include>
  </div>
	<div id="accordion">
		<h3>教师管理&nbsp;&gt;&nbsp;教师基本信息修改</h3>
		<div align="center">
			<form id="form1" name="form1" method="post" action="teacherModifySuc.action">
		<%-- 	<input type="hidden" name="teacher.courseID"  value='<s:property value="teacher.courseID" />' >  --%>
			<table class="datalist">
				<tr>
					<th width="75"><strong>教师编号</strong></th>
					<td width="419"><input
						type="text" name="teacher.bianHao" id="bianHao" readonly="readonly" value='<s:property value="teacher.bianHao" />' /><span class="flag">*</span><span id="bianHaoError" class="ErrorStyle"></span></td>
				</tr>
				<tr>
					<th><strong>教师姓名</strong></th>
					<td><input type="text" name="teacher.name"
						id="name" value='<s:property value="teacher.name" />' /><span class="flag">*</span><span id="nameError" class="ErrorStyle"></span></td>
				</tr>
				<tr>
					<th><strong>身份证号</strong></th>
					<td><input type="text"
						name="teacher.idCard" id="idCard" value='<s:property value="teacher.idCard" />' /><span class="flag">*</span><span id="idCardError" class="ErrorStyle"></span></td>
				</tr>
				<tr>
					<th><strong>入职时间</strong></th>
					<td><input type="text"
						name="teacher.jobTime" id="jobTime" value='<s:property value="teacher.jobTime"/>'  /><span class="flag">*</span><span id="jobTimeError" class="ErrorStyle"></span></td>
				</tr>
				<tr>
					<th><strong>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族</strong></th>
					<td><select name="teacher.nation"
						id="nation">
							<option value="汉族" <s:if test="teacher.nation=='汉族'">selected="selected"</s:if> >汉族</option>
							<option value="少数民族" <s:if test="teacher.nation=='少数民族'">selected="selected"</s:if> >少数民族</option>
					</select><span class="flag">*</span></td>
				</tr>
				<tr>
					<th><strong>最高学历</strong></th>
					<td><select name="teacher.eduBg"
						id="eduBg">
							<option value="专科" <s:if test="teacher.eduBg == '专科' ">selected="selected"</s:if> >专科</option>
							<option value="本科" <s:if test="teacher.eduBg == '本科' ">selected="selected"</s:if> >本科</option>
							<option value="研究生" <s:if test="teacher.eduBg == '研究生' ">selected="selected"</s:if> >研究生</option>
							<option value="博士生" <s:if test="teacher.eduBg == '博士生' ">selected="selected"</s:if> >博士生</option>
					</select><span class="flag">*</span></td>
				</tr>
				<tr>
					<th width="75"><strong>登录密码</strong></th>
					<td width="327"><input
						type="text" name="teacher.password" id="password" value='<s:property value="teacher.password" />' /><span class="flag">*</span><input type="button"  class="randomPass" value="产生随机密码" />
						<span id="passwordError" class="ErrorStyle"></span>
						</td>
				</tr>
				<tr>
					<th  width="75"><strong>所带课程</strong></th>
					<td>
						<select name="teacher.courseID">
							<s:iterator value="courses">
								<option value='<s:property value="bianHao"/>' <s:if test="teacher.courseID==bianHao">selected="selected"</s:if> ><s:property value="name" /></option>
							</s:iterator>
						</select><span class="flag">*</span>
					</td>
				</tr>
				<tr>
					<th><strong>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</strong></th>
					<td><select name="teacher.sex" id="sex">
							<option value="男" <s:if test='teacher.sex == "男" '>selected="selected"</s:if> >男</option>
							<option value="女" <s:if test='teacher.sex == "女" '>selected="selected"</s:if> >女</option>
					</select><span class="flag">*</span></td>
				</tr>
				<tr>
					<th><strong>家庭地址</strong></th>
					<td><input name="teacher.address"
						type="text" id="address" size="50" value='<s:property value="teacher.address" />'  /><span class="flag">*</span><span id="addressError" class="ErrorStyle"></span></td>
				</tr>
				<tr>
					<th><strong>联系电话</strong></th>
					<td><input type="text"
						name="teacher.phone" id="phone" value='<s:property value="teacher.phone" />'  /><span class="flag">*</span><span id="phoneError" class="ErrorStyle"></span></td>
				</tr>
				<tr>
					<th><strong>政治面貌</strong></th>
					<td><label for="polStat"></label> <select name="teacher.polStat"
						id="polStat">
						<option value="中共党员" <s:if test="teacher.polStat=='中共党员'">selected="selected"</s:if> >中共党员</option>
								<option value="中共预备党员" <s:if test="teacher.polStat=='中共预备党员'">selected="selected"</s:if> >中共预备党员</option>
								<option value="共青团员" <s:if test="teacher.polStat=='共青团员'">selected="selected"</s:if> >共青团员</option>
								<option value="无党派人士" <s:if test="teacherpolStat=='无党派人士'">selected="selected"</s:if> >无党派人士</option>
								<option value="普通公民" <s:if test="teacher.polStat=='普通公民'">selected="selected"</s:if> >普通公民</option>
								<option value="港澳同胞" <s:if test="teacher.polStat=='港澳同胞'">selected="selected"</s:if>  >港澳同胞</option>
								<option value="其它" <s:if test="teacher.polStat=='其它'">selected="selected"</s:if> >其它</option>
					</select><span class="flag">*</span></td>
				</tr>
				<tr>
					<th><strong>毕业院校</strong></th>
					<td><input type="text"
						name="teacher.graIns" id="graIns" value='<s:property value="teacher.graIns" />' /><span class="flag">*</span><span id="graInsError" class="ErrorStyle"></span></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center"><input type="button"
						name="save" id="save" value="修改" /> &nbsp;&nbsp; <input type="reset"
						name="cancel" id="cancel" value="取消" /></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>
