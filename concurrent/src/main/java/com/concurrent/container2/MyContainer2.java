package com.concurrent.container2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 
 * 使用wait和notify/notifyAll来实现
 * 
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 * @author dmf
 * @param <T>
 */
public class MyContainer2<T> {

	final private LinkedList<T> lists = new LinkedList<T>();
	final private int MAX = 10;//最多10个元素
	private int count = 0;
	
	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();
	
	public  void put(T t){
		lock.lock();
		try {
			
			while(lists.size() == MAX){ //想想为什么用while而不是用if？
				try {
					//容器满了，生产者等待
					producer.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//容器还有容量
			lists.add(t);
			++count;
			//通知所有消费者消费
			consumer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public  T get() {
		T t = null;
		lock.lock();
		try {
			while(lists.size() == 0) {
				try {
					//消费者等待
					consumer.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			t = lists.removeFirst();
			count --;
			//通知所有生产者进行生产
			producer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}
	
	public static void main(String[] args) {
		MyContainer2<String> c = new MyContainer2<>();
		//启动消费者线程
		for(int i=0; i<10; i++) {
			new Thread(()->{
				for(int j=0; j<5; j++) 
					System.out.println(c.get());
			}, "c" + i).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//启动生产者线程
		for(int i=0; i<2; i++) {
			new Thread(()->{
				for(int j=0; j<25; j++) 
					c.put(Thread.currentThread().getName() + " " + j);
			}, "p" + i).start();
		}
	}
}
