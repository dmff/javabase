package com.concurrent.volati;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile并不能保证多个线程共同修改running变量带来的不一致问题，也就说不能保证原子性
 * 不能使用volatile代替synchronized
 * 
 * 分析代码：启动10个线程，每个线程共享count，每次每个线程相加10000
 *           但是count会小于100000；因为当你写入的主内存的时候，其他线程
 *           把count读入到操作栈进行操作，最后完成写入的话会使得count变量偏小
 * @author dmf
 */
public class V2 {

	volatile int count = 0;
	void m(){
		for(int i=0;i<10000;i++)
			count++;
	}
	
	public static void main(String[] args) {
		V2 v2 = new V2();
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
