package org.pxxy.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.pxxy.domain.User;
import org.pxxy.service.UserService;
import org.pxxy.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {

	UserService userService;
	HttpSession session;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String option = request.getParameter("option");

		switch (option) {
		case "login": {
			login(request, response);
			break;
		}
		default: {

		}
		}

	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userService = new UserServiceImpl();
		User user = new User();

		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));

		user = userService.login(user);

		if (user != null) {
			session = request.getSession();

			if (session.getAttribute("userName") != null) {
				session.removeAttribute("userName");
			}

			session.setAttribute("userName", user.getUserName());

			request.getRequestDispatcher("/admin/main.jsp").forward(request, response);

		} else {

			request.setAttribute("msg", "用户名或密码错误!");

			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}

	}
}
