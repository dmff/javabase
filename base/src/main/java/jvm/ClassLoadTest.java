package jvm;

/**
 * @author dmf
 * @date 2018/4/23
 */
public class ClassLoadTest {

    static class SuperClass {
        static int value = 123;
        static {
            System.out.println("super init");
        }
    }

    static class SubClass extends SuperClass{
        static {
            System.out.println("sub init");
        }
    }

    public static void main(String[] args){
        System.out.println(SubClass.value);
    }

}
