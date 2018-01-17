package com.vortex.flywight;

/**
 * 象棋享元接口：
 * 		棋子颜色为内部状态，被所有棋子共享
 * 		棋子的位置为外部状态，不确定
 * @author dmf
 *
 */
public interface ChessFlyWeight {

	String  getColor();
	
	void display(Coordinate c);
	
}
