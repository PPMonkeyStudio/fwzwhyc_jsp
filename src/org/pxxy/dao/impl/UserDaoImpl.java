package org.pxxy.dao.impl;

import org.pxxy.dao.UserDao;
import org.pxxy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired //按类型注入
	private HibernateTemplate hibernateTemplate;
	@Override
	public User login(User user) {
		return hibernateTemplate.get(User.class, user.getUserName());
	}

}
