package com.vortex.strategy.CD;

public class TeacherQuery implements Query {

	private int qTeaNo;
	private String qName;
	
	public String getqName() {
		return qName;
	}
	
	public void setqName(String qName) {
		this.qName = qName;
	}
	
	public int getqTeaNo() {
		return qTeaNo;
	}
	
	public void setqTeaNo(int qTeaNo) {
		this.qTeaNo = qTeaNo;
	}
	
	
	@Override
	public String getSql() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from tea where 1 = 1 ");
		if(qTeaNo != 0){
			sb.append(" and teaNo = " + qTeaNo);
		}
		if(qName != null){
			sb.append(" and name = " + qName);
		}
		
		return sb.toString();
	}

}
