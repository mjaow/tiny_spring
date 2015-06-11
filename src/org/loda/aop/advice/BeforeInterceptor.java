package org.loda.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * @ClassName: BeforeInterceptor
 * @Description: 在执行目标方法之前调用
 * @author minjun
 * @date 2015年6月10日 下午7:51:41
 * 
 */
public class BeforeInterceptor implements MethodInterceptor {

	private Before advice;

	public BeforeInterceptor(Before advice) {
		this.advice =advice;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		advice.before(mi.getMethod(), mi.getArguments());
		return mi.proceed();
	}

}
