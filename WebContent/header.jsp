<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
			<script type="text/javascript">
				$(function() {
					//执行ajax
					/* 
						参数1：url请求路径
						参数2：param请求参数
						参数3：代表回调方法 ，用于处理服务器的返回结果
						参数4：代表结果类型  （默认是字符串）  json:表示把字符串自动转换为js识别的数据格式
					 */
					var url = "${pageContext.request.contextPath}/findNaviCategory.action";
					var param = "";
					$.post(url, param, function(data) {
						//data:[{"cid":"1","cname":"简介"}，{"cid":"2","cname":"规划"}。。。]
						//循环json对象类型数组，并向ul标签中添加内容
						$(data).each(
								function(i, n) { //i:数组中当前对象的索引   n:数组中当前对象
									$("#navigator").append(
											"<li><a href='${pageContext.request.contextPath}/findInfosByCid.action?cid="
													+ n.cid + "'>" + n.cname
													+ "</a></li>");
								});
					}, "json");
				});
			</script>
		</ul>
	</div>
</div>