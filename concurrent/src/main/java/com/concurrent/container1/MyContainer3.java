package com.concurrent.container1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 
 * 
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 * 
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * 
 * 阅读下面的程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出
 * 分析下面这个程序，能完成这个功能吗？
 * @author dmf
 */
public class MyContainer3 {
	
	//使用volatile关键字,使得t2可以得到通知
	volatile List<Object> lists = new ArrayList<>();
	
	public void add(Object obj){
		lists.add(obj);
	}
	
	public int size(){
		return lists.size();
	}
	
	public static void main(String[] args) {
		MyContainer3 cont = new MyContainer3();
		
		//往容器里面添加元素的线程，每个一秒添加一个
		final Object lock = new Object();
		
		new Thread(()->{
			synchronized (lock) {
				if (cont.size() !=5) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("t2线程结束");
			}
		}, "t2").start();
		
		//主线程休眠一秒钟
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		new Thread(()->{
			System.out.println("t1启动");
			synchronized(lock) {
				for(int i=0; i<10; i++) {
					cont.add(new Object());
					System.out.println("add " + i);
					
					if(cont.size() == 5) {
						//唤醒所有的线程，但是当前线程并没有释放lock，所以会等到释放锁之后进入同步队列获取锁执行
						lock.notify();
					}
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();;
	}
}
