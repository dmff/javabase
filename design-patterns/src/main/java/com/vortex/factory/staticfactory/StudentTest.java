package com.vortex.factory.staticfactory;

public class StudentTest {

	public static void main(String[] args) {
		Student student = Student.getInstance();
		student.introduce();
	}
}
