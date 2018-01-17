package com.concurrent_core.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

	static int a=1;
	
	public static void main(String[] args) {
		LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<Runnable>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, queue);
		for(int i=1;i<=50;i++){
			executor.execute(()->{
				System.out.println("runnable-"+a++);
			});
		}
				
	}
}
