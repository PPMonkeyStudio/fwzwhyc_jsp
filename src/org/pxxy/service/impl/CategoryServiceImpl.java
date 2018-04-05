package org.pxxy.service.impl;

import java.util.List;

import org.pxxy.dao.CategoryDao;
import org.pxxy.dao.impl.CategoryDaoImpl;
import org.pxxy.domain.Category;
import org.pxxy.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;

	@Override
	public List<Category> findAllCategory() {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.findAllCategory();
	}

	@Override
	public boolean addCategory(Category category) {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.addCategory(category);
	}

	@Override
	public boolean delCategory(Category category) {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.delCategory(category);
	}

	@Override
	public Category findCategoryByCid(Integer cid) {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.findCategoryByCid(cid);
	}

	@Override
	public boolean updateCategory(Category category) {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.updateCategory(category);
	}

	@Override
	public List<Category> findNaviCategory() {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.findNaviCategory();
	}

}
