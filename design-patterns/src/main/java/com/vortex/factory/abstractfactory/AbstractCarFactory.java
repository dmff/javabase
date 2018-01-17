package com.vortex.factory.abstractfactory;


import com.vortex.factory.staticfactory.Car;

/**
 * 抽象的汽车工厂，不生产具体的car
 * 4s店卖多种车型，它需要有一个抽象工厂
 * @author dmf
 *
 */
public interface AbstractCarFactory {
	Car getCar();
}
