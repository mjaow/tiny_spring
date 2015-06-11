package org.loda.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 
* @ClassName: AfterInterceptor 
* @Description: 目标方法执行后调用 
* @author minjun
* @date 2015年6月10日 下午7:46:08 
*
 */
public class AfterInterceptor implements MethodInterceptor {

	private After advice;

	public AfterInterceptor(After advice) {
		this.advice = advice;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		Object o = mi.proceed();
		advice.after(mi.getMethod(), mi.getArguments(),o);
		return o;
	}

}
