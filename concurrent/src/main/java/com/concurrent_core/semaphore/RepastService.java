package com.concurrent_core.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RepastService {

	private volatile Semaphore producerSemaphore = new Semaphore(10);
	private volatile Semaphore comsummerSemaphore = new Semaphore(20);
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition producerCondition = lock.newCondition();
	private Condition comsummerCondition = lock.newCondition();
	
	//容器，最多4个
	private volatile Object[] position = new Object[4];
	
	private boolean isEmpty(){
		boolean isEmpty = true;
		for(int i=0;i<position.length;i++){
			if (position[i]!=null) {
				isEmpty = false;
				break;
			}
		}
		return isEmpty;
	}
	
	private boolean isFull(){
		boolean isFull = true;
		for(int i=0;i<position.length;i++){
			if (position[i]==null) {
				isFull = false;
				break;
			}
		}
		return isFull;
	}
	
	public void put(){
		//System.out.println("set：");
		try {
			producerSemaphore.acquire();
			lock.lock();
			
			while(isFull()){
				System.out.println("生产者在等待");
				producerCondition.await();
			}
			
			for(int i=0;i<position.length;i++){
				if (position[i] == null) {
					position[i] = "数据";
					System.out.println(Thread.currentThread().getName()+"生产了"+position[i]);
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			comsummerCondition.signalAll();
			lock.unlock();
			producerSemaphore.release();
		}
	}
	
	public void get(){
		//System.out.println("get：");
		try {
			comsummerSemaphore.acquire();
			lock.lock();
			
			while(isEmpty()){
				System.out.println("消费者在等待");
				comsummerCondition.await();
			}
			
			for(int i=0;i<position.length;i++){
				if (position[i] != null) {
					System.out.println(Thread.currentThread().getName()+"消费了"+position[i]);
					//消费之后置空
					position[i] = null;
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			producerCondition.signalAll();
			lock.unlock();
			comsummerSemaphore.release();
		}
	}
	
	public static void main(String[] args) {
		RepastService service = new RepastService();
		Thread[] producer = new Thread[60];
		Thread[] comsummer = new Thread[60];
		
		for(int i=0;i<60;i++){
			//producer[i] = new Thread(()->service.put(),"thread"+(i+1));
			producer[i] = new Thread(service::put,"pthread"+(i+1));
			comsummer[i] = new Thread(()->service.get(),"cthread"+(i+1));
		}
		
		for(int i=0;i<60;i++){
			producer[i].start();
			comsummer[i].start();
		}
	}
}
