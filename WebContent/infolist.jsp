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
<meta charset="utf-8" />
<title>非遗首页</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=3, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="./css/base.css" rel="stylesheet" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<style type="text/css">
.pagination {
	padding: 50px 0 0 0;
	text-align: center;
	font-size: 14px;
}

.pagination a {
	display: inline-block;
	margin: 0 5px;
	height: 30px;
	line-height: 30px;
	border: 1px solid #eee;
	padding: 0 5px;
	border-radius: 0.3rem;
}

.pagination a:hover {
	border: 1px solid #ddd;
	background-color: rgba(153, 153, 153, 0.02);
}

.pagination a.active {
	color: #cd0104;
	font-weight: 600;
	border-color: rgba(205, 1, 4, 0.1);
	background-color: rgba(205, 1, 4, 0.05);
}

.pagination a.disabled {
	color: #999999;
	border-color: rgba(153, 153, 153, 0.05);
	background-color: rgba(153, 153, 153, 0.05);
}
</style>
</head>

<body>

	<!--头部-->
	<%@include file="header.jsp"%>

	<!--内容-->
	<div class="container container-bg">

		<div class="con-header">
			<div class="con-header-r">
				<a href="/">首页</a> - ${category.cname}
				<div style="display: none;" id="cid">
					<c:out value="${category.cid}" />
				</div>
			</div>
			<div class="con-header-l">
				<img src="./img/Information.jpg" alt="${category.cname }" />
				<h2>${category.cname}</h2>
			</div>
		</div>
		<div class="con-img">
			<img src="./img/c01.jpg" alt="" />
		</div>
		<div class="content list">
			<c:if test="${!empty pb.list}">
				<ul>
					<c:forEach items="${pb.list}" var="info">
						<li><span><c:out value="${info.publishTime}" /></span> <a
							target="_blank"
							href="${Path}info?option=findInfoByInfoId&infoId=<c:out value="${info.infoId}"/>&cid=<c:out value="${category.cid}" />">
								<c:out value="${info.title}" />
						</a></li>
					</c:forEach>

				</ul>
				<div class="pagination">
					第
					<c:out value="${pb.currentPage}" />
					页 &nbsp;&nbsp; 共
					<c:out value="${pb.totalPage}" />
					页 &nbsp;&nbsp; 共
					<c:out value="${pb.count}" />
					条信息
					<div style="height: 10px;"></div>

					<c:choose>
						<c:when test="${pb.currentPage <= 1}"> 首页&nbsp;&nbsp;上一页 </c:when>
						<c:otherwise>
							<a href='#' onclick="fy(1)">首页</a>
							<a href='#' onclick="fy(<c:out value="${pb.currentPage - 1}"/>)">上一页</a>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pb.currentPage < pb.totalPage}">
							<a href='#' onclick="fy(<c:out value="${pb.currentPage + 1}"/>)">下一页</a>
							<a href='#' onclick="fy(<c:out value="${pb.totalPage}"/>)">尾页</a>
						</c:when>
						<c:otherwise>
								下一页&nbsp;&nbsp;尾页
								</c:otherwise>
					</c:choose>
					&nbsp;&nbsp; 跳转至 <input type="text"
						style="height: 22px; border: 1px solid #888; width: 30px; border-radius: 0.2rem;"
						name="page" id="page"> 页 <a href='#' onclick="validate()">跳转</a>

				</div>
			</c:if>
			<c:if test="${empty pb.list}">
				<div>
					<br /> 暂无信息 <br /> <br />
				</div>
			</c:if>
		</div>
	</div>
	<script type="text/javascript">
		
		 function fy(page)
        { 
        var cid=$.trim(document.getElementById("cid").innerHTML);
 		   window.location = "/end_infopub/info?option=findInfosByCid&currentPage=" + page + "&cid=" + cid;		  
        }
		function validate()
        {
        var cid=document.getElementById("cid").value; 
            var page = document.getElementById("page").value;
            fy(page);
        } 
	</script>

	<!--底部-->
	<%@include file="footer.jsp"%>
</body>

</html>