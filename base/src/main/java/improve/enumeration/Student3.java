package improve.enumeration;

public class Student3 {

	private String name;
	private Grade1 grade;  //A B C D E
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Grade1 getGrade() {
		return grade;
	}
	
	//这种不太合理，直接把程序杀死了
	public void setGrade(Grade1 grade) {
		this.grade = grade;
	}
}

enum Grade1{
	A("90-100"),B("80-90"),C("70-80"),D("60-70"),E("0-60");
	private String value;
	private Grade1(String value){  //构造方法私有
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}

/*class Grade{
	private Grade(){};  //不用枚举类的办法
	public static Grade A = new Grade();
	public static Grade B = new Grade();
	public static Grade C = new Grade();
	public static Grade D = new Grade();
	public static Grade E = new Grade();
}*/