package util;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: dmf
 * @date: 2018/4/10 16:02
 */
public class UnSafeTest {



    @Test
    public void cityTest() throws Exception{
        final Unsafe unsafe = getUnsafe();
        //不调用构造方法直接生成对象
        City city = (City) unsafe.allocateInstance(City.class);
        System.out.println(city);

        City city1 = new City();
        System.out.println(city1);
    }

    @Test
    public void validationTest() throws Exception{
        final Unsafe unsafe = getUnsafe();
        Validation validation = new Validation();
        System.out.println(validation.sizeValidate());

        Field field = validation.getClass().getDeclaredField("MAX_SIZE");
        //修改内存地址内容
        unsafe.putInt(validation,unsafe.objectFieldOffset(field),100);
        System.out.println(validation.sizeValidate());
    }

    @Test
    public void getObjectSize(){
       /**
        * 通过java.lang.instrument.Instrumentation的getObjectSize(obj)直接获取对象的大小；
        * 通过sun.misc.Unsafe对象的objectFieldOffset(field)等方法结合反射来计算对象的大小。
        * 通过Unsafe获取Java对象大小的基本思路如下：
        *    通过反射获得一个类的Field；
        *    通过Unsafe的objectFieldOffset()获得每个Field的off。Set；
        *    对Field进行遍历，取得最大的offset，然后加上这个field的长度，再加上Padding对齐。
        */

    }



    static class Validation {

        private int MAX_SIZE = 10;

        public boolean sizeValidate() {
            return 20 < MAX_SIZE;
        }
    }

    public static Unsafe getUnsafe() throws Exception{
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }

    static class City{
        private String name;
        private int flag;

        public City() {
            this.name = "北京";
            this.flag = 1;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", flag=" + flag +
                    '}';
        }
    }
}
