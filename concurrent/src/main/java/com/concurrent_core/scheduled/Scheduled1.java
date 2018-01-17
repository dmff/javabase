package com.concurrent_core.scheduled;

import java.util.concurrent.Callable;

public class Scheduled1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		
		try {
			System.out.println("scheduled1 begin " + Thread.currentThread().getName()+" "+System.currentTimeMillis());
			Thread.sleep(3000);	
			System.out.println("scheduled1 end " + Thread.currentThread().getName()+" "+System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "returnA";
	}

}
