package com.container;

public class TestMap {

	public static void main(String[] args) {
		MyHashMap<String, Object> map = new MyHashMap<>();
		
		for(int i=0;i<100;i++){
			map.put(""+i, i);
		}
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		
		System.out.println(map.get("a"));
		System.out.println(map.get("b"));
		System.out.println(map.get("c"));
		
		System.out.println(map);
	}
}
