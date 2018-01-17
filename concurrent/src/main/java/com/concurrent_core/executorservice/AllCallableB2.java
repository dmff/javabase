package com.concurrent_core.executorservice;

import java.util.concurrent.Callable;

public class AllCallableB2 implements Callable<String> {

	@Override
	public String call() throws Exception {

		System.out.println("AllCallableB2 begin " + System.currentTimeMillis());
		for (int i = 0; i < 10; i++) {
			Math.random();
			Math.random();
			Math.random();
			System.out.println("AllCallableB2 " + (i + 1));
		}
		System.out.println("AllCallableB2 end " + System.currentTimeMillis());

		return "returnB";

	}
}
