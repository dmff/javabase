package com.lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LruCache2<K,V> {

	private final int MAX_CACHE_SIZE;
	private final float DEFAUT_LOAD_FACTORY = 0.75f;
	
	LinkedHashMap<K, V> map;

	@SuppressWarnings("serial")
	public LruCache2(int cachasize) {
		
		MAX_CACHE_SIZE = cachasize;
		//因为超过负载因子的大小，就需要扩容
		int capacity = (int) Math.ceil(cachasize/DEFAUT_LOAD_FACTORY)+1;
		map = new LinkedHashMap<K,V>(capacity, DEFAUT_LOAD_FACTORY, true){
			@Override
			protected boolean removeEldestEntry(Entry<K, V> eldest) {
				return size()>MAX_CACHE_SIZE;
			}
			
		};
	}
	
	public void put(K key,V value){
		map.put(key, value);
	}
	
	public void get(K key){
		map.get(key);
	}
	
	public void remove(K key){
		map.remove(key);
	}
	
	public void clear(){
		map.clear();
	}
	
	public int size(){
		return map.size();
	}
	
	public Set<Entry<K, V>> getAll(){
		return map.entrySet();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Entry<K, V> entty : map.entrySet()){
			builder.append(String.format("%s:%s ", entty.getKey(),entty.getValue()));
		}
		
		return builder.toString();
	}
	
}
