package com.concurrent_core.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 实现所有线程都到达同步点在继续运行
 * @author dmf
 *
 */
public class CyclicBarrierBegin {

	private CyclicBarrier barrier;

	public CyclicBarrierBegin(CyclicBarrier barrier) {
		super();
		this.barrier = barrier;
	}
	
	public void run(){
		try {
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName()+"到了"+System.currentTimeMillis());
			
			//Thread.sleep(Long.MAX_VALUE);
			
			//会再次等待，等待所有的线程都到达同步点
			barrier.await();
			
			System.out.println(Thread.currentThread().getName()+"继续往下执行");
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				System.out.println("全都到了");
			}
		});
		CyclicBarrierBegin begin = new CyclicBarrierBegin(barrier);
		
		for(int i=0;i<5;i++){
			Thread thread = new Thread(begin::run,"thread"+(i+1));
			thread.start();
		}
	}
}
