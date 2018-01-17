package com.vortex.proxy.Aop;

import net.sf.cglib.proxy.Enhancer;

public class ProxyBuilder {

	private Enhancer enhancer = new Enhancer();
	
	public ProxyBuilder setRealObj(Object real){	
		enhancer.setSuperclass(real.getClass());
		enhancer.setCallback(new MyMethodInterceptor(real));
		return this;
	}
	
	public Object buildProxy(){  //����������
		return enhancer.create();
	}
}
