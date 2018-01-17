package com.concurrent_core.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试cancle方法
 * @author dmf
 *
 */
public class Run3 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2));
		
		//执行完毕之后的结果保存在future中
		Future<String> future = executor.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				int i=1;
				while(i == 1){
					System.out.println("zzzzzz");
				}
				return "我的年龄是100岁";
			}
		});
		
		Thread.sleep(2000);
		System.out.println(future.cancel(true)+" "+future.isCancelled());
		executor.shutdown();
	}
}
