package com.concurrent_core.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureLast implements Callable<String> {

	private String username;
	private long sleepvalue;

	public FutureLast(String username, long sleepvalue) {
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
	
	public static void main(String[] args) {
		FutureLast callable1 = new FutureLast("name1", 5000);
		FutureLast callable2 = new FutureLast("name2", 4000);
		FutureLast callable3 = new FutureLast("name3", 3000);
		FutureLast callable4 = new FutureLast("name4", 2000);
		FutureLast callable5 = new FutureLast("name5", 1000);
		
		List<Callable<String>> list = new ArrayList<>();
		list.add(callable1);
		list.add(callable2);
		list.add(callable3);
		list.add(callable4);
		list.add(callable5);
		
		@SuppressWarnings("rawtypes")
		List<Future> futures = new ArrayList<>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2));
		for(int i=0;i<5;i++){
			futures.add(executor.submit(list.get(i)));
		}
	
		//这里是按顺序输出的，说明一个future对应一个callable，并且是阻塞的，只有拿到前一个的结果，才能去拿后一个的执行结果
		System.out.println("run first time "+System.currentTimeMillis());
		for(int i=0;i<5;i++){
			try {
				System.out.println(futures.get(i).get()+" "+System.currentTimeMillis());
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
