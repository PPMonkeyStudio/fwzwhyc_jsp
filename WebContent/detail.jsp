<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>非遗首页</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=3, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="./css/base.css" rel="stylesheet" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/common.js"></script>
</head>

<body>

	<!--头部-->
	<%@include file="header.jsp"%>

	<!--内容-->
	<div class="container container-bg">

		<div class="con-header">
			<div class="con-header-r">
				<a href="index.jsp">首页</a> - ${category.cname }
			</div>
			<div class="con-header-l">
				<img src="./img/Information.jpg" alt="简介" />
				<h2>${category.cname }</h2>
			</div>
		</div>
		<div class="con-img">
			<img src="./img/c02.jpg" alt="" />
		</div>
		<div class="content">
			<div align="center" style="color: Red; font-size: 24px;">
				${info.title }</div>
			<div style="height: 10px;"></div>
			<div align="center" style="color: Red; font-size: 20px;">
				${info.contentTitle }</div>
			<div style="height: 15px;"></div>
			<p align="center">${category.cname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${info.publishTime }
			</p>
			<div style="height: 4px;"></div>
			<div style="height: 2px; background-color: #E9E5DA;"></div>
			<br />
			<p>${info.content }
			<p>
		</div>

	</div>

	<!--底部-->
	<%@include file="footer.jsp"%>
</body>

</html>