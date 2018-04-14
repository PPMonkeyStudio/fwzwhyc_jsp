<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path", basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="keywords" content="scclui框架">
<meta name="description" content="scclui为轻量级的网站后台管理系统模版。">
<title>首页</title>
<link rel="stylesheet" href="${path}common/layui/css/layui.css">
<link rel="stylesheet" href="${path}common/css/sccl.css">

</head>
<body class="login-bg">
	<div class="login-box">
		<header>
		<h1>后台管理系统</h1>
		</header>
		<div class="login-main">
			<form action="${path}user?option=login" class="layui-form"
				method="post">
				<div align="center" style="height: 30px;">
					<font style="color: #ff0000; font-size: 16px;">${msg}</font>
				</div>
				<input name="__RequestVerificationToken" type="hidden" value="">
				<div class="layui-form-item">
					<input type="text" name="userName" lay-verify="userName"
						autocomplete="off" placeholder="这里输入登录名" class="layui-input">
				</div>
				<div class="layui-form-item">
					<input type="password" name="password" lay-verify="password"
						autocomplete="off" placeholder="这里输入密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<div class="pull-right">
						<button class="layui-btn layui-btn-primary" lay-submit=""
							lay-filter="login">登录</button>
					</div>
					<div class="clear"></div>
				</div>
			</form>
		</div>
		<footer>
		<p>非物质文化遗产研究中心</p>
		</footer>
	</div>
	<script src="${path}common/layui/layui.js"></script>
	<script>
		layui.use([ 'layer', 'form' ], function() {
			var layer = layui.layer, $ = layui.jquery, form = layui.form();
			form.verify({
				userName : function(value) {
					if (value === '')
						return '请输入用户名';
				},
				password : function(value) {
					if (value === '')
						return '请输入密码';
				}
			});
			var errorCount = 0;
			form.on('submit(login)', function(data) {

			});
		});
	</script>
</body>
</html>

