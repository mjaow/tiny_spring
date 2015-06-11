package org.loda.ioc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: Component 
* @Description: 组件（去除spring中拥有相同功能的@controller和@service以及@repository） 
* @author minjun
* @date 2015年6月8日 下午10:56:33 
*
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

	/**
	 * 
	* @Title: value 
	* @Description: 该类的签名
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	String value() default "";
}
