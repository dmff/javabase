package com.concurrent_core.executorservice;

import java.util.concurrent.Callable;

public class MyCallableB4 implements Callable<String> {

	@Override
	public String call() throws Exception {

		try {
			System.out.println("MyCallableB begin " +Thread.currentThread().getName() +"begin"+ System.currentTimeMillis());
			for (int i = 0; i < 193456; i++) {
				Math.random();
				Math.random();
				Math.random();
				System.out.println("MyCallableB " + (i + 1));
			}
			// 测试执行慢的线程出现异常，是不会在控制台输出的，使用try-catch显示捕获异常
			int a = 1;
			if (a == 1) {
				System.out.println("****中断了");
				throw new NullPointerException();
			}
			System.out.println("MyCallableB begin " +Thread.currentThread().getName() +"end"+ System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage()+" 使用try-catch显示捕获异常");
			throw e;
		}
		return "returnB";
	}

}
