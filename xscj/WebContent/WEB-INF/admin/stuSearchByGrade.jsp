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
				url:"stuSearchGradeTest.action",
				data:{
					gradeID:$("select[name=gradeID]").val()
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
			    	alert("该班级目前还没有任何学生！");
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
</style>
  </head>
  
  <body>
  <div>
  	<jsp:include page="stuServiceNavigation.jsp"></jsp:include>
  </div>
  	<div	id="accordion">
  		<h3>学生管理&nbsp;&gt;&nbsp;班级信息查询</h3>
  		<div align="center">
  			<form action="stuSearchSucByGrade.action" method="get" id="myForm">
  				<strong>请选择<span style="color:red">班级</span>：</strong>
  				<select name="gradeID">
  					<s:iterator value="grades">
  						<option value='<s:property value="bianHao" />' > <s:property value="year" />届（<s:property value="classID" />）班 </option>
  					</s:iterator>
  				</select>
  				&nbsp;&nbsp;&nbsp;<input type="button" name="searchBtn" value="查询"  />
  			</form>
  			<hr>
  			<img alt="" src="image/stuInfoSearch.jpg" width="1000" height="500" />
  		</div>
  	</div>
  </body>
</html>
