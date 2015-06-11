package org.loda.ioc.demo;

import org.loda.config.SpringConfig;

public class DemoConfig implements SpringConfig{

	@Override
	public String[] basePath() {
		return new String[]{"org.loda.ioc"};
	}

}
