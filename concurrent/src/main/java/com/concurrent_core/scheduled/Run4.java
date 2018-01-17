package com.concurrent_core.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**测试runnable周期性的执行任务:scheduleAtFixedRate
 * 测试执行任务的时间>period预定的周期时间，
 */
public class Run4 {

	public static void main(String[] args) {
		
//		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
		executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
		executor.setRemoveOnCancelPolicy(false);
		
		System.out.println("   X="+System.currentTimeMillis());
		//执行任务的时间大于周期性的执行时间
//		executor.scheduleAtFixedRate(new Runnable1(), 1, 2, TimeUnit.SECONDS);
		
		//执行任务的时间小于周期性的执行时间
//		executor.scheduleAtFixedRate(new Runnable1(), 1, 5, TimeUnit.SECONDS);
		
		//设置多个任务之间固定的运行时间间隔
//		executor.scheduleWithFixedDelay(new Runnable1(), 1, 2, TimeUnit.SECONDS);
		
		executor.scheduleWithFixedDelay(new Runnable1(), 1, 5, TimeUnit.SECONDS);
		System.out.println("   Y="+System.currentTimeMillis());
		
	}
}
