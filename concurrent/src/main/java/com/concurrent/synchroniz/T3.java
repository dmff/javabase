package com.concurrent.synchroniz;

/**
 * 使用synchronized关键字，对某个对象加锁
 * 任何线程要执行synchronized里面的代码，必须先拿到对象的锁
 * @author dmf
 *
 */
public class T3 {

	private int count = 10;
	//private Object obj = new Object();
	
	public synchronized void m(){  //在方法上加synchronized等同于锁住当前对象
			count --;
			System.out.println(Thread.currentThread().getName()+";count = "+count);
		
	}
}
