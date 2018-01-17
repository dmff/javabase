package com.concurrent.concur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 写时复制容器copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 * @author dmf
 *
 */
public class T02_CopyOnWriteList {
	
	public static void main(String[] args) {
		List<String> lists = 
				new ArrayList<>(); //这个会出并发问题！
				//new Vector();
				//new CopyOnWriteArrayList<>();
		
		Random r = new Random();
		Thread[] ths = new Thread[100];
		
		for(int i=0; i<ths.length; i++) {
			ths[i] = new Thread(()->{
				for(int j=0;j<1000;j++){
					lists.add("a" + r.nextInt(10000));
				}
			});
		}
		
		runAndComputeTime(ths);
		System.out.println(lists.size());
	}
	
	static void runAndComputeTime(Thread[] ths) {
		long s1 = System.currentTimeMillis();
		Arrays.asList(ths).forEach(t->t.start());
		Arrays.asList(ths).forEach(t->{
			try {
				//线程依次执行
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		long s2 = System.currentTimeMillis();
		System.out.println(s2 - s1);
		
	}
}
