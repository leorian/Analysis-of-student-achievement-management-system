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
				type:"get",
				url:"teacherSearchTest.action",
				data:{
					teacherBianHao:$("input[name=teacherBianHao]").val()
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
			    	alert("没有此教师编号对应的教师信息，请重新核对后输入！");
			    	window.location.href="teacherSearch.action";
			    	return;
			    }
			    else{
			    	$("#myForm").submit();
			    }
			};
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
.frame{
	height:435px;
}
a:link{
	text-decoration:none;
}
</style>
  </head>
  
  <body>
  <div>
  	<jsp:include page="teacherServiceNavigation.jsp"></jsp:include>
  </div>
  	<div	id="accordion">
  		<h3>教师管理&nbsp;&gt;&nbsp;教师信息查询</h3>
  		<div align="center" class="frame">
  			<form action="teacherSearchSuc.action" method="get" id="myForm">
  				<strong>请输入完整的<span style="color:red">教师编号</span>：</strong><input type="text" name="teacherBianHao" value='<s:property value="teacherBianHao" />' />&nbsp;&nbsp;&nbsp;<input type="button" name="searchBtn" value="查询"  />
  			</form>
  			<hr>
  			<table cellspacing="0" cellpadding="8" class="datalist" >  
    <tr>  
       <th>教师编号</th>  
       <th>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</th>  
       <th>科&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目</th>
       <th>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</th>  
       <th>身份证号</th>
       <th>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族</th>
       <th>政治面貌</th>
       <th>入职时间</th>
             
       <th>操作</th>
    </tr>  
       <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" >  
          <td><s:property value="teacher.bianHao"/></td>  
          <td><s:property value="teacher.name"/></td>
          <td><s:property value="teacher.courseName"/></td>  
          <td><s:property value="teacher.sex"/></td>  
          <td><s:property value="teacher.idCard"/></td>        
          <td><s:property value="teacher.nation"/></td>  
          <td><s:property value="teacher.polStat"/></td>  
          <td><s:property value="teacher.jobTime"/></td> 
          <td><a href="teacherModify.action?teacherBianHao=<s:property value="teacher.bianHao"/>"><input type="button" value="修改" /></a>&nbsp;&nbsp;<a href="teacherSeeing.action?teacherBianHao=<s:property value="teacher.bianHao"/>"><input type="button" value="查看" /></a>&nbsp;&nbsp;<a href='teacherDelete.action?bianHao=<s:property value="teacher.bianHao"/>' onclick="return confirm('确定删除吗');" ><input type="button" value="删除" /></a></td>
       </tr>
    </table>
  		</div>
  	</div>
  </body>
</html>
