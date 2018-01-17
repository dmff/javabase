package com.concurrent_core.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 测试超时异常
 * @author dmf
 *
 */
public class Run2 {

	public static void main(String[] args) {
		List<Callable<String>> list = new ArrayList<>();
		list.add(new MyCallableB5_Timeout());
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		try {
			//String value = executorService.invokeAny(list);
			String value = executorService.invokeAny(list, 1L, TimeUnit.SECONDS);
			System.out.println("================"+value);
			System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZ");
		} catch (InterruptedException e) {
			System.out.println("进入  InterruptedException");
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("进入  ExecutionException");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("进入  TimeoutException");
			e.printStackTrace();
		}
	}
}
