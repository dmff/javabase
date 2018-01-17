package com.concurrent_core.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Run2 {

	public static void main(String[] args)  {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2));
		
		Future<?> future = executor.submit(()->{
			System.out.println("打印的信息");
		});
		
		try {
			//由此可知submit传入runnable的返回值为null，get具有阻塞性，isDone无阻塞性
			System.out.println(future.get()+"  "+future.isDone());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		executor.shutdown();
	}
}
