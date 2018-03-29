package org.pxxy.dao;

import java.util.List;

import org.pxxy.domain.Category;

public interface CategoryDao {

	List<Category> findAllCategory();

	void addCategory(Category category);

	void delCategory(Category category);

	Category findCategoryByCid(Integer cid);

	void updateCategory(Category category);

	List<Category> findNaviCategory();

}
