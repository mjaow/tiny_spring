package org.loda.ioc.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.loda.ioc.util.ClassUtil;

/**
 * 
* @ClassName: ApplicationContext 
* @Description: 上下文环境
* @author minjun
* @date 2015年6月9日 下午2:22:29 
*
 */
public class ApplicationContext implements BeanFactory {

	/**
	 * 使用map作为容器存储bean对象
	 */
	protected Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 以下两个方法都是获取bean容器中的bean对象
	 */

	@Override
	public Object getBean(String beanName) {
		return map.get(beanName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBean(Class<T> requireType) {
		return (T) map
				.get(StringUtils.uncapitalize(requireType.getSimpleName()));
	}

	/**
	 * 
	* @Title: refresh 
	* @Description: 清理bean容器 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	protected void refresh() {
		map.clear();
	}

	/**
	 * 
	* @Title: getClassLoader 
	* @Description: 使用自己的类加载器
	* @param @return    设定文件 
	* @return ClassLoader    返回类型 
	* @throws
	 */
	protected ClassLoader getClassLoader() {
		return ClassUtil.getClassLoader();
	}

}
