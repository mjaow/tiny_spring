package org.loda.ioc.demo;

import org.junit.Assert;
import org.junit.Test;
import org.loda.aop.core.AopProxyFactory;
import org.loda.ioc.core.AnnotationContext;
import org.loda.ioc.core.BeanFactory;
import org.loda.ioc.demo.controller.UserController;
import org.loda.ioc.demo.model.User;

/**
 * 
 * @ClassName: TestDemo
 * @Description: 入门案例
 * 
 * @author minjun
 * @date 2015年6月9日 下午2:40:38
 * 
 */
public class TestDemo {

	@Test
	public void testIoc() throws Exception {
		// 创建工厂对象，并将配置信息DemoConfig设置进去，工厂对象会自动在后台完成对象装载
		BeanFactory bf = new AnnotationContext(new DemoConfig());

		// 从bean工厂中根据类型（也可以根据名字）取出对象
		UserController uc = bf.getBean(UserController.class);

		// 断言不能为空
		Assert.assertNotNull(uc);

		// 完成添加动作（如果成功依赖注入，那么这个流程肯定可以走完）
		uc.add(new User(1, "jack", "123456"));

	}

	@Test
	public void testAop() throws Exception {
		// 创建动态代理工厂，这是调用动态代理实现aop的初始点
		AopProxyFactory proxy = new AopProxyFactory();

		// 创建目标对象
		proxy.setTarget(new AopDemo());

		// 设置各个advice，以便在调用目标对象的指定方法时可以出现这些advice
		proxy.addAdvice(new LogBefore());
		proxy.addAdvice(new LogError());
		proxy.addAdvice(new LogAfter());

		// 获取代理对象
		AopDemo p = (AopDemo) proxy.getProxy();

		// 通过代理对象调用目标对象的指定方法
		p.doSomething();
	}

	@Test
	public void testTranscation() {
		// 创建动态代理工厂，这是调用动态代理实现aop的初始点
		AopProxyFactory proxy = new AopProxyFactory();

		// 创建目标对象
		proxy.setTarget(new AopDemo());

		// 设置各个advice，以便在调用目标对象的指定方法时可以出现这些advice
		proxy.addAdvice(new TransactionAspect());

		// 获取代理对象
		IAopDemo p = (IAopDemo) proxy.getProxy();

		// 通过代理对象调用目标对象的指定方法
		p.doSomething();
	}
	
	@Test
	public void testInterface(){
		IAopDemo a=new AopDemo();
		System.out.println(a.getClass().getInterfaces()[0].isInterface());
	}
}
