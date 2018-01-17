package com.vortex.flywight;

/**
 * 具体的享元类
 * @author dmf
 *
 */
public class ConcreteChess implements ChessFlyWeight{

	private String color;
	
	public ConcreteChess(String color) {
		super();
		this.color = color;
	}

	@Override
	public String getColor() {
		return this.color;
	}

	@Override
	public void display(Coordinate c) {
		System.out.println("当前颜色:"+this.color);
		System.out.println("当前位置:"+c.getX()+";"+c.getY());
	}

}
