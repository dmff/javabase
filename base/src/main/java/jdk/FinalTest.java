package jdk;

import org.junit.Test;

/**
 * @author dmf
 * @date 2018/3/4
 */
public class FinalTest {

    /**
     * true、false
     * 因为final修饰编译器会把b当做常量使用，而d是在运行时通过链接访问
     * 但是只有在编译期确定final变量值才会做这样的优化
     */
    @Test
    public void test1(){
        String a = "hello2";
        final String b = "hello";
        String d="hello";
        String c = b+2;
        String e = d+2;
        System.out.println(a==c);
        System.out.println(a==e);
    }

    /**
     * 返回false，因为在编译器并不能优化
     */
    @Test
    public void test2(){
        String a = "hello2";
        final String b = getHello();
        String c = b+2;
        System.out.println(a==c);
    }

    public String getHello() {
        return "hello";
    }
}
