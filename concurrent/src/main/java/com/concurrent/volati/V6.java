package com.concurrent.volati;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某一个对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成了另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 * @author dmf
 *
 */
public class V6 {

	Object obj = new Object();
	
	void m() {
		synchronized(obj) {
			while(true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName());	
			}
		}
	}
	
	public static void main(String[] args) {
		V6 v6 = new V6();
		new Thread(v6::m, "v6_1").start();
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//创建第二个线程
		Thread t2 = new Thread(v6::m,"v6_2");
		
		//锁对象发生改变，所以t2线程得以执行，如果注释掉这句话，线程2将永远得不到执行机会
		//v6.obj = new Object();
		
		t2.start();
	}
}
