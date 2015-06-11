package org.loda.ioc.demo.controller;

import org.loda.ioc.annotation.Component;
import org.loda.ioc.annotation.Inject;
import org.loda.ioc.demo.model.User;
import org.loda.ioc.demo.service.UserService;

@Component
public class UserController {
	
	@Inject
	private UserService userService;
	
	public void add(User user){
		userService.add(user);
	}
}
