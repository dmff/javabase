package com.concurrent_core.executorservice;

import java.util.concurrent.Callable;

public class AllCallableA implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println("AllCallableA2 begin "+System.currentTimeMillis());
		for(int i=0;i<123456;i++){
			Math.random();
			Math.random();
			Math.random();
			
			System.out.println("AllCallableA2 "+(i+1));
		}
		
		System.out.println("AllCallableA2 end "+System.currentTimeMillis());
		
		//测试执行快的任务出现异常
		int a=1;
		if (a==1) {
			System.out.println("***********中断了");
			throw new NullPointerException("heihei");
		}
		return "returnA";
	}

	
}
