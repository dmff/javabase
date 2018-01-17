package com.concurrent_core.threadpool;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DiscardOldestPolicy {

    static int a=1;
	public static void main(String[] args) throws InterruptedException {
		LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<Runnable>(2);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, queue, 
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		for(int i=0;i<5;i++){
			executor.execute(()->{
				System.out.println("runnable-"+(a++)+"-run");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		
		Thread.sleep(50);
		
		Iterator<Runnable> iterator = queue.iterator();
		while (iterator.hasNext()) {
			Runnable runnable = (Runnable) iterator.next();
			System.out.println(runnable);
		}
		
		executor.execute(()->{
			System.out.println("runnable-"+6+"-run");
		});
		
		executor.execute(()->{
			System.out.println("runnable-"+7+"-run");
		});
		
	    iterator = queue.iterator();
		while (iterator.hasNext()) {
			Runnable runnable = (Runnable) iterator.next();
			System.out.println(runnable);
		}
	}
}
