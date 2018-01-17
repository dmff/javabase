package com.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用链表+hashmap实现非同步的lru
 * @author dmf
 *
 * @param <K>
 * @param <V>
 */
public class LruCache3<K,V> {

	private Entry first;
	private Entry last;
	
	private final int MAX_CACHE_SIZE;
	private HashMap<K, Entry<K,V>> hashmap;
	
	
	public LruCache3(int cachesize) {
		super();
		MAX_CACHE_SIZE = cachesize;
		hashmap = new HashMap<K,Entry<K,V>>();
	}


	public void put(K key,V value){
		Entry<K, V> entry = getEntry(key);
		//往map里面put操作，判断entry是否存在
		if (entry == null) {
			//判断是否超出容量，超出移除last，解除entry链的关系
			if (hashmap.size()>=MAX_CACHE_SIZE) {
				hashmap.remove(last.key);
				removeLast();
			}
			entry = new Entry<>();
			entry.key = key;
		}
		//如果entry存在，则替换
		entry.value = value;
		moveFirst(entry);
		hashmap.put(key, entry);
	}
	
	public V get(K key){
		Entry<K, V> entry = getEntry(key);
		if (entry == null) {
			return null;
		}
		moveFirst(entry);
		return entry.value;	
	}
	
	public void remove(K key){
		Entry<K, V> entry = getEntry(key);
		if (entry!=null) {
			//改变entry在链表中的结构
			if(entry.pre!=null)  entry.pre.next = entry.next;
			if(entry.next!=null)  entry.next.pre = entry.pre;
			if(entry==first) first = entry.next;
			if (entry == last) last = entry.pre; 
		}
		
		hashmap.remove(key);
	}
	
	private Entry<K, V> getEntry(K key){
		return hashmap.get(key);
	}

	private void removeLast(){
		if (last!=null) {
			last = last.pre;
			if (last == null) 
				first = null;
			else
				last.next = null;
		}
	}
	
	//把entry移动到first节点,每次添加达到最新的目的
	private void moveFirst(Entry<K, V> entry){
		if(entry == first) return;
		if(entry.pre !=null) entry.pre.next = entry.next;
		if(entry.next !=null) entry.next.pre = entry.pre;
		if (entry == last) last = entry.pre;
		
		if (first == null || last ==null) {
			first = last = entry;  //???
			return;
		}
		
		entry.next = first;
		first.pre = entry;
		first = entry;
		entry.pre = null;
		
	}
	
	class Entry<K,V>{
		public Entry pre;
		public Entry next;
		public K key;
		public V value;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Entry<K, V> entry = first;
		while(entry!=null){
			builder.append(String.format("%s:%s ", entry.key,entry.value));
			entry = entry.next;
		}
		
		return builder.toString();
	}
}
