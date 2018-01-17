package com.concurrent.synchroniz;

/**
 * 分析程序输出
 * 启动5个线程执行count--
 *    	会乱序输出：为什么会乱序输出；需要了解多线程的原理，多线程其实是通过cpu执行，每个线程
 *    	分到cpu的一些片段执行，当没有执行完的时候，线程停止了count--之后；那么就会使得后面的
 *      线程先打印输出
 *      加上synchronized关键字的话，会锁住当前this对象，那么其他线程是不能访问this对象的，并且每次
 *      访问的时候会清除缓存，从主内存读入；这样就会顺序输出
 * @author dmf
 *
 */
public class T6 implements Runnable{
	
	private int count = 10;

	@Override
	public synchronized void run() {
		count --;
		System.out.println(Thread.currentThread().getName()+";count = "+count);
	}
	
	public static void main(String[] args) {
		T6 t5 = new T6();
		for(int i=0;i<5;i++){
			new Thread(t5,"THREAD"+i).start();
		}
	}

	
}
