package improve.enumeration;

public class Student {

	private String name;
	private String grade;  //A B C D E
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	
	//这种不太合理，直接把程序杀死了
	public void setGrade(String grade) {
		if (!grade.matches("[ABCDE]")) {
			throw new RuntimeException("对不起，传入非法参数"); 
		}
		this.grade = grade;
	}
	
	
	
}
