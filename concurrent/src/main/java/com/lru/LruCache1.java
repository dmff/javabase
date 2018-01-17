package com.lru;

import java.util.LinkedHashMap;
import java.util.Map;


public class LruCache1<K,V> extends LinkedHashMap<K, V>{

	private static final long serialVersionUID = 1L;
	
	private final int MAX_CACHE_SIZE;

	public LruCache1(int cacheSize) {
		//因为超过负载因子的大小，就需要扩容
		super((int)Math.ceil(cacheSize/0.75)+1,0.75f,true);
		MAX_CACHE_SIZE = cacheSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size()>MAX_CACHE_SIZE;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Map.Entry<K, V> entty : entrySet()){
			builder.append(String.format("%s:%s ", entty.getKey(),entty.getValue()));
		}
		
		return builder.toString();
	}
	
	
}
