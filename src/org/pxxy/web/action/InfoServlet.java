package org.pxxy.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pxxy.domain.Info;
import org.pxxy.domain.PageBean;
import org.pxxy.service.CategoryService;
import org.pxxy.service.InfoService;
import org.pxxy.service.impl.InfoServiceImpl;

@SuppressWarnings("serial")
public class InfoServlet extends HttpServlet {
	/*
	 * 
	 */
	private CategoryService categoryService;
	private InfoService infoService;
	/*
	 * 
	 */

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
		case "findInfosByPage": {
			findInfosByPage(request, response);
			break;
		}
		default: {

		}
		}

	}

	/*
	 * 
	 */
	private int pageSize = 10;// 默认每页显示条数
	/*
	 * 
	 */

	public void findInfosByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		infoService = new InfoServiceImpl();

		String keywords = request.getParameter("keywords");

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		if (keywords != null) {
			keywords = keywords.trim();
		} else {
			keywords = "";
		}

		PageBean<Info> pb = infoService.findInfosByPage(currentPage, pageSize, keywords);

		request.setAttribute("pb", pb);

		request.setAttribute("keywords", keywords);

		request.getRequestDispatcher("/admin/info/list.jsp").forward(request, response);
	}

	/*
	 * 
	 */

}
