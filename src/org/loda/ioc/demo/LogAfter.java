package org.loda.ioc.demo;

import java.lang.reflect.Method;

import org.loda.aop.advice.After;

public class LogAfter implements After {

	@Override
	public void after(Method method, Object[] args, Object retVal) {
		System.out.println("在方法调用之后打印日志");		
	}


}
