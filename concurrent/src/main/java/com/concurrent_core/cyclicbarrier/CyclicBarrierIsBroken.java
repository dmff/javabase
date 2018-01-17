package com.concurrent_core.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierIsBroken {

	CyclicBarrier barrier;

	public CyclicBarrierIsBroken(CyclicBarrier barrier) {
		super();
		this.barrier = barrier;
	}
	
	public void beginrun(int count){
		
		try {
			System.out.println(Thread.currentThread().getName()+" 到了 在等待处等待其他人都到了开始起跑");
			if (Thread.currentThread().getName().equals("thread-2")) {
				System.out.println("thread-2进来了");
				Thread.sleep(5000);
				
				//int a = 1/0;
				
				Thread.currentThread().interrupt();
			}
			
			barrier.await();
			System.out.println("都到了，开始跑");
			System.out.println(Thread.currentThread().getName()+" 到达终点，并结束第"+count+"阶段");
		} catch (InterruptedException e) {
			System.out.println("进入InterruptedException块 "+ barrier.isBroken());
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println("进入BrokenBarrierException块 "+ barrier.isBroken());
			e.printStackTrace();
		}	
	}
	
	public void test(){
		for(int i=0;i<1;i++){
			beginrun(i);
		}
	}
	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("都到了");
			}
		});
		
		CyclicBarrierIsBroken barrierIsBroken = new CyclicBarrierIsBroken(barrier);
		for(int i=0;i<4;i++){
			Thread thread = new Thread(barrierIsBroken::test,"thread-"+(i+1));
			thread.start();
		}
	}
}
