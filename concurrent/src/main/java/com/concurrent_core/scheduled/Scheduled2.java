package com.concurrent_core.scheduled;

import java.util.concurrent.Callable;

public class Scheduled2 implements Callable<String>{

	@Override
	public String call() throws Exception {
		
		try {
			System.out.println("scheduled2 begin" + Thread.currentThread().getName()+" "+System.currentTimeMillis());
			System.out.println("scheduled2 end" + Thread.currentThread().getName()+" "+System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "returnB";
	}

}
