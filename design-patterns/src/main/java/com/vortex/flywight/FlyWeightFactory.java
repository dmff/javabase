package com.vortex.flywight;

import java.util.HashMap;
import java.util.Map;

public class FlyWeightFactory {

	private static Map<String, ConcreteChess> map = new HashMap<String, ConcreteChess>();
	
	public static ConcreteChess getConcreteChess(String color){
		/*if (map.get(color) !=null) {
			return map.get(color);
		}else {
			map.put(color, new ConcreteChess(color));
			return map.get(color);
		}*/
		
		while (map.get(color) ==null) {
			map.put(color, new ConcreteChess(color));
		}
		
		return map.get(color);
	}
}
