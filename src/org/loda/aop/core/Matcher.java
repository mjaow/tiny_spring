package org.loda.aop.core;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.loda.ioc.annotation.Match;
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
	 * 查看类是否匹配
	 */
	private String classFilter;

	/**
	 * 查看方法是否匹配
	 */
	private String methodMatcher;

	/**
	 * @param interfaceClass
	 *            接口类型
	 * 
	 * @param implementClass
	 *            实现类类型
	 */
	public Matcher(Class<?> interfaceClass, Class<?> implementClass) {
		// 获取接口的第一个方法（每个接口只有一个方法。如：org.loda.aop.advice.Before接口只有一个before方法）
		Method method = interfaceClass.getMethods()[0];
		logger.info("method name is {}", method.getName());
		Match m = null;
		try {
			m = implementClass.getMethod(method.getName(),
					method.getParameterTypes()).getAnnotation(Match.class);
			if (m != null) {
				this.classFilter = m.classFilter();
				this.methodMatcher = m.methodMatch();
			}
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("找不到该方法", e);
		}
		logger.info(method + "注解信息为：" + this.classFilter + ","
				+ this.methodMatcher);
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
		String target = targetClass.getName() + "." + method.getName();

		String defineMatcher = classFilter + "." + methodMatcher;

//		logger.info("target is {},and defineMatcher is {}", target,
//				defineMatcher);
		if (StringUtils.equals(target, defineMatcher)) {
			logger.info("方法{}匹配", target);
			return true;
		}
		return false;
	}

}
