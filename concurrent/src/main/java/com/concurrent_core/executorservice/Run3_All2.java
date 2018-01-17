package com.concurrent_core.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试有无if语句
 * @author dmf
 *
 */
public class Run3_All2 {

	public static void main(String[] args) {
		List<Callable<String>> list = new ArrayList<>();

		list.add(new AllCallableA());
		list.add(new AllCallableB());
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		try {
			
			System.out.println(" begin "+System.currentTimeMillis());
			List<Future<String>> all = executorService.invokeAll(list);
			System.out.println(" end "+System.currentTimeMillis());
			
			
			for (Future<String> future : all) {
				System.out.println("返回结果 = "+future.get()+ " "+System.currentTimeMillis());
			}
			
			System.out.println("main end");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("InterruptedException ....");
		} catch (ExecutionException e) {
			e.printStackTrace();
			System.out.println("ExecutionException....");
		}
	}
}
