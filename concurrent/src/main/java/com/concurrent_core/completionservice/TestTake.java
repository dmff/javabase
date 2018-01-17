package com.concurrent_core.completionservice;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试pool方法
 * @author dmf
 *
 */
public class TestTake {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		CompletionService cs = new ExecutorCompletionService<>(executorService);
		
		for(int i=0;i<1;i++){
			cs.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					Thread.sleep(3000);
					System.out.println("3秒过后");
					return "dmf:睡了3秒";
				}
			});
		}
		
		for(int i=0;i<1;i++){
			System.out.println(cs.poll());
		}
	}
}
