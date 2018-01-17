package com.concurrent_core.future;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String>{

	private int age;
	
	public MyCallable(int age) {
		super();
		this.age = age;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		return "返回值 年齡是:"+age;
	}

}
