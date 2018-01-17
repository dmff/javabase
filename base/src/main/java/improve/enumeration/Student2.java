package improve.enumeration;

public class Student2 {

	private String name;
	private Grade grade;  //A B C D E
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Grade getGrade() {
		return grade;
	}
	
	//这种不太合理，直接把程序杀死了
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
}

class Grade{
	private Grade(){};  //不用枚举类的办法
	public static Grade A = new Grade();
	public static Grade B = new Grade();
	public static Grade C = new Grade();
	public static Grade D = new Grade();
	public static Grade E = new Grade();
}