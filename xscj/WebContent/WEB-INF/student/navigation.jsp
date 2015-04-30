<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
	<Style>
		.topInfo{
			text-align:right;
		}
		.popInfo{
			text-align:left;
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
	.ErrorStyle{
		color:red;
		border-left:6px;
	}
	</Style>
	<script type="text/javascript">
		$(function (){
			$("#btn").button();
			$("#exit").button();
				$( "#dialog" ).dialog({
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
									url:"passModify.action",
									dataType:"json",
									data:{
										xuehao:$("input[name=xuehao]").val(),
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
		$( "#dialog-link" ).click(function( event ) {
			$( "#dialog" ).dialog( "open" );
			event.preventDefault();
		});
			
		});
	</script>
</head>
<s:if test="#session['userName'] != null">
  <div class="topInfo">
   <p><a href="#" id="dialog-link" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-newwin"></span>修改密码</a>
  	<a href="stuMain.action"><button id="btn">主界面</button></a>
  	<a href="exitSystem.action"><button id="exit">退出系统</button></a>
   登录用户名：<s:property value="#session['userName']" />&nbsp;&nbsp;&nbsp;
  	姓名：<s:property value="#session['userAliaName']" />&nbsp;&nbsp;&nbsp;
  	用户身份：<s:property value="#session['userRole']" /> &nbsp;&nbsp;&nbsp;</p>
  </div>
</s:if>
<div id="dialog" title="密码修改" class="popInfo">
	<form action="" method="post" id="form1">
		<input type="hidden" name="xuehao" value='<s:property value="#session['userName']" />'/>
		<p><strong>原始密码：</strong><input type="password" name="oldPass" /><span id="oldPassError" class="ErrorStyle"></span></p>
		<p><strong>新 &nbsp;密 &nbsp;码：</strong><input type="password" name="newPass" /><span id="newPassError" class="ErrorStyle"></span></p>
		<p><strong>确认密码：</strong><input type="password" name="rePass" /><span id="rePassError" class="ErrorStyle"></span></p>
	</form>
</div>