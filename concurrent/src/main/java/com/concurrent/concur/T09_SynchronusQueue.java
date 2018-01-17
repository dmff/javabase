package com.concurrent.concur;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 容量为0的同步阻塞队列，put阻塞等待消费者消费
 * @author dmf
 *
 */
public class T09_SynchronusQueue {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		//strs.put("aaa"); //阻塞等待消费者消费
		strs.add("aaa");
		System.out.println(strs.size());
	}
}
