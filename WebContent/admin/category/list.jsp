<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
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
<title></title>
<link href="${path }admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }admin/js/jquery.js"></script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">信息类别列表</a></li>
    </ul>
    </div>    
    <div class="rightinfo">    
    <div class="tools">    
    	<ul class="toolbar">
        <li class="click"><a href="${path }admin/category/add.jsp"><span><img src="images/t01.png" /></span>添加信息类别</a></li>
        </ul>
       </div>
   		 <table style="width:700px;" class="tablelist">
	    	<thead>
	    	<tr>
	        <th style="width:160px;">信息类别编号</th>
	        <th>信息类别名称</th>
	        <th style="width:160px;">操作</th>
	        </tr>
	        </thead>
				<tbody>	
					<s:iterator value="list" id="category">	
						<tr>
							<td><s:property value="#category.cid" /></td>
							<td><s:property value="#category.cname" /></td>
							<td><a
								href='${path}admin/editCategory.action?cid=<s:property value="#category.cid"/>'
								class="tablelink">更新</a> <a href='#'
								onclick="del(<s:property value="#category.cid"/>)"
								class="tablelink">删除</a></td>
						</tr>	
					</s:iterator>	
				</tbody>
		</table>
	    <script type="text/javascript">
			function del(cid){
				if(window.confirm("您确定要删除吗？"))
				{
				   location.href="${path}admin/delCategory.action?cid="+cid;
				 }
			}
		</script>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>


