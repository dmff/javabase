package com.concurrent.synchroniz;

/**
 * 使用synchronized关键字，对某个对象加锁
 * 任何线程要执行synchronized里面的代码，必须先拿到对象的锁
 * @author dmf
 *
 */
public class T4 {

	private static int count = 10;
	//private Object obj = new Object();
	
	public static synchronized void m(){  //在方法上加synchronized等同于锁住当前对象,static方法锁住当前class对象
			count --;
			System.out.println(Thread.currentThread().getName()+";count = "+count);	
	}
	
	public static void mm(){
		synchronized (T4.class) {  //是不能写this的，因为static方法栈没有this对象的引用
			count --;
		}
	}
}
