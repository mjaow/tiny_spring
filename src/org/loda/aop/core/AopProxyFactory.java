package org.loda.aop.core;

import java.util.ArrayList;
import java.util.List;

import org.loda.aop.advice.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AopProxyFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(AopProxyFactory.class);

	private Object target;

	private List<Advice> adviceList = new ArrayList<Advice>();

	/**
	 * 
	 * @Title: setTarget
	 * @Description: 设置目标
	 * @param @param target 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void setTarget(Object target) {
		this.target = target;
	}

	public void addAdvice(Advice advice) {
		if (advice == null)
			throw new NullPointerException("添加的通知不能为空");
		adviceList.add(advice);
	}

	public Object getProxy() {
		if (target == null)
			throw new NullPointerException("目标对象不能为空");
		
		AdvisedSupport config=new AdvisedSupport(target, adviceList);

		AopProxy proxy = null;
		// 若该目标对象实现了接口，就优先选择jdk动态代理；如果没有实现任何接口，就只能采用cglib动态代理；
		if (config.hasInterfaces()) {
			logger.info("采用jdk动态代理");
			proxy = new JDKAopProxy(config);
		} else {
			logger.info("采用cglib动态代理");
			proxy = new CglibAopProxy(config);
		}
		return proxy.getProxy();
	}
}
