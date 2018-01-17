package com.concurrent_core.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool_ShutDowNow {

	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 9999, 9999L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
		for(int i=0;i<4;i++){
			executor.execute(()->{
				//使用	if (Thread.currentThread().isInterrupted()==true)的话没有执行完会进行判断
				/*try {
					if (Thread.currentThread().isInterrupted()==true) {
						System.out.println("任务没有完成，中断了");
						throw new InterruptedException();
					}
					System.out.println("任务完成了");
				} catch (InterruptedException e) {
					System.out.println("进入catch中断了任务...");
					e.printStackTrace();
				}*/
				
				System.out.println("任务完成了...");
			});
		}
		executor.shutdownNow();
		System.out.println(executor.isShutdown());
	}
}
