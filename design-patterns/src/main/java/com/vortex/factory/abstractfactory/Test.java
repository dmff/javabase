package com.vortex.factory.abstractfactory;

public class Test {

	public static void main(String[] args) {
		/**
		 * 模拟场景：顾客去4s买了一辆benz汽车，4s去benz拿车
		 * 			顾客驾车去兜风
		 */
		Customer customer = new Customer();
		
		SSSS ssss = new SSSS();
		ssss.setCarFactory(new BenzCarFactory());
		customer.buyCar(ssss );
		
		customer.driver();
	}
}
