package com.container;

public class MyHashMap<K,V> {

	private static final int DEFAULT_INIT_CAPACITY = 16;  //默认数组的大小
	
	private static final float DEFAULT_LOAD_FACTORY = 0.75f;  //默认的负载因子
	
	private int threshold;  //临界值
	
	private int size;  //元素个数
	
	private int resize;  //扩容次数
	
	private HashEntry<K,V>[] table;
	
	@SuppressWarnings("unchecked")
	public MyHashMap() {
		super();
		table = new HashEntry[DEFAULT_INIT_CAPACITY];
		threshold = (int) (DEFAULT_INIT_CAPACITY*DEFAULT_LOAD_FACTORY);
		size = 0;
	}

	public void put(K key,V value){
		if (key == null) {
			//简化空值，不做处理
			return;
		}
		int index = index(key,table.length);
		HashEntry<K,V> entry = table[index];
		while(entry!=null){
			//如果key已经存在
			if (entry.getKey().hashCode() == key.hashCode() && 
					(entry.getKey()==key || entry.getKey().equals(key))) {
				//直接替换value值
				entry.value = value;
				return;
			}
			
			entry = entry.next;
		}
		
		//如果不存在在这样的entry，创建entry并添加到table中
		add(index,key,value);
	}
	
	private void add(int index, K key, V value) {
		//将新的entry放到table的index位置第一个，若原来有值则以链表形式存放
		HashEntry<K, V> entry = new HashEntry<K, V>(key, value, table[index]);
		table[index] = entry;
		
		//判断size是否达到临界值，若已达到则进行扩容，将table的capacicy翻倍
		if (size++>=threshold) {
			resize(table.length*2);
		}
	}

	//扩容
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		if (capacity<=table.length) {
			return;
		}
		
		@SuppressWarnings("rawtypes")
		HashEntry[] newTable = new HashEntry[capacity];
		//遍历原table，将每个entry都重新计算hash放入newTable中
		for(int i=0;i<table.length;i++){
			HashEntry<K, V> old = table[i];
			while(old!=null){
				HashEntry<K, V> next = old.next;
				int index = index(old.key,newTable.length);
				
				//old.next = newTable[index];  //这一步好像没什么用
				newTable[index] = old;
				old = next;
			}
		}
		
		//用newTable替table
		table = newTable;
		//修改临界值
		threshold = (int) (table.length * DEFAULT_LOAD_FACTORY);
		
		resize++;
		
	}

	public V get(K key){
		//简化空值处理，忽略空值
		if (key == null) {
			return null;
		}
		HashEntry<K,V> entry = getEntry(key);
		return entry == null ? null : entry.value;
	}
	
	public void remove(K key){
		if (key == null) {
			return;
		}
		
		int index = index(key,table.length);
		HashEntry<K,V> entry = table[index];
		HashEntry<K,V> pre = null;
		while(entry!=null){
			if (entry.getKey().hashCode() == key.hashCode() && 
					(entry.getKey()==key || entry.getKey().equals(key))) {
				
				if (pre==null) {
					table[index] = entry.next;  //设置头结点
				}else {
					pre.next = entry.next;
				}
				//如果成功删除，修改size的值
				size--;
				return;
			}
			
			pre = entry;
			entry =entry.next;
		}
	}
	
	public int  size(){
		return size;
	}
	
	public void clear(){
		for(int i=0;i<table.length;i++){
			table[i] = null;
		}
		size = 0;
	}
	
	public boolean containsKey(K key){
		return false;
	}
	
	public HashEntry<K, V> getEntry(K key){
		HashEntry<K,V> entry = table[index(key,table.length)];
		while(entry!=null){
			if (entry.getKey().hashCode() == key.hashCode() && 
					(entry.getKey()==key || entry.getKey().equals(key))) {
				return entry;
			}
			entry = entry.next;
		}
		return null;
	}
	
	private int index(K key,int length){
		//根据key的hashcode和table长度计算key在table中的位置
		return key.hashCode()%table.length;
	}
	
	@Override
	public String toString() {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append(String.format("size:%s capacity:%s resize:%s\n\n", size, table.length,resize));
		for(HashEntry<K, V> entry:table){
			while(entry!=null){
				sbuilder.append(String.format("%s:%s \n", entry.key,entry.value));
				entry =entry.next;
			}
		}
		return sbuilder.toString();
	}

	@SuppressWarnings("hiding")
	class HashEntry<K,V>{
		private final K key;
		private V value;
		private HashEntry<K, V> next;
		
		public HashEntry(K key, V value, HashEntry<K, V> next) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public HashEntry<K, V> getNext() {
			return next;
		}

		public K getKey() {
			return key;
		}		
	}
	
}
