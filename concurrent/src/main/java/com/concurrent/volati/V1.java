package com.concurrent.volati;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字，使一个变量在多个线程间可见
 * A、B线程都用到一个变量，java默认是A线程保留一份copy，这样如果B线程修改了该变量，则A下城未必可知
 * 使用volatile关键字，会让所有的线程都会读到变量的修改值
 * 
 * 在下面代码中，running是存在于堆内的v对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 * 
 * 
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 * 
 * 可以阅读这篇文章进行更深入的理解
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * 
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * @author dmf
 *
 */
public class V1 {

	//对比一下有无volatile的情况下，整个程序运行结果的区别
	volatile boolean running = true;
	
	void m(){
		System.out.println("m start");
		while(running) {
			
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("m end!");
	}
	
	public static void main(String[] args) {
		V1 v1 = new V1();
		
		//v1线程运行m方法
		new Thread(v1::m, "v1").start();
		
		try {
			//主线程休眠一秒
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		v1.running = false;
	}
}
