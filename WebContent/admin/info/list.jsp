<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path }admin/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${path }admin/js/jquery.js"></script>
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
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">信息列表</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li class="click"><a href="${path }admin/info/add.jsp"><span><img
							src="images/t01.png" /></span>添加信息</a></li>
			</ul>
		</div>
		<form action="${path }admin/findInfosByPage" id="infoListForm"
			name="ff" method="post">
			<ul class="seachform">
				<li><label>标题关键词</label><input name="keywords"
					value="${keywords }" type="text" style="width: 400px;"
					class="scinput" /></li>
				<li><label>&nbsp;</label><input name="" type="submit"
					class="scbtn" value="查询" /></li>
			</ul>
		</form>
		<table style="width: 100%;" class="tablelist">
			<thead>
				<tr>
					<th style="width: 100px;">信息id</th>
					<th>信息标题</th>
					<th style="width: 100px;">排序</th>
					<th style="width: 100px;">是否发布</th>
					<th style="width: 160px;">发布时间</th>
					<th style="width: 100px;">所属类别</th>
					<th style="width: 100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty pb.list}">
					<s:iterator value="pb.list" id="info">
						<tr>
							<td><s:property value="#info.infoId" /></td>
							<td><s:property value="#info.title" /></td>
							<td><s:property value="#info.paiXu" /></td>
							<td><s:if test="#info.publishStatus == 1">
									是
			                    </s:if> <s:else>
			                    	否
			                    </s:else></td>
							<td><s:date name="#info.publishTime"
									format="yyyy-MM-dd HH:mm:ss" /></td>
							<td><s:property value="#info.category.cname" /></td>
							<td><a
								href='${path}admin/editInfo.action?infoId=<s:property value="#info.infoId"/>'>更新</a>
								&nbsp;|&nbsp; <a href='#'
								onclick="del(<s:property value="#info.infoId"/>)">删除</a></td>
						</tr>
					</s:iterator>
				</c:if>
				<c:if test="${empty pb.list}">
					<div>
						<tr>
							<td colspan="7" align="center">暂无信息</td>
						</tr>
					</div>
				</c:if>
				<tr>
					<td colspan="7" align="center">
						<div class="pagination">
							第
							<s:property value="#request.pb.currentPage" />
							页 &nbsp;&nbsp; 共
							<s:property value="#request.pb.totalPage" />
							页 &nbsp;&nbsp; 共
							<s:property value="#request.pb.count" />
							条信息
							<div style="height: 10px;"></div>
							<s:if test="#request.pb.currentPage == 1"> 首页&nbsp;&nbsp;上一页 </s:if>
							<s:else>
								<a href='#' onclick="fy(1)">首页</a>
								<a href='#'
									onclick="fy(<s:property value="#request.pb.currentPage - 1"/>)">上一页</a>
							</s:else>
							<s:if test="#request.pb.currentPage != #request.pb.totalPage">
								<a href='#'
									onclick="fy(<s:property value="#request.pb.currentPage + 1"/>)">下一页</a>
								<a href='#'
									onclick="fy(<s:property value="#request.pb.totalPage"/>)">尾页</a>
							</s:if>
							<s:else>下一页&nbsp;&nbsp;尾页</s:else>
							&nbsp;&nbsp; 跳转至 <input type="text"
								style="height: 22px; border: 1px solid #888; width: 30px; border-radius: 0.2rem;"
								name="page" id="page"> 页 <a href='#'
								onclick="validate()">跳转</a>

						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<script type="text/javascript">
		
		 function fy(page)
        {
			 var form=document.getElementById("infoListForm");
			 form.action="${pageContext.request.contextPath}/admin/findInfosByPage.action?currentPage=" + page ;
			 form.submit();
 		   //window.document.location.href = "${pageContext.request.contextPath}/admin/findInfosByPage.action?currentPage=" + page 		  
        }
		function validate()
        {
            var page = document.getElementById("page").value;
            if(page > <s:property value="#request.pb.totalPage"/> || page <= 0 )
            {
                alert("你输入的页数大于最大页数或小于最小页面，页面将跳转到首页！");
                fy(1)
             }else{
            	fy(page)
           }
        }
	</script>
		<script type="text/javascript">
		function del(infoId){
			if(window.confirm("您确定要删除吗？"))
			{
			   location.href="${path}admin/delInfo.action?infoId="+infoId;
			 }
		}
	</script>
	</div>
	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>



