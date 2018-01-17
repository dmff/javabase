package com.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestLru {

	public static void main(String[] args) {
		lruCache1();
		lruCache2();
		lruCache3();
		lruCache4();
	}
	
	static void lruCache1() {
		System.out.println();
		System.out.println("===========================LRU LinkedHashMap(inheritance)实现===========================");
		LruCache1<Integer, String> lru = new LruCache1(5);
		lru.put(1, "11");
		lru.put(2, "11");
		lru.put(3, "11");
		lru.put(4, "11");
		lru.put(5, "11");
		System.out.println(lru.toString());
		lru.put(6, "66");
		lru.get(2);
		lru.put(7, "77");
		lru.get(4);
		System.out.println(lru.toString());
		System.out.println();
	}

	static <T> void lruCache2() {
		System.out.println();
		System.out.println("===========================LRU LinkedHashMap(delegation)实现===========================");
		LruCache2<Integer, String> lru = new LruCache2(5);
		lru.put(1, "11");
		lru.put(2, "11");
		lru.put(3, "11");
		lru.put(4, "11");
		lru.put(5, "11");
		System.out.println(lru.toString());
		lru.put(6, "66");
		lru.get(2);
		lru.put(7, "77");
		lru.get(4);
		System.out.println(lru.toString());
		System.out.println();
	}

	static void lruCache3() {
		System.out.println();
		System.out.println("===========================LRU 链表实现===========================");
		LruCache3 <Integer, String> lru = new LruCache3(5);
		lru.put(1, "11");
		lru.put(2, "11");
		lru.put(3, "11");
		lru.put(4, "11");
		lru.put(5, "11");
		System.out.println(lru.toString());
		lru.put(6, "66");
		lru.get(2);
		lru.put(7, "77");
		lru.get(4);
		System.out.println(lru.toString());
		System.out.println();
	}

	static void lruCache4() {
		System.out.println();
		System.out.println("===========================FIFO LinkedHashMap默认实现===========================");
		final int cacheSize = 5;
		LinkedHashMap<Integer, String> lru = new LinkedHashMap<Integer, String>() {
			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
				return size() > cacheSize;
			}
		};
		lru.put(1, "11");
		lru.put(2, "11");
		lru.put(3, "11");
		lru.put(4, "11");
		lru.put(5, "11");
		System.out.println(lru.toString());
		lru.put(6, "66");
		lru.get(2);
		lru.put(7, "77");
		lru.get(4);
		System.out.println(lru.toString());
		System.out.println();
	}
}
