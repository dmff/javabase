package com.concurrent_core.completionservice.exception;

import java.util.concurrent.Callable;

public class CallableA implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println("callableA begin"+System.currentTimeMillis());
		Thread.sleep(1000);
		System.out.println("callableA end"+System.currentTimeMillis());
		return "returnA";
	}

}
