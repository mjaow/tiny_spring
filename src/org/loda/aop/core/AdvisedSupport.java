package org.loda.aop.core;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.loda.aop.advice.Advice;
import org.loda.ioc.util.ClassUtil;

/**
 * 
 * @ClassName: AdvisedSupport
 * @Description: 包含目标对象、目标对象的类型、加载在目标对象上的所有advice
 * @author minjun
 * @date 2015年6月10日 下午10:20:54
 * 
 */
public class AdvisedSupport {

	/**
	 * 被代理对象
	 */
	private Object target;

	/**
	 * 被代理对象的类型
	 */
	private Class<?> targetClass;

	/**
	 * 加载在该target目标对象上的所有advice
	 */
	private List<Advice> advice;

	/**
	 * 这些advice所对应的方法拦截器
	 */
	private List<MethodInterceptor> interceptors;

	/**
	 * matchers.get(i)表示第i个（从0开始）的advice拦截器所设置的匹配信息，如果没有设置，则匹配所有。如果设置了，
	 * 就按照设置的信息进行匹配
	 */
	private List<Matcher> matchers;

	/**
	 * 该对象实现的接口
	 */
	private Class<?>[] interfaces;

	/**
	 * 类加载器
	 */
	private ClassLoader classLoader;

	public AdvisedSupport(Object target, List<Advice> advice) {
		this.target = target;
		this.targetClass = target.getClass();
		this.advice = advice;
		this.interfaces = targetClass.getInterfaces();
		this.classLoader = targetClass.getClassLoader();
		this.interceptors = new ArrayList<MethodInterceptor>(advice.size());
		this.matchers = new ArrayList<Matcher>(advice.size());

		// 获取目前加载进来的advice的所有接口，这些接口才真正代表的是实际业务中插入的advice逻辑，将这些接口全部添加到拦截器集合中以便后面调用
		for (Advice a : advice) {
			for (Class<?> i : a.getClass().getInterfaces()) {
				// 将所有需要的advice添加到拦截器集合中
				interceptors.add((MethodInterceptor) ClassUtil.newInstance(
						i.getName() + "Interceptor", new Class[] { i },
						new Object[] { a }));

				// 将该advice对应的方法、类匹配信息添加到集合中
				matchers.add(new Matcher(i, a.getClass()));
			}
		}
	}

	public Object getTarget() {
		return target;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public List<Advice> getAdvice() {
		return advice;
	}

	public List<MethodInterceptor> getInterceptors() {
		return interceptors;
	}

	public Class<?>[] getInterfaces() {
		return interfaces;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public List<Matcher> getMatchers() {
		return matchers;
	}

	/**
	 * 
	 * @Title: hasInterfaces
	 * @Description: 被代理对象是否拥有接口
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean hasInterfaces() {
		return targetClass.getInterfaces().length > 0;
	}

}
