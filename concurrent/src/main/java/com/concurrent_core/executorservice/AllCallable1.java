package com.concurrent_core.executorservice;

import java.util.concurrent.Callable;

public class AllCallable1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName() + "begin "+System.currentTimeMillis());
		Thread.sleep(3000);
		System.out.println(Thread.currentThread().getName() + "end "+System.currentTimeMillis());
		return "returnA";
	}

}
