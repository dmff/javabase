package com.concurrent.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 * 
 * @author dmf
 */
public class ThreadLocal2 {

	//volatile static Person person = new Person();
	//线程里面的变量是私有的
	static ThreadLocal<Person> tl = new ThreadLocal<>();
	
	public static void main(String[] args) {
		/**
		 * 分析：使用了volatile关键字修饰person，person的修改对其他线程是可见的
		 * 使用threadlocal，线程2是读取不到线程1里面变量的变化
		 */
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(tl.get());
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tl.set(new Person());
		}).start();
	}
	
	static class Person {
		String name = "zhangsan";
	}
}


