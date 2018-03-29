package org.pxxy.service.impl;

import org.pxxy.dao.UserDao;
import org.pxxy.domain.User;
import org.pxxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired  //注入
	private UserDao userDao;
	@Override
	public User login(User user) {
		return userDao.login(user);
	}

}
