package com.concurrent_core.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * 测试pool超时实验：在指定时间内获取指定值继续往下执行，如果超时也立即向下执行
 * @author dmf
 *
 */
public class Run1 {

	public static void main(String[] args) throws InterruptedException {
		CompletionService service = new ExecutorCompletionService<>(Executors.newCachedThreadPool());
		
		service.submit(new CallableA());
		service.submit(new CallableB());
		
		Thread.sleep(6000);
		for(int i=0;i<2;i++){
			System.out.println("zzzzzzzzzzz,"+service.poll());
		}
		
		System.out.println("main end");
	}
}
