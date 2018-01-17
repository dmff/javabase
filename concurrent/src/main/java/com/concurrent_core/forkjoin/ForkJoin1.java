package com.concurrent_core.forkjoin;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoin1 {

	//求1000000万个随机100以内的数的和
	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random r = new Random();
	
	static {
		for(int i=0; i<nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		
		System.out.println(Arrays.stream(nums).sum()); //stream api 
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		//默认采用计算机的现有的内核
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Mytask mytask = new Mytask(0, nums.length);
		
		forkJoinPool.execute(mytask);
		//Integer result = mytask.get();
		//System.out.println(result);
		
		System.in.read();
		
		
	}

/*	static class Mytask extends RecursiveTask<Integer>{

		private static final long serialVersionUID = 1L;
		private int begin;
		private int end;
		
		public Mytask(int begin, int end) {
			super();
			this.begin = begin;
			this.end = end;
		}
	
		@SuppressWarnings("static-access")
		@Override
		protected Integer compute() {
			if (end-begin<=MAX_NUM) {
				int sum=0;
				for(int i=begin;i<end;i++){
					sum = sum+nums[i];
				}
				return sum;
			} else {
				int mid = begin + (end - begin)/2;
				Mytask mytask1 = new Mytask(begin, mid);
				Mytask mytask2 = new Mytask(mid, end);
				
				//使用fork会创建大量的线程，是用invokeAll以优化的方式分解及运行任务
//				mytask1.fork();
//				mytask2.fork();
				this.invokeAll(mytask1, mytask2);
				return mytask1.join()+mytask2.join();
			}			
		}
		
	}*/
	
	static class Mytask extends RecursiveAction{
		private static final long serialVersionUID = 1L;
		private int begin;
		private int end;
		
		public Mytask(int begin, int end) {
			super();
			this.begin = begin;
			this.end = end;
		}

		@SuppressWarnings("static-access")
		@Override
		protected void compute() {
			if (end-begin<=MAX_NUM) {
				int sum=0;
				for(int i=begin;i<end;i++){
					sum = sum+nums[i];
				}
				System.out.println("from "+begin+" to "+end+" ="+sum);
			}else {
				int mid = begin + (end - begin)/2;
				Mytask mytask1 = new Mytask(begin, mid);
				Mytask mytask2 = new Mytask(mid, end);
				
//				mytask1.fork();
//				mytask2.fork();
				this.invokeAll(mytask1, mytask2);
			}			
		}
	}
}
