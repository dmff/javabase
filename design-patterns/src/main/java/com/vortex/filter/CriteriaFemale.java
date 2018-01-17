package com.vortex.filter;

import java.util.ArrayList;
import java.util.List;

public class CriteriaFemale implements Criteria {

	@Override
	public List<Person> filterPerson(List<Person> persons) {
		List<Person> lists = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getSex().equalsIgnoreCase("female")) {
				lists.add(person);
			}
		}
		
		return lists;
	}

}
