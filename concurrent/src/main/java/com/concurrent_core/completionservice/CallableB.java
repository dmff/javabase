package com.concurrent_core.completionservice;

import java.util.concurrent.Callable;

public class CallableB implements Callable<String>{

	@Override
	public String call() throws Exception {
		Thread.sleep(5000);
		System.out.println("callableB "+System.currentTimeMillis());
		return "returnB";
	}

}
