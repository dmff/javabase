package com.concurrent_core.executorservice;

import java.util.concurrent.Callable;

public class AllCallableA3 implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("AllCallableA3 begin " + System.currentTimeMillis());
		for (int i = 0; i < 223456; i++) {
			Math.random();
			Math.random();
			Math.random();

			System.out.println("AllCallableA3 " + (i + 1));
		}

		System.out.println("MyCallableA3 end " + System.currentTimeMillis());

		return "returnA";
	}

}
