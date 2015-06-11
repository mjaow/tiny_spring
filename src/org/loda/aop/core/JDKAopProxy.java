package org.loda.aop.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 
 * @ClassName: JDKAopProxy
 * @Description: jdk动态代理
 * @author minjun
 * @date 2015年6月9日 下午8:41:44
 * 
 */
public class JDKAopProxy implements AopProxy, InvocationHandler {

	private AdvisedSupport config;

	public JDKAopProxy(AdvisedSupport config) {
		this.config = config;
	}

	@Override
	public Object getProxy() {
		return Proxy.newProxyInstance(config.getClassLoader(),
				config.getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return new ReflectiveMethodInvocation(config.getInterceptors(),config.getMatchers(), args,
				method, config.getTarget()).proceed();
	}

}
