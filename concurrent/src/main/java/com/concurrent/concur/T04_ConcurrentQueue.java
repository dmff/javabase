package com.concurrent.concur;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 非阻塞的同步队列
 * @author dmf
 *
 */
public class T04_ConcurrentQueue {
	
	public static void main(String[] args) {
		Queue<String> strs = new ConcurrentLinkedQueue<>();
		
		for(int i=0;i<10;i++){
			strs.offer("a"+i);
		}
		
		System.out.println(strs);
		
		//移出队头的元素
		System.out.println(strs.size());
		
		System.out.println(strs.poll());
		
		System.out.println(strs.size());
		
		//队头的元素，并不会移出
		System.out.println(strs.peek());
		
		System.out.println(strs.size());
	}
}
