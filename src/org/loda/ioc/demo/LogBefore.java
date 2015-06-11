package org.loda.ioc.demo;

import java.lang.reflect.Method;

import org.loda.aop.advice.Before;

public class LogBefore implements Before {

	@Override
	public void before(Method method, Object[] args) {
		System.out.println("在方法调用之前记录日志.......");
	}

}
