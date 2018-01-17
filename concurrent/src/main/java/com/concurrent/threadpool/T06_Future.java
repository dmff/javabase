package com.concurrent.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * future,run方法执行完之后的返回值
 * future和futuretask的区别：
 * 		future表示返回值，执行的返回值存入futer中，
 * 		futuretask表示执行任务，执行完之后可以从任务重拿到结果，是阻塞的拿到结果
 * @author dmf
 *
 */
public class T06_Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		}); // //new Callable () { Integer call();}
		
		new Thread(task).start();
		
		System.out.println(task.get());//阻塞
		//*******************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> f = service.submit(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		System.out.println(f.get());
		System.out.println(f.isDone());
	}
}
