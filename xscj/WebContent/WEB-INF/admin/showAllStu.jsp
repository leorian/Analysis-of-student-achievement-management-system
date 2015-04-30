<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>洛天工作室</title>
    <link href="css/ui-lightness/jquery-ui-1.10.4.custom.css"
	rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript">
	$(function() {
		$("#accordion").accordion();
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
  	<jsp:include page="stuServiceNavigation.jsp"></jsp:include>
  </div>
    <div  id="accordion">
    	<h3>学生管理&nbsp;&gt;&nbsp;学生信息编辑</h3>
    	<div align="center" class="frame">
    			<table cellspacing="0" cellpadding="8" class="datalist" >  
    <tr>  
       <th>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</th>  
       <th>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</th>
       <th>班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级</th>  
       <th>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</th>  
       <th>身份证号</th>
       <th>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族</th>
       <th>政治面貌</th>
       <th>入学时间</th>
       <th>操作</th>
    </tr>  
    <s:iterator value="simpleStudents" status="st">
    	  <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" <s:if test="#st.odd">class="altrow"</s:if> >
    	  <td><s:property value="xuehao"/></td>  
          <td><s:property value="name"/></td>
          <td><s:property value="className" /></td>  
          <td><s:property value="sex"/></td>  
          <td><s:property value="idCard"/></td>        
          <td><s:property value="nation"/></td>  
          <td><s:property value="polStat"/></td>  
          <td><s:property value="schoolTime"/></td> 
          <td><a href="stuModify.action?xuehao=<s:property value="xuehao"/>"><input type="button" value="修改" /></a>&nbsp;&nbsp;<a href="stuSeeing.action?xuehao=<s:property value="xuehao"/>"><input type="button" value="查看" /></a>&nbsp;&nbsp;<a href='stuDelete.action?xuehao=<s:property value="xuehao"/>' onclick="return confirm('确定删除吗，将会删除该学生所有的成绩记录？');"><input type="button" value="删除" /></a></td>
      	  </tr>
    </s:iterator> 
    <tr>
    	<td colspan="9"> <s:url id="url_first" value="showAllStu.action">
  	<s:param name="pageNow" value="1"></s:param>
  </s:url>
     <s:url id="url_pre" value="showAllStu.action">  
         <s:param name="pageNow" value="pageNow-1"></s:param>  
     </s:url>  
  
     <s:url id="url_next" value="showAllStu.action">  
         <s:param name="pageNow" value="pageNow+1"></s:param>  
     </s:url>    
  <s:url id="url_last" value="showAllStu.action">
  	<s:param name="pageNow" value="pageTotal"></s:param>
  </s:url>
  共有【<s:property value="rowTotal"/>】条学生记录，当前第【<s:property value="pageNow"/>】页，总共【<s:property value="pageTotal"/>】页
  	<s:a href="%{url_first}"><input type="button" value="首页" /></s:a>
     <s:a href="%{url_pre}"><input type="button" value="上一页" /></s:a>  
       
     <s:iterator value="simpleStudents" status="status">  
        <s:url id="url" value="showAllStu.action">  
            <s:param name="pageNow" value="pageNow"/>  
        </s:url>  
     </s:iterator>  
  
     <s:a href="%{url_next}"><input type="button" value="下一页" /></s:a>  
     <s:a href="%{url_last}"><input type="button" value="尾页" /></s:a></td>
    </tr>
  </table>  
    	</div>
    </div>
  </body>
</html>
