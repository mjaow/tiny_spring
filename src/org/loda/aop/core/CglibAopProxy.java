package org.loda.aop.core;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibAopProxy implements AopProxy, MethodInterceptor {

	/**
	 * 存有被代理对象信息的配置项
	 */
	private AdvisedSupport config;

	public CglibAopProxy(AdvisedSupport config) {
		this.config = config;
	}

	@Override
	public Object getProxy() {
		return Enhancer.create(config.getTargetClass(), this);
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		return new CglibMethodInvocation(config.getInterceptors(),
				config.getMatchers(), args, method, config.getTarget(), proxy)
				.proceed();
	}

	private class CglibMethodInvocation extends ReflectiveMethodInvocation {

		private MethodProxy methodProxy;

		public CglibMethodInvocation(
				List<org.aopalliance.intercept.MethodInterceptor> chain,
				List<Matcher> matchers, Object[] arguments, Method method,
				Object target, MethodProxy methodProxy) {
			super(chain, matchers, arguments, method, target);
			this.methodProxy = methodProxy;
		}

		@Override
		protected Object invokeJoinpoint() throws Throwable {
			return methodProxy.invoke(getThis(), getArguments());
		}

	}

}
