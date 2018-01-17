package com.concurrent_core.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 测试超时：指定全部任务在指定的时间没有完成，则抛出异常
 * 分三种情况：
 * 		1.先慢后快：慢的抛出CancellationException异常，而不是timeout异常，后面的for循环不会执行
 *      2.先快后慢：快的会有返回值，慢的会抛出CancellationException异常
 *      3.全慢：和先慢后快情形一样，通过for循环获取结果，第一次获取的时候出现异常，后面的不会执行
 * @author dmf
 *
 */
public class Run3_All3 {

	public static void main(String[] args) {
		List<Callable<String>> list = new ArrayList<>();

		//先慢后快
//		list.add(new AllCallableA2());
//		list.add(new AllCallableB2());
		
		//先快后慢
//		list.add(new AllCallableB2());
//		list.add(new AllCallableA2());
		
		//全慢，
		list.add(new AllCallableA2());
		list.add(new AllCallableA3());
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		try {
			
			System.out.println(" begin "+System.currentTimeMillis());
			List<Future<String>> all = executorService.invokeAll(list,2,TimeUnit.SECONDS);
			System.out.println(" end "+System.currentTimeMillis());
			
			
			for(int i=0;i<all.size();i++){
				System.out.println("for 第"+(i+1)+"次循环");
				System.out.println("返回结果 = "+all.get(i).get()+ " "+System.currentTimeMillis());
			
			}
			System.out.println("main end " +System.currentTimeMillis() );
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("InterruptedException ....");
		} catch (ExecutionException e) {
			e.printStackTrace();
			System.out.println("ExecutionException....");
		}
	}
}
