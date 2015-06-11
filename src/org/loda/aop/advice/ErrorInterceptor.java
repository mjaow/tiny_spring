package org.loda.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * @ClassName: ErrorInterceptor
 * @Description: 在执行目标方法出现异常的时候调用
 * @author minjun
 * @date 2015年6月10日 下午7:51:10
 * 
 */
public class ErrorInterceptor implements MethodInterceptor {

	private Error advice;

	public ErrorInterceptor(Error advice) {
		this.advice = advice;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		Object retVal = null;
		try {
			retVal = mi.proceed();
		} catch (Exception e) {
			advice.error(mi.getMethod(), mi.getArguments(), e);
			throw e;
		}
		return retVal;
	}

}
