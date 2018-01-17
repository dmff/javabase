package com.vortex.filter;

import java.util.List;

/**
 * 使用双重过滤
 * @author dmf
 *
 */
public class AndCriteria implements Criteria{

	private Criteria criteria1;
	private Criteria criteria2;
	
	public AndCriteria(Criteria criteria1, Criteria criteria2) {
		super();
		this.criteria1 = criteria1;
		this.criteria2 = criteria2;
	}

	@Override
	public List<Person> filterPerson(List<Person> persons) {
		List<Person> person1 = criteria1.filterPerson(persons);
		List<Person> person2 = criteria2.filterPerson(person1);
		return person2;
	}
	
}
