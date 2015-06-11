package org.loda.aop.core;

/**
 * 
* @ClassName: AopProxy 
* @Description: Aop代理 
* @author minjun
* @date 2015年6月9日 下午8:38:42 
*
 */
public interface AopProxy {

	/**
	 * 
	* @Title: getProxy 
	* @Description: 根据默认的类加载器获取代理对象 
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	Object getProxy();
	
}
