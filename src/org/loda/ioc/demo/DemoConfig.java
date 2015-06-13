package org.loda.ioc.demo;

import java.util.ArrayList;
import java.util.List;

import org.loda.config.SpringConfig;

public class DemoConfig implements SpringConfig {

	@Override
	public String[] basePath() {
		return new String[] { "org.loda.ioc" };
	}

	@Override
	public List<Class<?>> getTargetToProxy() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(AopDemo.class);
		return list;
	}

}
