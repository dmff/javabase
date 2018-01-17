package com.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的概念，固定线程池的使用
 * @author dmf
 *
 */
public class T05_ThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		for(int i=0;i<6;i++){
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println(service);
		
		//关闭线程池，线程没有执行完会继续执行
		service.shutdown();
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
	}
}
