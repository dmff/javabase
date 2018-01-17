package com.vortex.factory.staticfactory;

public abstract class Student {

	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * 抽象的自我介绍方法
	 */
	public abstract void introduce();
	
	public static Student getInstance(){
		return new ColleageStudent();
	}
	
	private static class ColleageStudent extends Student{

		@Override
		public void introduce() {
			System.out.println("hello 我是大学生");
		}
		
	}
	
}
