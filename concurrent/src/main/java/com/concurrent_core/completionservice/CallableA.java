package com.concurrent_core.completionservice;

import java.util.concurrent.Callable;

public class CallableA implements Callable<String>{

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		System.out.println("callableA "+System.currentTimeMillis());
		return "returnA";
	}

}
