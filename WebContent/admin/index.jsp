<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
request.setCharacterEncoding("UTF-8");
%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
request.setAttribute("path", basePath);  
%> 
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="images/sun.png" alt="天气" /></span>
    <b><s:property	value="#session.userName" />你好，欢迎使用信息管理系统</b>
    </div>    
        
    <div class="xline"></div>
    
    <ul class="iconlist">

    <li><img src="images/ico06.png" /><p><a href="${path }admin/findInfosByPage">查询信息</a></p></li> 
    <li><img src="images/ico02.png" /><p><a href="${path }admin/info/add.jsp">发布信息</a></p></li>
    <li><img src="images/ico03.png" /><p><a href="${path }admin/findAllCategory">信息类别管理</a></p></li>       
    
    </ul>
        
    </div>
</body>
</html>
