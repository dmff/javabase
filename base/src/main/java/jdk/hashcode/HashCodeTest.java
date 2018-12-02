package jdk.hashcode;

import org.junit.Test;

/**
 * @author: dmf
 * @date: 2018/4/4 8:53
 */
public class HashCodeTest {

    static class Person {
        private String name;
        private int age;


        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;

            Person person = (Person) o;

            if (age != person.age) return false;
            return name.equals(person.name);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            return result;
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("aaa",12);
        Person p2 = new Person("aaa",12);
        //两个new出来的对象的地址值肯定不相等
        System.out.println(p1.hashCode()+":"+p2.hashCode());
        //equals是调用对象比较属性时，equal会影响hashcode的值,调用equals属性的hashcode的值计算hashcode，所以equal相等，hashcode一定相等，
        //hashcode相等，equal可能相等，可能不相等（hashcode是算出来的，可能是完全不同的对象）
        System.out.println(p1.equals(p2));
    }

    @Test
    public void test(){
//        String a = "test";
        String a = new String("test");
        String b = new String("test");
        //false、true、两者hashcode相等
        System.out.println(a==b);
        System.out.println(a.equals(b));
        //string字符串相等，hashcode和equals是相等的，但是地址值不相等
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
}
