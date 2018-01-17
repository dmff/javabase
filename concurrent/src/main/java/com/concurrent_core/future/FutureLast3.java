package com.concurrent_core.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureLast3 implements Callable<String> {

	private String username;
	private long sleepvalue;

	public FutureLast3(String username, long sleepvalue) {
		super();
		this.username = username;
		this.sleepvalue = sleepvalue;
	}

	@Override
	public String call() throws Exception {
		System.out.println(username);
		Thread.sleep(sleepvalue);
		return "return "+username;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		FutureLast3 callable1 = new FutureLast3("name1", 5000);
		FutureLast3 callable2 = new FutureLast3("name2", 4000);
		FutureLast3 callable3 = new FutureLast3("name3", 3000);
		FutureLast3 callable4 = new FutureLast3("name4", 2000);
		FutureLast3 callable5 = new FutureLast3("name5", 1000);
		
		List<Callable<String>> list = new ArrayList<>();
		list.add(callable1);
		list.add(callable2);
		list.add(callable3);
		list.add(callable4);
		list.add(callable5);
		
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2));
		@SuppressWarnings("rawtypes")
		ExecutorCompletionService service = new ExecutorCompletionService<>(executor);
	
		for(int i=0;i<5;i++){
			service.submit(list.get(i));
		}
		
		//乱序打印的效果，说明一个future对应当前执行的任务
		for(int i=0;i<6;i++){
			try {
				System.out.println("等待打印 "+(i+1)+"返回值");
				System.out.println(service.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
