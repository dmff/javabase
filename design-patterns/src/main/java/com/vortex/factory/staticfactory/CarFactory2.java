package com.vortex.factory.staticfactory;

import java.io.IOException;
import java.util.Properties;

public class CarFactory2 {
	
	private static Properties properties = new Properties();
	
	static{
		try {
			properties.load(CarFactory2.class.getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用配置文件读取信息，利于扩展
	 * @param car
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Car getCar() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String clazz = properties.getProperty("car");
		Class<?> clazzName = Class.forName(clazz);
		return (Car) clazzName.newInstance();
	}
	
}
