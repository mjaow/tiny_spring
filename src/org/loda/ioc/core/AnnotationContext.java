package org.loda.ioc.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.loda.config.SpringConfig;
import org.loda.ioc.annotation.Component;
import org.loda.ioc.annotation.Inject;
import org.loda.ioc.exception.PackageNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: AnnotationContext
 * @Description: 基于注解环境的bean工厂
 * @author minjun
 * @date 2015年6月9日 上午11:48:26
 * 
 */
@SuppressWarnings("unchecked")
public class AnnotationContext extends ApplicationContext implements
		BeanFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(AnnotationContext.class);

	public AnnotationContext(SpringConfig config) {
		// 注册bean文件(ioc流程)
		register(config.basePath());
	}

	/**
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * 
	 * @Title: register
	 * @Description: 注册指定根路径下的bean
	 * @param @param basePath 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private <T> void register(String[] basePath) {
		if (basePath == null)
			throw new NullPointerException("basePath不能为空");

		// 将扫描到的类文件加载到spring的存储容器map中
		scan(basePath);

		// 然后将所有依赖的属性一一注入
		loadProperty();
	}

	/**
	 * 
	 * @Title: loadProperty
	 * @Description: 加载所有依赖
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private <T> void loadProperty() {
		for (Entry<String, Object> entry : container.entrySet()) {
			T t = (T) entry.getValue();
			for (Field f : t.getClass().getDeclaredFields()) {
				Inject inject = f.getAnnotation(Inject.class);
				if (inject != null) {
					try {
						// 不用根据set和get共有方法进行private属性设置，因为用户写起来太麻烦，直接暂时暴力破坏封装，通过提供
						// “可访问权限”来设置该属性
						f.setAccessible(true);
						Object obj = getInjectedBean(inject, f);
						if (obj == null) {
							logger.info("依赖对象为空");
							break;
						}
						f.set(t, obj);
					} catch (Exception e) {
						throw new RuntimeException("注入依赖属性失败", e);
					}
				}
			}
		}
	}

	private Object getInjectedBean(Inject inject, Field f) {
		// 按照以下优先级注入：@inject注解上配置的name->@inject注解上配置的class->属性变量名
		if (!StringUtils.isBlank(inject.name()))
			return getBean(inject.name());

		Object obj = getBean(inject.type());

		if (obj != null)
			return obj;

		return getBean(f.getName());

	}

	private <T> void scan(String[] basePath) {
		ClassLoader cl = getClassLoader();
		for (String packageName : basePath) {
			try {
				String packageFileName = packageName.replaceAll("\\.", "/");
				Enumeration<URL> urls = cl.getResources(packageFileName);

				if (urls == null)
					throw new PackageNotFoundException("没有找到这个包，请重新检查包名"
							+ packageName + "是正确");

				while (urls.hasMoreElements()) {
					URL u = urls.nextElement();

					String protocol = u.getProtocol();
					if ("file".equals(protocol)) {
						File file = new File(u.toString().substring(6));

						loadClass(file, packageName);
					}

				}

			} catch (IOException e) {
				logger.error("扫描bean文件失败", e);
			}
		}
	}

	private <T> void loadClass(File file, String packageName) {
		File[] children = file.listFiles();

		for (File child : children) {
			if (child.isFile()) {
				String name = child.getName();
				if (name.endsWith(".class")) {
					try {
						Class<? extends T> targetClass = (Class<? extends T>) Class
								.forName(packageName + "."
										+ name.substring(0, name.length() - 6));
						loadBean(targetClass);
					} catch (ClassNotFoundException e) {
						throw new PackageNotFoundException("包名" + packageName
								+ "填写错误导致类文件加载出现异常");
					}
				}
			} else if (child.isDirectory()) {
				loadClass(child, packageName + "." + child.getName());
			}
		}
	}

	/**
	 * 
	 * @Title: loadBean
	 * @Description: 加载所有注解上的component文件
	 * @param @param beans 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private <T> void loadBean(Class<? extends T> targetClass) {
		Component com = targetClass.getAnnotation(Component.class);
		if (com != null) {
			try {
				String key = null;
				T bean = targetClass.newInstance();
				if (!StringUtils.isBlank(com.value())) {
					key = com.value();
				} else {
					key = StringUtils.uncapitalize(targetClass.getSimpleName());
				}
				container.put(key, bean);

			} catch (Exception e) {
				throw new RuntimeException("无法访问" + targetClass.getName()
						+ "的空构造方法");
			}
		}
	}
}
