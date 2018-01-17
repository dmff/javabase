package com.vortex.flywight;

public class Client {

	public static void main(String[] args) {
		ConcreteChess chess1 = FlyWeightFactory.getConcreteChess("白色");
		ConcreteChess chess2 = FlyWeightFactory.getConcreteChess("白色");
		
		//测试内部状态是否一致，和单例模式一样
		System.out.println(chess1);
		System.out.println(chess2);
		
		//测试外部状态,落子
		chess1.display(new Coordinate(10, 20));
		chess2.display(new Coordinate(20, 10));
		
	}
}
