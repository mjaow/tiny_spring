package org.loda.ioc.core;

/**
 * 
* @ClassName: BeanFactory 
* @Description: bean工厂 
* @author minjun
* @date 2015年6月8日 下午10:18:18 
*
 */
public interface BeanFactory {

	/**
	 * 
	* @Title: getBean 
	* @Description: 根据bean的名字获取bean对象 
	* @param @param beanName
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	Object getBean(String beanName);
	
	/**'
	 * 
	* @Title: getBean 
	* @Description: 根据bean的类型获取bean对象 
	* @param @param requireType
	* @param @return    设定文件 
	* @return T    返回类型 
	* @throws
	 */
	<T> T getBean(Class<T> requireType);
	
	
}
