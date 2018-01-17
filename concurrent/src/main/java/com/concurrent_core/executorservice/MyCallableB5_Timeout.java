package com.concurrent_core.executorservice;

import java.util.concurrent.Callable;

public class MyCallableB5_Timeout implements Callable<String> {

	@Override
	public String call() throws Exception {

		try {
			System.out.println("MyCallableB begin " +Thread.currentThread().getName() +"begin"+ System.currentTimeMillis());
			for (int i = 0; i < 193456; i++) {
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				System.out.println("MyCallableB " + (i + 1));
				
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("============中断了");
					throw new NullPointerException();
				}
			}
			
			System.out.println("MyCallableB begin " +Thread.currentThread().getName() +"end"+ System.currentTimeMillis());
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("使用try-catch捕获异常");
			throw e;
		}
		
		return "returnB";
	}

}
