package com.concurrent.synchroniz;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁，读方法不加锁
 * 容易产生脏读的问题(dirtyRead)？
 * 	     读到没有写入的数据
 * @author dmf
 *
 */
public class T8_Account {
	
	String name;
	double balance;
	
	public synchronized void set(String name,double balance){
		this.name = name;
		//假设写入需要两秒的时间
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.balance = balance;
	}
	
	public /*synchronized*/ double getBalance(String name) {
		return balance;
	}
	
	public static void main(String[] args) {
		T8_Account account = new T8_Account();
		
		new Thread(()->account.set("dmf", 200.0), "setAccount").start();
		
		try {
			//主线程睡眠1秒钟
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(account.getBalance("dmf"));
		
		try {
			//主线程睡眠2秒钟
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(account.getBalance("dmf"));
	}
}
