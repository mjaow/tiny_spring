package org.loda.ioc.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ TYPE, FIELD, METHOD })
@Retention(RUNTIME)
public @interface Inject {

	/**
	 * 
	* @Title: name 
	* @Description: 通过name进行注入 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	String name() default "";

	/**
	 * 
	* @Title: type 
	* @Description: 通过类型进行注入 
	* @param @return    设定文件 
	* @return Class<?>    返回类型 
	* @throws
	 */
	Class<?> type() default java.lang.Object.class;
}
