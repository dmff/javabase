package com.concurrent_core.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Run1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2));
		
		//执行完毕之后的结果保存在future中
		Future<String> future = executor.submit(new MyCallable(100));
		System.out.println("main A "+System.currentTimeMillis());
		
		//阻塞的获取结果,时间间隔是线程执行的时间，如果没有阻塞，两者时间几乎相等
		//System.out.println(future.isDone());
		
		System.out.println(future.get());
		
		//任务没有运行完cancle返回true
		System.out.println(future.cancel(true)+" "+future.isCancelled());
		
		
		System.out.println("main B "+System.currentTimeMillis());
		executor.shutdown();
	}
}
