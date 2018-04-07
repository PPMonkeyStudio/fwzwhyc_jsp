<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>非物质文化遗产研究中心</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=3, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="./css/base.css" rel="stylesheet" />
<script type="text/javascript" src="./js/modernizr.custom.53451.js"></script>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery.gallery.js"></script>
<script type="text/javascript" src="./js/common.js"></script>
</head>

<body>

	<!--头部-->
	<%@include file="header.jsp"%>
	<c:import var="data" url="/info?option=findInfos" />
	<%-- <s:action name="findInfos" executeResult="false" namespace="/"></s:action> --%>
	<!--幻灯片-->
	<div class="slide-container">
		<section id="dg-container" class="dg-container">
			<div class="dg-wrapper">
				<a href="#1"><img src="./img/slide1.jpg" alt="image01"></a> <a
					href="#2"><img src="./img/slide2.jpg" alt="image02"></a> <a
					href="#3"><img src="./img/slide3.jpg" alt="image03"></a>
			</div>
			<nav>
				<span class="dg-prev"></span> <span class="dg-next"></span>
			</nav>
		</section>
	</div>

	<!--内容-->
	<div class="container">

		<!--非遗资讯-->
		<div class="item col-12">
			<div class="item-header">
				<div class="item-header-r">
					<a href="${Path}info?option=findInfosByCid&cid=101&currentPage=1"
						class="icon-more" title="更多"></a>
				</div>
				<div class="item-header-l">
					<strong>非遗资讯</strong><span>INFORMATION</span>
				</div>
			</div>
			<div class="item-body">

				<div class="book-scroll">
					<div class="book-scroll-wrap">
						<ul>
							<c:forEach items="${fyzxInfos}" var="info">
								<li class="open"><a href="javascript:void(0);" class="pic">
										<img src="${path}info?option=getImg&imgName=${info.picPath}"
										alt="">
										<div>${info.publishTime}</div>
								</a>
									<div class="detail">
										<h3>${info.title}</h3>
										<p>${info.contentAbstract}…</p>
										<p>
											<a target="blank"
												href="${Path}info?option=findInfoByInfoId&infoId=${info.infoId}' />">详细内容</a>
										</p>
									</div></li>
							</c:forEach>
						</ul>
					</div>
					<a href="javascript:void(0)" class="btn-prev icon-arrowl"></a> <a
						href="javascript:void(0)" class="btn-next icon-arrowr"></a>
				</div>
			</div>
		</div>
		<div style="">
			<!--学术交流-->
			<div class="item col-6 col-r">
				<div class="item-header">
					<div class="item-header-r">
						<a href="${Path}info?option=findInfosByCid&cid=102&currentPage=1"
							class="icon-more" title="更多"></a>
					</div>
					<div class="item-header-l">
						<strong>学术交流</strong><span>COMMUNICATION</span>
					</div>
				</div>
				<div class="item-body item-img-list">
					<div class="item-img">
						<c:set var="index" value="1" />
						<c:forEach items="${xsjlInfos}" var="info">
							<c:if test="${info.picPath != NULL && info.picPath != ''}">
								<c:if test="${index==1}">
									<c:set var="index" value="2" />
									<a target="blank"
										href="${Path}/findInfoByInfoId&infoId=${xsjlInfo.infoId}"><img
										src="${path}info?option=getImg&imgName=${info.picPath}" /></a>
								${memeber.nickName}
							</c:if>
							</c:if>
						</c:forEach>
					</div>
					<ul>
						<c:forEach items="${xsjlInfos}" var="info">
							<li><a target="blank"
								href="${Path}info?option=findInfoByInfoId&infoId=${info.infoId}">${info.title}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<!--非遗聚焦-->
			<div class="item col-6 col-l">
				<div class="item-header">
					<div class="item-header-r">
						<a href="${Path}info?option=findInfosByCid&cid=103&currentPage=1"
							class="icon-more" title="更多"></a>
					</div>
					<div class="item-header-l">
						<strong>非遗聚焦</strong><span>FOCUS</span>
					</div>
				</div>
				<div class="item-body item-img-list">
					<ul>
						<c:forEach items="${fyjjInfos}" var="info">
							<li><span>${info.publishTime}</span><a target="blank"
								href="${Path}info?option=findInfoByInfoId&infoId=${info.infoId}">${info.title}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
		<div style="float: left;">
			<!--非遗讲坛-->
			<div class="item col-4 col-r">
				<div class="item-header">
					<div class="item-header-r">
						<a href="${Path}info?option=findInfosByCid&cid=104&currentPage=1"
							class="icon-more" title="更多"></a>
					</div>
					<div class="item-header-l">
						<strong>非遗讲坛</strong><span>PLATFORM</span>
					</div>
				</div>
				<div class="item-body item-img-list">
					<c:forEach items="${fyjtInfos}" var="info">
						<a target="blank"
							href="${Path}info?option=findInfoByInfoId&infoId=${info.infoId}">
							<img src="${path}info?option=getImg&imgName=${info.picPath}" />
						</a>
					</c:forEach>
				</div>
			</div>

			<!--合作平台-->
			<div class="item col-4 col-rl">
				<div class="item-header">
					<div class="item-header-r">
						<a href="${Path}info?option=findInfosByCid&cid=105&currentPage=1"
							class="icon-more" title="更多"></a>
					</div>
					<div class="item-header-l">
						<strong>合作平台</strong><span>COOPERATION</span>
					</div>
				</div>
				<div class="item-body item-img-list">
					<c:forEach items="${hzptInfos}" var="info">
						<a target="blank"
							href="${Path}info?option=findInfoByInfoId&infoId=${info.infoId}">
							<img src="${path}info?option=getImg&imgName=${info.picPath}" />
						</a>
					</c:forEach>
				</div>
			</div>

			<!--非遗传人-->
			<div class="item col-4 col-l">
				<div class="item-header">
					<div class="item-header-r">
						<a href="${Path}info?option=findInfosByCid&cid=106&currentPage=1"
							class="icon-more" title="更多"></a>
					</div>
					<div class="item-header-l">
						<strong>非遗传人</strong><span>INHERITOR</span>
					</div>
				</div>
				<div class="item-body item-img-list">
					<c:forEach items="${fycrInfos}" var="info">
						<a target="blank"
							href="${Path}info?option=findInfoByInfoId&infoId=${info.infoId}">
							<img src="${path}info?option=getImg&imgName=${info.picPath}" />
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
		<!--其他-->
		<div class="item col-12 hide">
			<ul class="item-other">
				<li><a href=""> <i class="icon-earth"></i>
						<p>非遗网站</p>
				</a></li>
				<li><a href=""> <i class="icon-letter"></i>
						<p>邮件投递</p>
				</a></li>
				<li><a href=""> <i class="icon-computer"></i>
						<p>学院入口</p>
				</a></li>
				<li><a href=""> <i class="icon-book"></i>
						<p>OA校内</p>
				</a></li>
				<li><a href=""> <i class="icon-folder"></i>
						<p>OA校外</p>
				</a></li>
				<li><a href=""> <i class="icon-report"></i>
						<p>投稿平台</p>
				</a></li>
				<li class="qrcode"><img src="./img/qrcode.jpg" />
					<p>微信公众号</p></li>
			</ul>
		</div>

	</div>

	<!--底部-->
	<%@include file="footer.jsp"%>
</body>

</html>