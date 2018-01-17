package com.vortex.filter;

import java.util.List;

/**
 * 查询两个过滤条件的并集
 * @author dmf
 *
 */
public class OrCriteria implements Criteria{

	private Criteria criteria1;
	private Criteria criteria2;
	
	public OrCriteria(Criteria criteria1, Criteria criteria2) {
		super();
		this.criteria1 = criteria1;
		this.criteria2 = criteria2;
	}

	@Override
	public List<Person> filterPerson(List<Person> persons) {
		List<Person> person1 = criteria1.filterPerson(persons);
		List<Person> person2 = criteria2.filterPerson(persons);
		
		for (Person person : person2) {
			if (!person1.contains(person)) {
				person1.add(person);
			}
		}
		
		return person1;
	}
	
}
