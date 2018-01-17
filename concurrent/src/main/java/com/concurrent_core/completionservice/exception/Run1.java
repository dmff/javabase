package com.concurrent_core.completionservice.exception;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;


/**
 * future没有调用get方法，是不会出现异常
 * @author dmf
 *
 */
public class Run1 {

	public static void main(String[] args) {
		CompletionService service = new ExecutorCompletionService<>(Executors.newCachedThreadPool());
		service.submit(new CallableA());
		service.submit(new CallableB());
		
		for(int i=0;i<2;i++){
			try {
				System.out.println("zzzzzzzzzzz,"+service.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("main end");
	}
}
