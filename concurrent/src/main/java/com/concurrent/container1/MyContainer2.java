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
 * 分析下面这个程序，能完成这个功能吗？
 * @author dmf
 */
public class MyContainer2 {
	
	//使用volatile关键字
	volatile List<Object> lists = new ArrayList<>();
	
	public void add(Object obj){
		lists.add(obj);
	}
	
	public int size(){
		return lists.size();
	}
	
	public static void main(String[] args) {
		MyContainer2 cont = new MyContainer2();
		
		//往容器里面添加元素的线程，每个一秒添加一个
		new Thread(()->{
			for(int i=0;i<10;i++){
				cont.add(new Object());
				
				System.out.println("add "+i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				
		}, "AddThread").start();
		
		new Thread(()->{
			while(true){
				if (cont.size() == 5) {
					break;
				}
			}
			
			System.out.println("t2线程结束");
		}, "GetThread").start();
	}
}
