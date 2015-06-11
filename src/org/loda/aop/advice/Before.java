package org.loda.aop.advice;

import java.lang.reflect.Method;

/**
 * 
* @ClassName: Before 
* @Description: 在切面方法执行之前处理
* @author minjun
* @date 2015年6月10日 上午12:23:01 
*
 */
public interface Before extends Advice{

	void before(Method method, Object[] args);
}
