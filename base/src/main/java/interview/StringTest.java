package interview;

public class StringTest {

	 public static void main(String[] args) {
	        String s1 = "Programming";
	        String s2 = new String("Programming");
	        String s3 = "Program";
	        String s4 = "ming";
	        String s5 = "Program" + "ming";
	        String s6 = s3 + s4;
	        System.out.println(s1 == s2);
	        System.out.println(s1 == s5);
	        System.out.println(s1 == s6);
	        System.out.println(s1 == s6.intern());
	        System.out.println( s2.intern()==s2);
	        
	    }
	 
	 //递归方法反转字符串
	 public static String reverse(String originStr) {
	        if(originStr == null || originStr.length() <= 1) 
	            return originStr;
	        return reverse(originStr.substring(1)) + originStr.charAt(0);
	 }
	 

	 public void testReverse(){
		 System.out.println(reverse("abcd"));
	 }
	 

	 public void testIntern(){
		 //首次出现实例的引用
		 String str1 = new StringBuilder("计算机").append("软件").toString();//new string("计算机软件");
		 //String str1 = new String("计算机软件");
		 System.out.println(str1.intern() == str1);  
		 //toString()方法之前java在常量池存在，所以返回常量池java的引用
		 //String str2 = new StringBuilder("ja").append("va").toString();
		 String str2 = new String("aava");
		 System.out.println(str2.intern() == str2);  //特殊关键字
		 String str3 = new StringBuilder("ao").append("id").toString();
		 System.out.println(str3.intern() == str3);
 	 }

	 public void test(){
		 //两份地址，一份堆上，str1引用，一份常量池中 string s="abc"; String str1 = new String("哈哈");只在堆中存在一份地址
		 String str1 = new String("哈哈");
		 String str = "哈哈";
		 //两份对象肯定不相等
		 System.out.println(str1 == str);
		 String str2 = new String("哈哈");
		 //intern返回对象引用的地址,
		 System.out.println(str1.intern() == str1);  //返回false
		 //如果池已经包含一个字符串等于这个String对象由equals(Object)方法,然后返回的字符串从池中
		 String str11 = str1.intern(); 
		 System.out.println(str11 == str2.intern());
	 }
	 

	 public void testReference(){
		 String s = "hello";
		 System.out.println(s);
		 //new出一个对象，指向了新的地址
		 s = s + "world";
		 System.out.println(s);
	 }
	 
	/* public String concatString(String s1,String s2,String s3){
		 //return s1+s2+s3;
		 //前面一段代码相当于后面这些代码，由于stringbuffer不会逃逸，所以sb不会加锁
		 StringBuffer sb = new StringBuffer();
		 sb.append(s1);
		 sb.append(s2);
		 sb.append(s3);
		 return sb.toString(); 
	 }*/
}
