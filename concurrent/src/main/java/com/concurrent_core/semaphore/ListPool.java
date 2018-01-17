package com.concurrent_core.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 设置同时访问pool池中数据的线程数量
 * @author dmf
 *
 */
public class ListPool {

	private int poolMaxSize = 3;
	private int semaphorePermits = 5;
	private List<String> list = new ArrayList<String>();
	private Semaphore semaphore = new Semaphore(semaphorePermits);
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public ListPool() {
		super();
		for(int i=0;i<poolMaxSize;i++){
			list.add("dmf"+(i+1));
		}
	}
	
	public String get(){
		String str = null;
		try {
			semaphore.acquire();
			lock.lock();
			while(list.size()==0){
				condition.await();
			}
			
			str = list.remove(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		
		return str;
	}
	
	public void put(String value){
		lock.lock();
		try{
			list.add(value);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			semaphore.release();
		}
		
	}
}
