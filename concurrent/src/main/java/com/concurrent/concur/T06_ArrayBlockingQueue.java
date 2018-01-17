package com.concurrent.concur;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 有界数组阻塞同步队列
 * @author dmf
 *
 */
public class T06_ArrayBlockingQueue {
	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
	static Random r = new Random();
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);
		}
		
		//strs.put("aaa"); //满了就会等待，程序阻塞
		//strs.add("aaa"); //会抛出异常，queue full
		strs.offer("aaa"); //满了可阻塞的等待
		//strs.offer("aaa", 1, TimeUnit.SECONDS);
		
		System.out.println(strs);
	}
}
