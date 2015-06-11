package org.loda.aop.advice;

import java.lang.reflect.Method;

/**
 * 
* @ClassName: After 
* @Description: 在切面完成之后处理 
* @author minjun
* @date 2015年6月10日 上午12:23:30 
*
 */
public interface After extends Advice{

	void after(Method method, Object[] args,Object retVal);

}
