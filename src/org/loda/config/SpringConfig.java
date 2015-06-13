package org.loda.config;

import java.util.List;

/**
 * 
 * @ClassName: SpringConfig
 * @Description: mini-spring的配置信息
 * @author minjun
 * @date 2015年6月8日 下午11:18:31
 * 
 */
public interface SpringConfig {

	/**
	 * 
	 * @Title: basePath
	 * @Description: 注解扫描的根路径
	 * @param @return 设定文件
	 * @return String[] 返回类型
	 * @throws
	 */
	String[] basePath();

	/**
	 * 
	 * @Title: getTargetToProxy
	 * @Description: 需要代理的目标类集合
	 * @param @return 设定文件
	 * @return List<Class<?>> 返回类型
	 * @throws
	 */
	List<Class<?>> getTargetToProxy();

}
