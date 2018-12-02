package jvm;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author dmf
 * @date 2018/4/22
 */
public class GenericTest {


    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4);
        int sum = 0;
        for(int i : list) {
            sum += i;
        }
        System.out.println(sum);
    }

    /**
     * 默认情况对-128到127的整数对象进行了缓存，超出的就要在堆上建立新的对象
     * equals方法不会处理类型转换，同类型则比较值，不同类型直接false
     * Float和Double没有缓存，Boolean和Byte全部缓存，Character <=127缓存，Integer，Short，Long缓存-128到127
     */
    @Test
    public void test2(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 128;
        Integer f = 128;
        Long g = 3L;

        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));
        System.out.println(g.equals(a+b));
    }


}
