package org.loda.aop.advice;

import java.lang.reflect.Method;

/**
 * 
* @ClassName: Error 
* @Description: 在切面方法抛出异常处理 
* @author minjun
* @date 2015年6月10日 上午12:23:42 
*
 */
public interface Error extends Advice{

	void error(Method method, Object[] args, Exception e);
}
