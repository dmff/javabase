package improve.enumeration;

public class Student4 {

	private String name;
	private Grade2 grade;  //A B C D E
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Grade2 getGrade() {
		return grade;
	}
	
	//这种不太合理，直接把程序杀死了
	public void setGrade(Grade2 grade) {
		this.grade = grade;
	}
}

enum Grade2{
	A("90-100"){

		@Override
		public String toLocaleString() {
			return "优";
		}},
	B("80-90"){
	@Override
	public String toLocaleString() {
		return "良";
	}},
    C("70-80"){
    	@Override
    	public String toLocaleString() {
    		return "中等";
    	}},
    D("60-70"){
    	@Override
    	public String toLocaleString() {
    		return "及格";
    	}},
    E("0-60"){
    	@Override
    	public String toLocaleString() {
    		return "差";
    	}};
	private String value;
	private Grade2(String value){  //构造方法私有
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public abstract String toLocaleString();  //返回在本地的评分
}

