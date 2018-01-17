package com.concurrent.volati;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *对比上一个程序，可以用synchronized解决，synchronized可以保证可见性和原子性，volatile只能保证可见性
 *解决同样的问题的更高效的方法：使用AtomXXX类
 *
 *AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 * @author dmf
 */
public class V3 {

	/*volatile*/ //int count = 0;
	
	AtomicInteger count = new AtomicInteger(0);
	/*synchronized*/ void m(){
		for(int i=0;i<10000;i++)
			//采用CAS进行赋值
			count.incrementAndGet();
	}
	
	public static void main(String[] args) {
		V3 v2 = new V3();
		//线程容器，装有10个线程,每次打印当前count
		List<Thread> threads = new ArrayList<Thread>();
		for(int i=0; i<10; i++){
			threads.add(new Thread(()->v2.m(), "thread-"+i));
		}
		
		//启动所有线程
		threads.forEach((o)->o.start());
		//等待所有线程执行完成
		threads.forEach((o)->{
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println(v2.count);
	}
}
