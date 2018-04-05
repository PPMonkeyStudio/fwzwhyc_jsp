package org.pxxy.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pxxy.domain.Category;
import org.pxxy.service.CategoryService;
import org.pxxy.service.impl.CategoryServiceImpl;

@SuppressWarnings("serial")
public class CategoryServlet extends HttpServlet {

	private CategoryService categoryService;
	private List<Category> list = null;

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
		case "findAllCategory": {
			findAllCategory(request, response);
			break;
		}
		case "addCategory": {
			addCategory(request, response);
			break;
		}
		default: {

		}
		}

	}

	/*
	 * 
	 */

	/*
	 * 后台查询信息类别数据
	 * 
	 */
	public void findAllCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		categoryService = new CategoryServiceImpl();

		list = categoryService.findAllCategory();

		request.setAttribute("list", list);

		request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);

	}

	public void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		categoryService = new CategoryServiceImpl();
		/*
		 * 
		 */
		Category category = new Category();

		category.setCid(Integer.parseInt(request.getParameter("cid")));

		category.setCname(request.getParameter("cname"));

		categoryService.addCategory(category);

		/*
		 * 重新查询所有类别
		 */
		list = categoryService.findAllCategory();

		request.setAttribute("list", list);

		request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);

	}

	/*
	 * 
	 */

}
