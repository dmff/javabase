package com.concurrent.concur;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞的同步队列，基于生产者-消费者的模式实现
 * @author dmf
 *
 */
public class T05_LinkedBlockingQueue {
	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
	static Random r = new Random();
	
	public static void main(String[] args) {
		//一个生产者往里面添加产品
		new Thread(()->{
			for(int i=0;i<100;i++){
				try {
					strs.put("a" + i); //如果满了，就会等待
					TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"p1").start();;
		
		//5个消费者等待消费
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (;;) {
					try {
						System.out.println(Thread.currentThread().getName() + " take -" + strs.take()); //如果空了，就会等待
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "c" + i).start();
		}
	}
}
