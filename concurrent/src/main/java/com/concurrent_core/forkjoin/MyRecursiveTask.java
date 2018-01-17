package com.concurrent_core.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 测试对异常的处理
 * @author dmf
 *
 */
public class MyRecursiveTask extends RecursiveTask<Integer>{

	private static final long serialVersionUID = 1L;

	@Override
	protected Integer compute() {
		try {
			Thread.sleep(1000);
			Integer.parseInt("a");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 100;
	}

	public static void main(String[] args) throws InterruptedException {
		MyRecursiveTask task = new MyRecursiveTask();
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Integer> forkJoinTask = pool.submit(task);
		
		System.out.println(forkJoinTask.isCompletedAbnormally() + " "+forkJoinTask.isCompletedNormally());
		Thread.sleep(2000);
		System.out.println(forkJoinTask.isCompletedAbnormally() + " "+forkJoinTask.isCompletedNormally());
		
		System.out.println(forkJoinTask.getException());
	}
}
