package com.vortex.filter;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSingle implements Criteria {

	@Override
	public List<Person> filterPerson(List<Person> persons) {
		List<Person> lists = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getMarryStatus().equalsIgnoreCase("single")) {
				lists.add(person);
			}
		}
		
		return lists;
	}

}
