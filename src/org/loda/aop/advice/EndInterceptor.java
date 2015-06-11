package org.loda.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class EndInterceptor implements MethodInterceptor {
	private End advice;

	public EndInterceptor(End advice) {
		this.advice = advice;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		Object retVal = null;
		try {
			retVal = mi.proceed();
		} finally {
			advice.end(mi.getMethod(), mi.getArguments(), retVal);
		}
		return retVal;
	}

}
