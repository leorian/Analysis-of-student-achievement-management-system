<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>洛天工作室</title>
    <link href="css/ui-lightness/jquery-ui-1.10.4.custom.css"
	rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript">
	$(function(){
		$("#accordion").accordion();
	});
</script>
<style type="text/css">
body {
	font: 72.5% "Trebuchet MS", sans-serif;
	margin: 50px;
	background-color:#7ecef4;
	background-image: url(image/loginBg.jpg);
}
#info{
	height:400px;
}
</style>
  </head>
  
  <body>
    <jsp:include page="stuServiceNavigation.jsp"></jsp:include>
    <div id="accordion">
    	<h3>石牌高级中学学生成绩管理系统欢迎您的使用</h3>
    	<div align="left" id="info">
    		<p><strong>公司：</strong>洛天工作室</p>
    		<p><strong>作者：</strong>谢中贵</p>
    		<p><strong>联系电话：</strong>18365073582</p>
    		<p><strong>QQ：</strong>1573623031</p>
    		<p><strong>Email：</strong>1573623031@qq.com</p>
    		<p><strong>版权说明：</strong>业余娱乐之作，不具有任何版权纠纷。</p>
    		<p><strong>系统说明：</strong>一款小型的通用的中学教务系统，可根据学校具体要求进行修改、扩展。</p>
    		<p><strong>开发目的：</strong>纪念我即将逝去的大学生活。</p>
    	</div>
    </div>
  </body>
</html>
