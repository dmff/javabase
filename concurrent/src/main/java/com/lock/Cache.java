package com.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试读写锁：维护了一对读锁和写锁；当加上读锁时，其他线程依然可以获取读锁，而加上写锁时，不能获取读锁
 * 应用场景，假设在程序中定义一个共用的缓存数据结构，他大部分在提供读服务，
 * 			而写操作战友时间很少，但是写操作完成后需要更新且对后续的读服务可见
 * 		读写锁特点：1.公平、2.可重入、3.锁降级
 * 
 * 		读写锁的设计：在一个整数上维护多种状态，按位分割，高16位和低16位
 * 			读状态：S>>>16(在高16位加上16个0)，写状态：S&0x0000FFFF(将高16位抹去)，S为当前同步状态的值
 * 		锁降级是指把当前拥有写锁，在获取读锁时，时候释放写锁的过程
 *      写锁的获取和释放：一旦获取写锁，所有线程都会阻塞等待，必须等待其他线程都释放读锁才能获取写锁
 * @author dmf
 *
 */
public class Cache {

	private  static Map<String, Object> map = new HashMap<>();
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	static Lock r = readWriteLock.readLock();
	static Lock w = readWriteLock.writeLock();
	
	//获取一个key对应的value
	public static final Object get(String key){
		r.lock();
		try {
			return map.get(key);
		} finally {
			r.unlock();
		}
	}
	//返回旧的value
	public static final Object set(String key,Object value){
		w.lock();
		try {
			return map.put(key, value);
		} finally {
			w.unlock();
		}
	}
	
	public static final void clear(){
		w.lock();
		try {
			map.clear();
		} finally {
			w.unlock();
		}
	}
	
}
