package com.rpc;

/**
 * 服务的接口和实现类
 * @author dmf
 *
 */
public class HelloServiceImpl implements HelloService{

	@Override
	public String sayHello(String hello) {
		if (hello!=null && hello.equals("hello")) {
			return hello;
		}else {
			return "bye bye";
		}
		
	}

}
