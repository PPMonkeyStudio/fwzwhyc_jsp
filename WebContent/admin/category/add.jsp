<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
request.setAttribute("path", basePath);  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加信息类别</title>
<link href="${path }admin/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="${path }admin/findAllCategory">信息类别列表</a></li>
    </ul>
    </div>    
    <div class="formbody">    
    <div class="formtitle"><span>添加信息类别</span></div>
    <form action="${path }admin/addCategory" name="ff" method="post" onsubmit="return checkValue()">     
    <ul class="forminfo">
   
    <li><label style="width:120px;">类别id（<font style="color:Red;font-weight:bolder;">*</font>）：</label><input id="cid" name="cid" type="text" class="dfinput" /><i>类别id为纯数字</i></li>
    <li><label style="width:120px;">类别名称（<font style="color:Red;font-weight:bolder;">*</font>）：</label><input id="cname" name="cname" type="text" class="dfinput" /><i>类别名称</i></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认添加"/>
    &nbsp;&nbsp;<input name="" type="button" onclick="goback();"class="btn" value="返回列表"/></li>
    
    </ul>
    </form>  
    </div>
    <script>
    function goback(){
    	window.location.href="${path }admin/findAllCategory";    	
    }

	function checkValue() {		
		var str = document.getElementById("cid").value;
		if (str.length < 1) {
			alert("请输入类别id");
			document.getElementById("cid").focus();
			return false;
		}		
		if (isNaN(str)) {
	        alert("类别id应为数字");
	        document.getElementById("cid").value = "";
	        document.getElementById("cid").focus();
	        return false;
        }
		str = document.getElementById("cname").value;
		if (str.length < 1) {
			alert("请输入类别名称");
			document.getElementById("cname").focus();
			return false;
		}
		if (str.length > 10) {
			alert("类别名称长度应小于等于10");
			document.getElementById("cname").focus();
			return false;
		}
/* 		str = document.getElementById("typeId").value;
		if (str == 0) {
			alert("请选择新闻类别");
			return false;
		} */
		
		return true;
	}
</script>
</body>
</html>
