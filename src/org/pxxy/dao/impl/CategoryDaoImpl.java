package org.pxxy.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.pxxy.dao.CategoryDao;
import org.pxxy.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

	@Autowired //按类型注入
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Category> findAllCategory() {
		return (List<Category>) hibernateTemplate.find("from Category");
	}

	@Override
	public void addCategory(Category category) {
		hibernateTemplate.save(category);
	}

	@Override
	public void delCategory(Category category) {
		hibernateTemplate.delete(category);
	}

	@Override
	public Category findCategoryByCid(Integer cid) {
		return hibernateTemplate.get(Category.class, cid);
	}

	@Override
	public void updateCategory(Category category) {
		hibernateTemplate.update(category);
	}

	@Override
	public List<Category> findNaviCategory() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
		return (List<Category>) hibernateTemplate.findByCriteria(criteria , 0, 5);
	}
	
}
