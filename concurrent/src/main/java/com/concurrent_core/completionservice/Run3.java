package com.concurrent_core.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试pool超时实验：在指定时间内获取指定值继续往下执行，如果超时也立即向下执行
 * @author dmf
 *
 */
public class Run3 {

	public static void main(String[] args) throws InterruptedException {
		CompletionService service = new ExecutorCompletionService<>(Executors.newCachedThreadPool());
		
		service.submit(new CallableA());
		service.submit(new CallableB());
		
		try {
			
			/**
			 * 从运行结果知，等待时间是累加的，一开始等待2秒没有执行完，返回null，在等待2秒，总共等了4秒，
			 *   threadA执行完了，然后返回；在等待3秒，总共等了7秒，这样threadB也执行完，返回
			 */
			
			System.out.println("zzzzzzzzzzz,"+service.poll(2,TimeUnit.SECONDS)+" "+System.currentTimeMillis());
			System.out.println("X");
			
			System.out.println("zzzzzzzzzzz,"+service.poll(2,TimeUnit.SECONDS).get()+" "+System.currentTimeMillis());
			System.out.println("X");
			
			System.out.println("zzzzzzzzzzz,"+service.poll(3,TimeUnit.SECONDS).get()+" "+System.currentTimeMillis());
			System.out.println("X");
			
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("main end");
	}
}
