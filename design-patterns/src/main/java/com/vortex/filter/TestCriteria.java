package com.vortex.filter;

import java.util.ArrayList;
import java.util.List;

public class TestCriteria {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("a", "MALE", "Single"));
		persons.add(new Person("b", "MALE", "Married"));
		persons.add(new Person("c", "FEMALE","Married"));
		persons.add(new Person("d", "FEMALE","SINGLE"));
		persons.add(new Person("e", "MALE", "SINGLE"));
		persons.add(new Person("f", "MALE", "SINGLE"));
		

		Criteria male = new CriteriaMale();
		Criteria female = new CriteriaFemale();
		Criteria single =new CriteriaSingle();
		Criteria singleMale = new AndCriteria(single, male);
		Criteria singleFemale = new OrCriteria(single, female);

		System.out.println("Males:");
		printPerson(male.filterPerson(persons));
		
		System.out.println("Females:");
		printPerson(female.filterPerson(persons));
//		
		System.out.println("Singles:");
		printPerson(single.filterPerson(persons));
		
		System.out.println("and singleMales:");
		printPerson(singleMale.filterPerson(persons));
		
		System.out.println("or singleFemales:");
		printPerson(singleFemale.filterPerson(persons));

	}
	
	public static void printPerson(	List<Person> persons ){
		for (Person person : persons) {
			System.out.println(person);
		}
	}
}
