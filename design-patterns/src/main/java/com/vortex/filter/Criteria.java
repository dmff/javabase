package com.vortex.filter;

import java.util.List;

/**
 * 过滤接口
 * @author dmf
 *
 */
public interface Criteria {

	public List<Person> filterPerson(List<Person> persons);
}
