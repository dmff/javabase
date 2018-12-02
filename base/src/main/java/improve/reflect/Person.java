package improve.reflect;

public class Person {

	/**-----------公有、私有、静态属性-----------**/
	public String name;
	private int age;
	public final String password="123";

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	/**-----------公有、私有、带参数构造方法-----------**/
	public Person(){ System.out.println("person!!!!!"); }
	public Person(String name){
		System.out.println("i named "+name);
	}
	private Person(int age){
		System.out.println("i old "+age);
	}

	/**-----------无参、单参数、多参数方法-----------**/
	public void eat(){
		System.out.println("eat!!!!");
	}
	public void run(String address){
		System.out.println("跑到" + address);
	}
	public void run(String address,int num[],String ss[]){
		System.out.println("跑到" + address + "," + num);
	}

	/**-----------公有、私有、静态方法-----------**/
	public String test(String str){ return str + "aaaa"; }
	private String test2(String str){ return str + "aaaa"; }
	public static String test3(String str){ return str + "aaaa"; }


	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", password='" + password + '\'' +
				'}';
	}

	public static void main(String[] args) {
		System.out.println(args[0]);
	}
}
