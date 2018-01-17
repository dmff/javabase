package com.concurrent_core.executorservice;

import java.util.concurrent.Callable;

public class MyCallableA implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println("MyCallableA begin "+System.currentTimeMillis());
		for(int i=0;i<123456;i++){
			Math.random();
			Math.random();
			Math.random();
			
			System.out.println("MyCallableA "+(i+1));
		}
		
		//测试执行快的任务出现异常
		int a=1;
		if (a==1) {
			System.out.println("***********中断了");
			throw new NullPointerException("heihei");
		}
		System.out.println("MyCallableA end "+System.currentTimeMillis());
		
		return "returnA";
	}

	
}
