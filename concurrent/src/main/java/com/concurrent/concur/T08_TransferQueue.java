package com.concurrent.concur;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 生产者会直接去找消费者消费，如果没有消费者
 * transfer阻塞等待消费者消费，put不会，会存入队列中
 * @author dmf
 *
 */
public class T08_TransferQueue {

	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
		
		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
		
		strs.transfer("aaa");
		
		//strs.put("aaa");
		

		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
