package com.concurrent_core.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试有无if语句
 * @author dmf
 *
 */
public class Run1 {

	public static void main(String[] args) {
		List<Callable<String>> list = new ArrayList<>();
		list.add(new MyCallableA());
		list.add(new MyCallableB1());   //无if
//		list.add(new MyCallableB2());   //有if
//		list.add(new MyCallableB3());   //没有捕获异常
//		list.add(new MyCallableB4());   //显示捕获异常
		
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		try {
			String value = executorService.invokeAny(list);
			System.out.println("================"+value);
			System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
