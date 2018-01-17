package com.concurrent_core.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeOut {

	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 5, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		
		executor.allowCoreThreadTimeOut(true);
		System.out.println(executor.allowsCoreThreadTimeOut());
		for(int i=0;i<4;i++){
			executor.execute(()->{
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		Thread.sleep(7000);
		System.out.println(executor.getPoolSize());
	}
}
