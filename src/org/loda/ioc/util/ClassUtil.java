package org.loda.ioc.util;

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
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param className 类全限定名
	* @param @param paramType 参数类型 
	* @param @param o 构造方法传递的参数
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	public static Object newInstance(String className, Class<?>[] paramType, Object[] o) {
		try {
			return Class.forName(className).getConstructor(paramType).newInstance(o);
		} catch (Exception e) {
			throw new RuntimeException("创建对象失败", e);
		}
	}
}
