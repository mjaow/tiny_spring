package org.loda.ioc.demo;

import java.lang.reflect.Method;

import org.loda.aop.advice.Error;

public class LogError implements Error {

	@Override
	public void error(Method method, Object[] args, Exception e) {
		System.out.println("在出现异常的时候记录");
	}


}
