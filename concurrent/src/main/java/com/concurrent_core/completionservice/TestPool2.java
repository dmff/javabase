package com.concurrent_core.completionservice;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试take()方法，获取最先执行完的future，如果不存在这样的任务，则等待
 * @author dmf
 *
 */
public class TestPool2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		CompletionService cs = new ExecutorCompletionService<>(executorService);
		
		for(int i=0;i<10;i++){
			cs.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					long sleep = (int) (Math.random()*1000);
					System.out.println("sleep ="+sleep+" "+Thread.currentThread().getName());
					Thread.sleep(sleep);
					return "dmf:"+sleep+" "+Thread.currentThread().getName();
				}
			});
		}
		
		for(int i=0;i<10;i++){
			System.out.println(cs.take().get());
		}
	}
}
