package com.concurrent.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * 
 * @author dmf
 */
public class ThreadLocal1 {

	volatile static Person person = new Person();
	
	public static void main(String[] args) {
		/**
		 * 分析：使用了volatile关键字修饰person，person的修改对其他线程是可见的
		 */
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(person.name);
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			person.name = "lisi";
		}).start();
	}
	
	static class Person {
		String name = "zhangsan";
	}
}


