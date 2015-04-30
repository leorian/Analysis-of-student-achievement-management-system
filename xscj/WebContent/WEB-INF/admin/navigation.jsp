<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<script type="text/javascript">
	$(function() {
		$("#copy").button();
		$("#exit").button();
		$( "#dialog2" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "修改",
					click: function() {
						if($("input[name=oldPass]").val()=="")
						{
							$(".ErrorStyle").empty();
							$("#oldPassError").text("旧密码不能为空");
						}
						else if($("input[name=newPass]").val()=="")
						{
							$(".ErrorStyle").empty();
							$("#newPassError").text("新密码不能为空");
						}
						else if($("input[name=newPass]").val()!=$("input[name=rePass]").val())
						{
							$(".ErrorStyle").empty();
							$("#rePassError").text("两次密码输入不匹配");
						}
						else{
								$.ajax(
								{
									type:"post",
									url:"adminPassModify.action",
									dataType:"json",
									data:{
										adminName:$("input[name=adminName]").val(),
										oldPass:$("input[name=oldPass]").val(),
										newPass:$("input[name=newPass]").val(),
										rePass:$("input[name=rePass]").val()
									},
									success : function(data) {
										var d = eval("(" + data + ")");
										if(d.flag)
										{
											alert("密码修改成功,将会跳转到登录页面请用新密码重新登录");
											 window.location.href="index.jsp";
										}
										else
										{
											$(".ErrorStyle").empty();
											$("#oldPassError").text("旧密码输入错误");
										}
										 
									},
									error : function() {
										alert("系统异常，请稍后重试！");
									}
								}
								);
						}
					}
				},
				{
					text: "取消",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});

		// Link to open the dialog
		$( "#dialog2-link" ).click(function( event ) {
			$( "#dialog2" ).dialog( "open" );
			event.preventDefault();
		});
	});
</script>
	<Style>
		.popInfo{
			text-align:left;
		}
			#dialog2-link {
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	#dialog2-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	.ErrorStyle{
		color:red;
		border-left:6px;
	}
	</Style>
</head>
<body>
	<s:if test="#session['userName'] != null">
		<div align="right">
			
			<a href="#" id="dialog2-link" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-newwin"></span>修改密码</a>
			<a href="adminMain.action"><button id="copy">版权信息</button></a><a
				href="exitSystem.action"><button id="exit">退出系统</button></a>&nbsp;&nbsp;登录用户名：
			<s:property value="#session['userName']" />
			用户身份：
			<s:property value="#session['userRole']" />
			&nbsp;&nbsp;
		</div>
	</s:if>
	<br>
	<div align="center">
		<a href="stuSearch.action"><button class="button">
				<img alt="" src="image/stu.jpg">
			</button></a><a href="teacherSearch.action"><button class="button">
				<img alt="" src="image/teacher.jpg">
			</button></a><a href="courseForm.action"><button class="button">
				<img alt="" src="image/course.jpg">
			</button></a><a href="gradeSetUp.action"><button class="button">
				<img alt="" src="image/grade.jpg">
			</button></a><a href="scoreEnterChose.action"><button class="button">
				<img alt="" src="image/score.jpg">
			</button></a>
	</div>
	<div id="dialog2" title="密码修改" class="popInfo">
	<form action="" method="post" id="form1">
		<input type="hidden" name="adminName" value='<s:property value="#session['userName']" />'/>
		<p><strong>原始密码：</strong><input type="password" name="oldPass" /><span id="oldPassError" class="ErrorStyle"></span></p>
		<p><strong>新 &nbsp;密 &nbsp;码：</strong><input type="password" name="newPass" /><span id="newPassError" class="ErrorStyle"></span></p>
		<p><strong>确认密码：</strong><input type="password" name="rePass" /><span id="rePassError" class="ErrorStyle"></span></p>
	</form>
	</div>
</body>
</html>
