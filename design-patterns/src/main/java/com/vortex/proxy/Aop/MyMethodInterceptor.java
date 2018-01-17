package com.vortex.proxy.Aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor {
	
	private static Properties properties;
	private static Map<String, String> confMap;
	
	//���������ļ�
	{
		properties = new Properties();
		try{
			properties.load(MyMethodInterceptor.class.getResourceAsStream("proxy.properties"));
			confMap = new HashMap<String, String>();
			String proxyRulesStr = properties.getProperty("proxyRules");
			if (proxyRulesStr !=null) {
				String[] proxyRules = proxyRulesStr.split(",");
				for(String proxyRule:proxyRules){
					String[] rule = proxyRule.split(":");
					confMap.put(rule[0], rule[1]);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//���еı��������real����Object
	private Object object;
	
	public MyMethodInterceptor(Object object) {
		super();
		this.object = object;
	}


	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		String realObjectClassName = object.getClass().getName();
		String methodName = method.getName();
		String key = realObjectClassName+"-"+methodName;
		String value = confMap.get(key);
		
		if (value==null) {   //com.mi.proxy6.Driver-*:after
			value = confMap.get(realObjectClassName + "-*");
			if (value==null) { //��Ȼû��ƥ�䣬ֱ�ӵ��ö���ִ��
				return method.invoke(object, args);
			}
		}
		
		if(value.equals("before")){
			System.out.println("ëүү���꣡");
			return method.invoke(object, args);
		}
		
		if(value.equals("after")){
			Object reVal = method.invoke(object, args);
			System.out.println("ëүү���꣡");
			return reVal;
		}
		
		return method.invoke(object, args);
	}

}
