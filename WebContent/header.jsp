<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import var="data" url="/category?option=findNaviCategory" />
<div class="header">
	<a href="/"><img src="./img/logo.png" class="logo" /></a> <a
		href="javascript:void(0)" class="icon-menu" id="menu"></a>
	<div class="menu" id="menu-box">
		<a href="javascript:void(0)" class="icon-close" id="menu-close"></a>
		<div class="search">
			<input type="text" name="" id="" placeholder="输入搜索内容" />
			<button class="icon-search" id="search"></button>
		</div>
		<ul id="navigator">
			<li><a href="index.jsp" class="active">首页</a></li>
			<c:forEach items="${categoryList}" var="category">
				<li><a
					href='${Path}info?option=findInfosByCid&cid=${category.cid}&currentPage=1'>${category.cname}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>