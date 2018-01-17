package com.concurrent_core.completionservice.exception;

import java.util.concurrent.Callable;

public class CallableB implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println("callableB begin"+System.currentTimeMillis());
		Thread.sleep(3000);
		
		int i=0;
		if (i==0) {
			throw new Exception("抛出异常!!");
		}
		System.out.println("callableB end"+System.currentTimeMillis());
		return "returnB";
	}

}
