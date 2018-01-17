package com.concurrent.concur;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913 
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 * 
 * 使用hashmap线程不安全，使用put会造成死循环(entry形成环形结构)
 * 使用hashtable效率低，会把整个map都锁住
 * ConcurrentHashMap使用分段锁技术来提高效率；容器有多把锁，没把锁只锁定一小部分
 * 1.7有segment数组结构(重入锁)和hashentry数组结构组成
 * 1.8使用cas和红黑树构成
 */
public class T01_ConcurrentMap {

	public static void main(String[] args) {
		Map<String, String> map = new ConcurrentHashMap<>();
		//Map<String, String> map = new ConcurrentSkipListMap<>(); //高并发并且排序
		
		//Map<String, String> map = new Hashtable<>();
		//Map<String, String> map = new HashMap<>(); //Collections.synchronizedXXX
		//TreeMap
		
		Random r = new Random();
		Thread[] ths = new Thread[100];
		CountDownLatch latch = new CountDownLatch(ths.length);
		long start = System.currentTimeMillis();
		
		for(int i=0; i<ths.length; i++) {
			ths[i] = new Thread(()->{
				for(int j=0; j<10000; j++) map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
				latch.countDown();
			});
		}
		
		Arrays.asList(ths).forEach(t->t.start());
		
		//主线程等待
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
