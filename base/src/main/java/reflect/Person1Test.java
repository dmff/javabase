package reflect;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author dmf
 * @date 2018/4/6
 */
public class Person1Test {

    private Class<?> clazz;
    private Person instance;

    @Before
    public void before() throws Exception{
        clazz = Class.forName("com.vortex.reflect.Person");
        instance = (Person)clazz.newInstance();
    }

    /**获取字节码的三种方式
     *  1.通过Class.forName获取
     *  2.通过类名.class获取
     *  3.通过对象的getClass方法获取
     *
     *  注意：私有字段、方法、构造方法都需要加上Declared
     */
    @Test
    public void testClass() throws Exception {
//        Class<?> clazz = Class.forName("com.vortex.reflect.Person");
//        Class<?> clazz = Person.class;
        Class<?> clazz = new Person().getClass();
        System.out.println(clazz);
        System.out.println(clazz.newInstance());
    }

    /**
     * 通过反射对属性赋值和取值
     * @throws Exception
     */
    @Test
    public void testField() throws Exception {
        //公有name属性
        Field name = clazz.getField("name");
        name.set(instance,"aaa");
        System.out.println(instance);
        System.out.println(name.get(instance));

        //私有age属性,
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.set(instance,18);
        System.out.println(instance);
        System.out.println(age.get(instance));

        //静态属性password
        Field password = clazz.getField("password");
        password.set(null,"123456");
        System.out.println(instance);
        System.out.println(password.get(null));

        //final类型,不能set，因为是常量
        Field sex = clazz.getField("sex");
        System.out.println(sex.get(null));
    }

    /**
     * 通过反射获取无参，有参数，私有和公有构造方法，然后创建对象
     */
    @Test
    public void testConstruct() throws Exception{
        Constructor<?> c1 = clazz.getConstructor();
        System.out.println(c1.newInstance());

        Constructor<?> c2 = clazz.getDeclaredConstructor(String.class);
        System.out.println(c2.newInstance("aaa"));

        Constructor<?> c3 = clazz.getDeclaredConstructor(int.class);
        c3.setAccessible(true);
        System.out.println(c3.newInstance(18));
    }

    /**
     *  通过反射获取无参数、有参数（多参数）、私有、公有、静态、以及main方法，并且调用
     */
    @Test
    public void testMethod() throws Exception{
        //参数
        Method eat = clazz.getMethod("eat");
        eat.invoke(instance);

        Method run = clazz.getMethod("run",String.class);
        run.invoke(instance,"hei hei");

        Method run2 = clazz.getMethod("run",String.class,int[].class,String[].class);
        run2.invoke(instance,"dmf",new int[]{1,2,3},new String[]{"a","b","c"});

        //访问权限
        Method test = clazz.getMethod("test",String.class);
        System.out.println(test.invoke(instance,"hello"));

        Method test2 = clazz.getDeclaredMethod("test2",String.class);
        test2.setAccessible(true);
        System.out.println(test2.invoke(instance,"hello"));

        Method test3 = clazz.getDeclaredMethod("test3",String.class);
        System.out.println(test3.invoke(null,"hello"));

        //main方法，1.5之后为可变参数
        //这里拆的时候将 new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
        Method main = clazz.getMethod("main",String[].class);
       // main.invoke(null, (Object)new String[]{"1","2"});
        main.invoke(null, new Object[]{new String[]{"1","2"}});
    }
}
