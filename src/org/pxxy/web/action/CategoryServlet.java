package org.pxxy.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pxxy.dao.CategoryDao;
import org.pxxy.domain.Category;

@SuppressWarnings("serial")
public class CategoryServlet extends HttpServlet {

	CategoryDao categoryDao;
	List<Category> list = null;

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
		case "findNaviCategory": {
			findNaviCategory(request, response);
			break;
		}

		}

	}

	/*
	 * 
	 */

	private void findNaviCategory(HttpServletRequest request, HttpServletResponse response) {
		categoryDao = new CategoryDao();
		List<Category> categoryList = categoryDao.findNaviCategory();
		request.setAttribute("categoryList", categoryList);
	}

	/*
	 * 后台查询信息类别数据
	 * 
	 */
	private void findAllCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		categoryDao = new CategoryDao();

		list = categoryDao.findAllCategory();

		request.setAttribute("list", list);

		request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);

	}

	/*
	 * 添加新的类别
	 */
	private void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		categoryDao = new CategoryDao();
		/*
		 * 
		 */
		Category category = new Category();

		category.setCid(Integer.parseInt(request.getParameter("cid")));

		category.setCname(request.getParameter("cname"));

		categoryDao.addCategory(category);

		/*
		 * 重新查询所有类别
		 */
		list = categoryDao.findAllCategory();

		request.setAttribute("list", list);

		request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);

	}

	/*
	 * 删除一条类别
	 */

	private void delCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		categoryDao = new CategoryDao();
		Category category = new Category();

		category.setCid(Integer.parseInt(request.getParameter("cid")));

		categoryDao.delCategory(category);
		/*
		 * 重新查询所有类别
		 */
		list = categoryDao.findAllCategory();

		request.setAttribute("list", list);

		request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
	}

	/*
	 * 对已有类别进行编辑
	 */
	private void editCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		categoryDao = new CategoryDao();

		Category category = categoryDao.findCategoryByCid(Integer.parseInt(request.getParameter("cid")));

		request.setAttribute("category", category);

		request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
	}

	/*
	 * 更新类别的信息
	 */
	private void updateCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		categoryDao = new CategoryDao();
		Category category = new Category();

		category.setCid(Integer.parseInt(request.getParameter("cid")));
		category.setCname(request.getParameter("cname"));
		categoryDao.updateCategory(category);
		/*
		 * 重新查询所有类别
		 */
		list = categoryDao.findAllCategory();

		request.setAttribute("list", list);

		request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
	}
	/*
	 * 
	 */

}
