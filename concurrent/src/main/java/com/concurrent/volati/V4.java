package com.concurrent.volati;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比上一个程序，可以用synchronized解决，synchronized可以保证可见性和原子性，volatile只能保证可见性
 * @author dmf
 */
public class V4 {

	/*volatile*/ int count = 0;
	synchronized void m(){
		for(int i=0;i<10000;i++)
			count++;
	}
	
	public static void main(String[] args) {
		V4 v2 = new V4();
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
