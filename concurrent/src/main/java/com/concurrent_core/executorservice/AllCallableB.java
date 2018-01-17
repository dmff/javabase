package com.concurrent_core.executorservice;

import java.util.concurrent.Callable;

public class AllCallableB implements Callable<String> {

	@Override
	public String call() throws Exception {

		System.out.println("MyCallableB begin " + System.currentTimeMillis());
		for (int i = 0; i < 223456; i++) {
			Math.random();
			Math.random();
			Math.random();
			System.out.println("AllCallableB " + (i + 1));
		}
		System.out.println("AllCallableB end " + System.currentTimeMillis());
		
		/*//测试慢的异常，块的正常
		int a =1;
		if (a==1) {
			System.out.println("B报错了");
			throw new Exception("出现异常");
		}*/
		
		return "returnB";

	}
}
