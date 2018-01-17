package improve.enumeration;

public class StudentTest {

	public static void main(String[] args) {
		/*Student s = new Student();
		s.setGrade("D");*/
		
		/*Student2 s2 = new Student2();
		s2.setGrade(Grade.A);  //就只能设置ABCDE了*/	
	     
		Student3 s3 = new Student3();
		s3.setGrade(Grade1.A);
		
		String value = Grade1.C.getValue();
		System.out.println(value);
		
	}
	
	@org.junit.Test
	public void test(){
		String name = "B";
		Grade2 g = Grade2.valueOf(Grade2.class,name);
		System.out.println(g.name());
		
		//得到枚举类的所有对象
		Grade2[] values = Grade2.values();
		for (Grade2 grade2 : values) {
			System.out.print(grade2.name()+" ");
		}
	}


}
