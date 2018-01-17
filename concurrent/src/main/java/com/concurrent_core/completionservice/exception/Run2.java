package com.concurrent_core.completionservice.exception;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;


/**
 * future没有调用get方法，是不会出现异常
 * @author dmf
 *
 */
public class Run2 {

	public static void main(String[] args) {
		CompletionService service = new ExecutorCompletionService<>(Executors.newCachedThreadPool());
		service.submit(new CallableA());  //先执行A
		service.submit(new CallableB());  //在执行B
		
		for(int i=0;i<2;i++){
			try {
				System.out.println("zzzzzzzzzzz,"+service.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("main end");
	}
}
