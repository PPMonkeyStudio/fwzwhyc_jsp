package org.pxxy.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pxxy.domain.Category;
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
	private Info info = new Info();
	private String keywords;
	private List<Category> categorylist;
	private CategoryService categoryService;
	private InfoService infoService;
	/*
	 * 
	 */
	private int currentPage = 1; // 当前页
	private int pageSize = 10;// 默认每页显示条数
	private int cid; // 商品类别编号
	private PageBean<Info> pb; // ${pb}
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
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
	public void findInfosByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		infoService = new InfoServiceImpl();
		if (keywords != null) {
			keywords = keywords.trim();
		}
		pb = infoService.findInfosByPage(currentPage, pageSize, keywords);
		request.getRequestDispatcher("/admin/info/list.jsp").forward(request, response);
	}

	/*
	 * 
	 */

}
