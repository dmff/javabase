package com.concurrent_core.scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**测试runnable延期执行任务
 * 
 */
public class Run3 {

	public static void main(String[] args) {
		List<Runnable> callables = new ArrayList<>();
		
		callables.add(new Runnable1());
		callables.add(new Runnable2());
		
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		
		System.out.println("   X="+System.currentTimeMillis());
		executor.schedule(callables.get(0), 4L, TimeUnit.SECONDS);
		executor.schedule(callables.get(1), 4L, TimeUnit.SECONDS);
		System.out.println("   Y="+System.currentTimeMillis());
		
	}
}
