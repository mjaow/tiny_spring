package org.loda.ioc.demo.dao;

import org.loda.ioc.annotation.Component;
import org.loda.ioc.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("userDao")
public class UserDaoImpl implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	@Override
	public void add(User user) {
		logger.info("添加了用户{}", user);
	}

}
