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
public class Run2 {

	public static void main(String[] args) throws InterruptedException {
		CompletionService service = new ExecutorCompletionService<>(Executors.newCachedThreadPool());
		
		service.submit(new CallableA());
		service.submit(new CallableB());
		
	
		for(int i=0;i<2;i++){
			try {
				//返回两个值，因为一共等了8秒
				System.out.println("zzzzzzzzzzz,"+service.poll(4,TimeUnit.SECONDS).get());
				System.out.println("X");
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("main end");
	}
}
