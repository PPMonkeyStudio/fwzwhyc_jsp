<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path", basePath);
%>
<%@ page import="org.pxxy.dao.UserDao"%>
<%@ page import="org.pxxy.domain.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
	<%
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user = userDao.login(user);
		if (user != null) {
			//若用户名或密码正确，则跳转到管理员页
			request.getRequestDispatcher("/admin/main.jsp").forward(request, response);
		} else {
			//若用户名或密码不正确，则跳转回登录页
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	%>
</body>
</html>

