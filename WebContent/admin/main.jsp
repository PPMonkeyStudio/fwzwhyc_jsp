<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path", basePath);
%>
<!doctype html>
<html>
<head>
<title>信息管理系统</title>
</head>

<frameset rows="88,*" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="${path}admin/top.jsp" name="topFrame" scrolling="no"
		noresize="noresize" id="topFrame" title="topFrame" />
	<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		<frame src="${path}admin/left.jsp" name="leftFrame" scrolling="no"
			noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="${path}admin/index.jsp" name="rightFrame" id="rightFrame"
			title="rightFrame" />
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>

</html>

