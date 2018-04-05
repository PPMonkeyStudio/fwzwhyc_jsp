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
		case "delCategory": {
			delCategory(request, response);
			break;
		}
		case "editCategory": {
			editCategory(request, response);
			break;
		}
		case "updateCategory": {
			updateCategory(request, response);
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

	/*
	 * 添加新的类别
	 */
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
	 * 删除一条类别
	 */

	public void delCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		categoryService = new CategoryServiceImpl();
		Category category = new Category();

		category.setCid(Integer.parseInt(request.getParameter("cid")));

		categoryService.delCategory(category);
		/*
		 * 重新查询所有类别
		 */
		list = categoryService.findAllCategory();

		request.setAttribute("list", list);

		request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
	}

	/*
	 * 对已有类别进行编辑
	 */
	public void editCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		categoryService = new CategoryServiceImpl();

		Category category = categoryService.findCategoryByCid(Integer.parseInt(request.getParameter("cid")));

		request.setAttribute("category", category);

		request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
	}

	/*
	 * 
	 */
	private void updateCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		categoryService = new CategoryServiceImpl();
		Category category = new Category();

		category.setCid(Integer.parseInt(request.getParameter("cid")));
		category.setCname(request.getParameter("cname"));
		categoryService.updateCategory(category);
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
