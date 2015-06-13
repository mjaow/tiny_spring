package org.loda.ioc.demo;

public class AopDemo implements IAopDemo{

	public void doSomething() {
		System.out.println("和哈哈哈哈哈...");		
	}
	
	public void hehe(){
		System.out.println("呵呵");
		System.out.println(1/0);//主动抛出异常
	}

}
