package com.vortex.factory.staticfactory;

public class CarFactory1 {

	/**
	 * 这种方法配置很不灵活，不利于维护
	 * @param car
	 * @return
	 */
	public static Car getCar(String car){
		if (null ==car || "".equals(car)) {
			throw new RuntimeException("carName不能为空...");
		}
		
		if (car.equals("benz")) {
			return new BenzCar();
		}else if (car.equals("ford")) {
			return new FordCar();
		}else if (car.equals("honda")) {
			return new HondaCar();
		}else {
			throw new RuntimeException("没有这种车型...");	
		}
		
	}
}
