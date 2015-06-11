package org.loda.aop.advice;

import java.lang.reflect.Method;

/**
 * 
 * @ClassName: End
 * @Description: 结束的时候调用
 * @author minjun
 * @date 2015年6月11日 下午12:04:05
 * 
 */
public interface End extends Advice {
	
	void end(Method method, Object[] args,Object retVal);
}
