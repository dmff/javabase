package com.concurrent_core.countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 实现完整的比赛流程
 * @author dmf
 *
 */
public class Runner {

	private CountDownLatch comingTag;  //裁判等待所有运动员到来
	private CountDownLatch waitTag;    //等待裁判说准备开始 
	private CountDownLatch waitRunTag; //等待起跑
	private CountDownLatch beginTag;   //起跑
	private CountDownLatch endTag;     //所有运动员到达终点
	
	public Runner(CountDownLatch comingTag, CountDownLatch waitTag, CountDownLatch waitRunTag, CountDownLatch beginTag,
			CountDownLatch endTag) {
		super();
		this.comingTag = comingTag;
		this.waitTag = waitTag;
		this.waitRunTag = waitRunTag;
		this.beginTag = beginTag;
		this.endTag = endTag;
	}
	
	public void run(){
		try {
			System.out.println("运动员使用不同交通工具不同速度到达起跑点正向这头走 !!!");
			Thread.sleep((int) (Math.random()*10000));
			System.out.println(Thread.currentThread().getName()+"到达起跑点了!!");
			comingTag.countDown();
			System.out.println("等待裁判说准备!");
			waitTag.await();
			
			System.out.println("各级格外，准备起跑姿势!");
			Thread.sleep((int) (Math.random()*10000));
			waitRunTag.countDown();
			
			beginTag.await();
			System.out.println(Thread.currentThread().getName()+"运动员起跑，并且比赛用时不确定!!");
			Thread.sleep((int) (Math.random()*10000));
			endTag.countDown();
			System.out.println(Thread.currentThread().getName()+"运动员到达终点!!");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch comingTag = new CountDownLatch(10);
		CountDownLatch waitTag = new CountDownLatch(1);
		CountDownLatch waitRunTag = new CountDownLatch(10);
		CountDownLatch beginTag = new CountDownLatch(1);
		CountDownLatch endTag = new CountDownLatch(10);
		
		Runner runner = new Runner(comingTag, waitTag, waitRunTag, beginTag, endTag);
		
		
		
		for(int i=0;i<10;i++){
			Thread thread = new Thread(runner::run,"thread-"+(i+1));
			thread.start();
		}
		
		System.out.println("裁判员在等待所有运动员的到来!");
		comingTag.await();
		
		System.out.println("裁判员看到所有运动员来了，各级各位前'巡视'用时5秒");
		Thread.sleep(5000);
		waitTag.countDown();
		
		System.out.println("各就各位!");
		waitRunTag.await();
		
		Thread.sleep(2000);
		System.out.println("发枪令响起!");
		beginTag.countDown();
		
		endTag.await();
		System.out.println("所有运动员到达，统计比赛名次!!");
	}
}
