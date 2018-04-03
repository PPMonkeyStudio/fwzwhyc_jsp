package org.pxxy.service.impl;

import java.util.List;

import org.pxxy.dao.CategoryDao;
import org.pxxy.domain.Category;
import org.pxxy.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;

	@Override
	public List<Category> findAllCategory() {
		return categoryDao.findAllCategory();
	}

	@Override
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	@Override
	public void delCategory(Category category) {
		categoryDao.delCategory(category);
	}

	@Override
	public Category findCategoryByCid(Integer cid) {
		return categoryDao.findCategoryByCid(cid);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	@Override
	public List<Category> findNaviCategory() {
		return categoryDao.findNaviCategory();
	}

}
