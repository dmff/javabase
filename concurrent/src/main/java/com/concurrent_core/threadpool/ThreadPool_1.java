package com.concurrent_core.threadpool;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool_1 {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
		for(int i=0;i<2;i++){
			executor.execute(()->{
				try {
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+" run "+ new Date(System.currentTimeMillis()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		
		//标准人数
		System.out.println(executor.getCorePoolSize());
		//最大载人数
		System.out.println(executor.getMaximumPoolSize());
		//正在载人数
		System.out.println(executor.getPoolSize());
		//扩展车中的载人数
		System.out.println(executor.getQueue().size());
		
		executor.shutdown();
	}
}
