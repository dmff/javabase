package com.concurrent_core.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UncaughtException1 {

	public static void main(String[] args) {
		//除了可以在构造方法中设置threadfactory，也可以使用set方法进行赋值
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				2, 9999, 9999L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
					
					@Override
					public Thread newThread(Runnable r){
						return new Thread(r, "dmf");
					}
				});
		
		executor.execute(()->{
			System.out.println(Thread.currentThread().getName());
		});
	}
}
