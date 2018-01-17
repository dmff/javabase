package com.vortex.proxy.Aop;

public class Test {

	public static void main(String[] args) {
		Worker worker = new Worker();
		Driver driver = new Driver();
		Coder coder = new Coder();
		
		worker = (Worker)new ProxyBuilder().setRealObj(worker).buildProxy();
		driver = (Driver)new ProxyBuilder().setRealObj(driver).buildProxy();
		coder = (Coder)new ProxyBuilder().setRealObj(coder).buildProxy();
		System.out.println("-------------------------------");
		worker.work();
		System.out.println("-------------------------------");
		driver.driver();
		driver.teach();
		System.out.println("-------------------------------");
		coder.code();
	}
}
