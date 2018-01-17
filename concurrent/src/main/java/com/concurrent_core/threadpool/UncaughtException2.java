package com.concurrent_core.threadpool;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UncaughtException2 {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		//除了可以在构造方法中设置threadfactory，也可以使用set方法进行赋值
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				2, 9999, 9999L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
					//可以在threadfactory中捕获异常
					@Override
					public Thread newThread(Runnable r){
						Thread thread = new Thread(r, "dmf");
						thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
							
							@Override
							public void uncaughtException(Thread t, Throwable e) {
								//打印异常日志
								System.out.println("自定义异常处理启用："+t.getName() +"发生"+e.getMessage());
								e.printStackTrace();
							}
						});
						return thread;
					}
				});
		
		executor.execute(()->{
			System.out.println(Thread.currentThread().getName());
			String a = null;
			a.indexOf(0);
		});
	}
}
