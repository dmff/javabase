package com.concurrent_core.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 缓冲线程池，可以使用线程工厂定制特定的线程
 * 			    内部是使用的SynchronousQueue队列
 * @author dmf
 *
 */
public class CachePool {

	public static void main(String[] args) throws InterruptedException {
		
		//ExecutorService executor = Executors.newCachedThreadPool();
		ExecutorService executor = Executors.newCachedThreadPool(new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "定制池的线程名称:"+Math.random());
			}
		});
		
		for(int i=0;i<2;i++){
			executor.execute(()->{
				try {
					Thread.sleep(500);
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		
		System.out.println(executor);
		TimeUnit.SECONDS.sleep(2);
		System.out.println(executor);
		
		executor.shutdown();
	}
}
