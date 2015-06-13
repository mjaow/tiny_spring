package org.loda.aop.core;

import java.lang.reflect.Method;

import org.loda.ioc.annotation.Match;
import org.loda.ioc.util.ClassUtil;
import org.loda.ioc.util.RegExUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: Matcher
 * @Description: 匹配类和方法
 * @author minjun
 * @date 2015年6月11日 下午3:48:52
 * 
 */
public class Matcher {

	private static final Logger logger = LoggerFactory.getLogger(Matcher.class);

	/**
	 * 查看类->方法是否匹配
	 */
	private String methodMatcher;

	/**
	 * 切面上是否有匹配器
	 * 
	 * 默认为false，表示没有匹配器
	 */
	private boolean hasMatcher;

	/**
	 * 
	 * 1.获取类上面的Match注解，修改方法匹配参数methodMatcher的值
	 * 2.获取方法上面的Match注解，如果方法上面有，则以方法上面的注解为主
	 * 
	 * @param interfaceClass
	 *            接口类型
	 * 
	 * @param implementClass
	 *            实现类类型
	 */
	public Matcher(Class<?> interfaceClass, Class<?> implementClass) {
		// 获取类上注解
		Match classMatcher = implementClass.getAnnotation(Match.class);

		setMethodMatch(classMatcher);

		// 获取接口的第一个方法（每个接口只有一个方法。如：org.loda.aop.advice.Before接口只有一个before方法）
		Method method = interfaceClass.getMethods()[0];
		logger.info("method name is {}", method.getName());
		Match m = ClassUtil.getMethodAnnotation(implementClass,
				method.getName(), method.getParameterTypes(), Match.class);

		setMethodMatch(m);

		logger.info(method + "注解信息为：" + this.methodMatcher);
	}

	/**
	 * 
	 * @Title: setMethodMatch
	 * @Description: 设置方法匹配器的值
	 * @param @param m 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void setMethodMatch(Match m) {
		if (m != null) {
			// 上面有match注解
			hasMatcher = true;
			this.methodMatcher = m.methodMatch();
		}
	}

	/**
	 * 
	 * @Title: matches
	 * @Description: 查看方法匹配器中的配置和指定的方法是否匹配
	 * @param @param method 目标对象的方法
	 * @param @param targetClass 目标对象所属类
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean matches(Method method, Class<? extends Object> targetClass) {
		// 如果方法上面没有注解
		if (!hasMatcher)
			return true;

		String target = targetClass.getName() + "." + method.getName();

		String defineMatcher = methodMatcher;
		
		logger.info("匹配样式为{},这个方法的样式为{}",defineMatcher,target);

		return match(target, defineMatcher);
	}

	/**
	 * 
	* @Title: match 
	* @Description: 利用正则匹配 
	* @param @param target
	* @param @param defineMatcher
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	private boolean match(String target, String defineMatcher) {
		//将具体的target指向的路径和定义的方法路径匹配，如果匹配成功，则表示该方法可以访问，否则不能访问
		return RegExUtil.match(defineMatcher,target);
		
	}

}
