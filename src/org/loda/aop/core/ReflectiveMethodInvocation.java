package org.loda.aop.core;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * @ClassName: ReflectiveMethodInvocation
 * @Description: 作为方法调用链的主执行器
 * @author minjun
 * @date 2015年6月11日 下午5:58:41
 * 
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

	/**
	 * 通知advice
	 */
	private List<MethodInterceptor> chain;

	/**
	 * 每个advice所配置的匹配信息
	 */
	private List<Matcher> matches;

	/**
	 * 执行目标方法需要的参数
	 */
	private Object[] arguments;

	/**
	 * 目标方法
	 */
	private Method method;

	/**
	 * 目标对象
	 */
	private Object target;
	
	/**
	 * 记录当前advice链条（chain）所需要执行的方法的索引
	 */
	private int index;

	public ReflectiveMethodInvocation(List<MethodInterceptor> chain,
			List<Matcher> matches, Object[] arguments, Method method,
			Object target) {
		this.chain = chain;
		this.matches = matches;
		this.arguments = arguments;
		this.method = method;
		this.target = target;
	}

	@Override
	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public AccessibleObject getStaticPart() {
		return method;
	}

	@Override
	public Object getThis() {
		return target;
	}

	@Override
	public Object proceed() throws Throwable {
		//当链条走完的时候调用目标方法
		if (index == chain.size())
			return invokeJoinpoint();

		Matcher matcher = matches.get(index);

		// 查看是否匹配，
		if (matcher.matches(this.method, this.target.getClass())) {
			return chain.get(index++).invoke(this);
		} else {
			index++;
			return proceed();
		}

	}

	/**
	 * 
	 * @Title: invokeJoinpoint
	 * @Description: 调用连接点方法
	 * @param @return
	 * @param @throws Throwable 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	protected Object invokeJoinpoint() throws Throwable {
		return method.invoke(target, arguments);
	}

	@Override
	public Method getMethod() {
		return method;
	}

}
