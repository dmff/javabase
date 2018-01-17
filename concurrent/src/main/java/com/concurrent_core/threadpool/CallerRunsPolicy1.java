package com.concurrent_core.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 这种方法会阻塞main线程，影响性能
 * @author dmf
 *
 */
public class CallerRunsPolicy1 {

	public static void main(String[] args) {
		LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<Runnable>(2);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, queue, 
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		System.out.println("a begin "+Thread.currentThread().getName()+":"+System.currentTimeMillis());
		for(int i=0;i<6;i++){
			executor.execute(()->{
				try {
					Thread.sleep(2000);
					System.out.println("end "+Thread.currentThread().getName()+":"+System.currentTimeMillis());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		
		System.out.println("a end "+Thread.currentThread().getName()+":"+System.currentTimeMillis());
	}
}
