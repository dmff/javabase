package com.vortex.strategy.CD;

public class Test2 {

	public static void main(String[] args) {
		StudentQuery query = new StudentQuery();
		query.setqStuNo(100);
		query.setqName("mi");
		CommonQueryer commonQueryer = new CommonQueryer();
		commonQueryer.setQuery(query);
		commonQueryer.find();
	}

}
