package org.pxxy.service;

import java.util.List;
import org.pxxy.domain.Category;

public interface CategoryService {

	List<Category> findAllCategory();

	void addCategory(Category category);

	void delCategory(Category category);

	Category findCategoryByCid(Integer integer);

	void updateCategory(Category category);

	List<Category> findNaviCategory();

}
