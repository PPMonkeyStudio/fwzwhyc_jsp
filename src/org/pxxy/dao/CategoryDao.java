package org.pxxy.dao;

import java.util.List;

import org.pxxy.domain.Category;

public interface CategoryDao {

	/**
	 * 查询所有类别
	 * 
	 * @return
	 */
	public List<Category> findAllCategory();

	/**
	 * 增加一条类别
	 * 
	 * @param category
	 * @return
	 */
	public boolean addCategory(Category category);

	/**
	 * 根据cid删除一条类别
	 * 
	 * @param category
	 * @return
	 */
	public boolean delCategory(Category category);

	/**
	 * 通过cid查询类别
	 * 
	 * @param cid
	 * @return
	 */
	public Category findCategoryByCid(Integer cid);

	public boolean updateCategory(Category category);

	public List<Category> findNaviCategory();

}
