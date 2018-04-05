package org.pxxy.dao;

import java.util.List;

import org.pxxy.domain.Category;

public interface CategoryDao {

	public List<Category> findAllCategory();

	public boolean addCategory(Category category);

	public boolean delCategory(Category category);

	public Category findCategoryByCid(Integer cid);

	public boolean updateCategory(Category category);

	public List<Category> findNaviCategory();

}
