package com.concurrent.synchroniz;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 * @author dmf
 */
public class T91 {

	public synchronized void m1(){
		System.out.println(Thread.currentThread().getName()+" m1 start...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" m1 end...");
	}
	
	public static void main(String[] args) {
		new TT().m1();
	}
}

class TT extends T91{

	@Override
	public void m1() {
		System.out.println("child m1 start");
		super.m1();
		System.out.println("child m1 end");
	}
	
	
}
