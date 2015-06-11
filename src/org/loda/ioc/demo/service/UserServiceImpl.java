package org.loda.ioc.demo.service;

import org.loda.ioc.annotation.Component;
import org.loda.ioc.annotation.Inject;
import org.loda.ioc.demo.dao.UserDao;
import org.loda.ioc.demo.model.User;

@Component("userService")
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDao userDao;
	
	public void add(User user){
		userDao.add(user);
	}
}
