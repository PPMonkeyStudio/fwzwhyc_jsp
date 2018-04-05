package org.pxxy.service;

import java.util.List;

import org.pxxy.domain.Category;

public interface CategoryService {

	public List<Category> findAllCategory();

	public boolean addCategory(Category category);

	public boolean delCategory(Category category);

	public Category findCategoryByCid(Integer integer);

	public boolean updateCategory(Category category);

	public List<Category> findNaviCategory();

}
