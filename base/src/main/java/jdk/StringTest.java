package jdk;

import org.junit.Test;

/**
 * @author dmf
 * @date 2018/1/7
 *
 * 主要测试string的intern方法
 */
public class StringTest {

    @Test
    public void test1(){
        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1+str2;
        String str5 = new String("ab");

        /**
         * 结果为true、false、true、false
         * 主要解释3和4：
         *      str5.intern()返回在常量池的引用，由于常量池已经有ab，所以两个的地址是一样的
         *      str1+str2返回的是一个新的对象
         */
        System.out.println(str5.equals(str3));
        System.out.println(str5==str3);
        System.out.println(str5.intern()==str3);
        System.out.println(str5.intern()==str4);
    }

    @Test
    public void test2(){
        String a = new String("ab");
        String b = new String("ab");
        String c = "ab";
        String d = "a"+"b";
        String e = "b";
        String f = "a" +e;

        /**
         * 运行结果为false、true、true、false、true
         * 说白了String.intern()返回常量池的对象，如果常量池没有则放进常量池再返回，有则直接返回该地址，
         * 还有表达式的为一个新的对象，不会进入字符串池中
         * 采用new 创建的字符串对象不进入字符串池(不应该这样解释，创建2个对象，只是返回的堆地址对象)
         */
        System.out.println(b.intern()==a);
        System.out.println(b.intern()==c);
        System.out.println(b.intern()==d);
        System.out.println(b.intern()==f);
        System.out.println(b.intern()==a.intern());

    }

    @Test
    public void test3(){
        String s = new String("1");
        s = s.intern();
        String s2 = "1";
        System.out.println(s == s2);
        String s3 = new String("1") + new String("1");
        s3 = s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
