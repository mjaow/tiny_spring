package org.loda.ioc.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: Match 
* @Description: 用来匹配具体的类和方法 
* @author minjun
* @date 2015年6月11日 下午1:57:32 
*
 */
@Target({TYPE,METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Match {

	String methodMatch() default "";
}
