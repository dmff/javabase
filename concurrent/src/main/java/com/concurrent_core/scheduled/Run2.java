package com.concurrent_core.scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 采用多线程的执行任务
 */
public class Run2 {

	public static void main(String[] args) {
		List<Callable<String>> callables = new ArrayList<>();
		
		callables.add(new Scheduled1());
		callables.add(new Scheduled2());
		
		//ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		
		ScheduledFuture<String> future1 = executor.schedule(callables.get(0), 4L, TimeUnit.SECONDS);
		ScheduledFuture<String> future2 = executor.schedule(callables.get(1), 4L, TimeUnit.SECONDS);
		
		
		try {
			System.out.println("   X="+System.currentTimeMillis());
			System.out.println("返回值1："+future1.get());
			System.out.println("返回值2："+future2.get());
			System.out.println("   Y="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
