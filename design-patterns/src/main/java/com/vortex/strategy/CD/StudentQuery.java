package com.vortex.strategy.CD;

public class StudentQuery implements Query {

	private int qStuNo;
	private String qName;
	
	public String getqName() {
		return qName;
	}
	
	public void setqName(String qName) {
		this.qName = qName;
	}
	
	public int getqStuNo() {
		return qStuNo;
	}
	
	public void setqStuNo(int qStuNo) {
		this.qStuNo = qStuNo;
	}
	
	
	@Override
	public String getSql() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from stu where 1 = 1 ");
		if(qStuNo != 0){
			sb.append(" and stuNo = " + qStuNo);
		}
		if(qName != null){
			sb.append(" and name like % " + qName + "% ");
		}
		
		return sb.toString();
	}

}
