package com.concurrent_core.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试如果线程大于parties个数是，能不能记性分批处理？
 * 会的，因为是周期性的屏障，又会回到起点
 * 是可以实现分批进行比赛的
 * @author dmf
 *
 */
public class CyclicBarrierBigParies {

	private CyclicBarrier barrier;
	
	public CyclicBarrierBigParies(CyclicBarrier barrier) {
		super();
		this.barrier = barrier;
	}

	public void run(){
		try {
			System.out.println(Thread.currentThread().getName()+" begin ="+System.currentTimeMillis()
					+"等待凑齐2个继续执行");
			
			barrier.await();
			System.out.println(Thread.currentThread().getName()+" end ="+System.currentTimeMillis()
			+"等待凑齐2个继续执行");
		} catch ( BrokenBarrierException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
			@Override
			public void run() {
				System.out.println("全都到了");
			}
		});
		CyclicBarrierBigParies paries = new CyclicBarrierBigParies(barrier);
		
		for(int i=0;i<4;i++){
			Thread thread = new Thread(paries::run,"thread"+(i+1));
			thread.start();
			Thread.sleep(2000);
		}
	}
}
