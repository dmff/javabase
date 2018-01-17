package com.vortex.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式之登记式模式，采用享元模式的思想
 * 不能懒加载
 * @author dmf
 *
 */
public class Singleton5 {
	
	private static Map<String,Singleton5> map = new HashMap<String,Singleton5>();
	
	/*static{  
        Singleton5 single5 = new Singleton5();  
        map.put(single5.getClass().getName(), single5);  
    }*/
	
	private  Singleton5() {}
	
	public static Singleton5 getInstance(String name){
		if (null ==name ||"".equals(name)) {
			name = Singleton5.class.getName();
		}
		
		//里面没有对象
		if (map.get(name) == null) {
			map.put(name, new Singleton5());
		}
		return map.get(name);
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				System.out.println(Singleton5.getInstance(null));
			}).start();;			
		}
	}
}
