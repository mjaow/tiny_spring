package org.loda.ioc.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import org.loda.ioc.core.AnnotationContext;

/**
 * 
 * @ClassName: ClassUtil
 * @Description: class对象所涉及的工具类
 * @author minjun
 * @date 2015年6月9日 下午8:43:36
 * 
 */
public class ClassUtil {

	/**
	 * 
	 * @Title: getClassLoader
	 * @Description: 获取默认自定义的类加载器
	 * @param @return 设定文件
	 * @return ClassLoader 返回类型
	 * @throws
	 */
	public static ClassLoader getClassLoader() {

		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {
			// Cannot access thread context ClassLoader - falling back...
		}
		if (cl == null) {
			// No thread context class loader -> use class loader of this class.
			cl = AnnotationContext.class.getClassLoader();
			if (cl == null) {
				// getClassLoader() returning null indicates the bootstrap
				// ClassLoader
				try {
					cl = ClassLoader.getSystemClassLoader();
				} catch (Throwable ex) {
					// Cannot access system ClassLoader - oh well, maybe the
					// caller can live with null...
				}
			}
		}
		return cl;

	}

	/**
	 * 
	 * @Title: newInstance
	 * @Description: 调用有参构造函数来创建对象
	 * @param @param className 类全限定名
	 * @param @param paramType 参数类型
	 * @param @param o 构造方法传递的参数
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	public static Object newInstance(String className, Class<?>[] paramType,
			Object[] o) {
		try {
			return Class.forName(className).getConstructor(paramType)
					.newInstance(o);
		} catch (Exception e) {
			throw new RuntimeException("创建对象失败", e);
		}
	}

	/**
	 * 
	 * @Title: hasAnnotation
	 * @Description: 判断元素element是否拥有annotationClass类型的注解
	 * @param @param element
	 * @param @param annotationClass
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean hasAnnotation(AnnotatedElement element,
			Class<? extends Annotation> annotationClass) {
		return element.getAnnotation(annotationClass) != null;
	}

	/**
	 * 
	 * @Title: getMethodAnnotation
	 * @Description: 获取方法上面的注解
	 * @param @param targetClass
	 * @param @param methodName
	 * @param @param parameterTypes
	 * @param @param annotationClass
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @throws
	 */
	public static <T extends Annotation> T getMethodAnnotation(
			Class<?> targetClass, String methodName, Class<?>[] parameterTypes,
			Class<T> annotationClass) {
		try {
			return targetClass.getMethod(methodName, parameterTypes)
					.getAnnotation(annotationClass);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("找不到这个方法" + methodName, e);
		}
	}

}
