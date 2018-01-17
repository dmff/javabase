package com.vortex.filter;

import java.util.ArrayList;
import java.util.List;

public class CriteriaMale implements Criteria {

	@Override
	public List<Person> filterPerson(List<Person> persons) {
		List<Person> lists = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getSex().equalsIgnoreCase("male")) {
				lists.add(person);
			}
		}
		
		return lists;
	}

}
