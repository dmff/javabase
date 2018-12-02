package jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmf
 * @date 2018/4/21
 */
public class JvmExceptionTest {

    static class TestObject{}

    /**
     * 堆溢出：java.lang.OutOfMemoryError: Java heap space
     * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\jvm\jvmDump.hprof
     */
    @Test
    public void testHeapOOM(){
        List<TestObject> list = new ArrayList<>();
        while (true){
            list.add(new TestObject());
        }
    }

    /**
     * 栈溢出：StackOverflowError和java.lang.OutOfMemoryError
     * -Xss128k
     * 在单线程下，无论由于栈帧太大还是虚拟机栈容量太小，当内存无法分配时，会抛出StackOverflowError
     * 多线程时，为每一个线程分配的栈容量越大，可以建立的线程数越小，这样越容易发生内存溢出
     */
    @Test
    public void testStack(){
        try {
            stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:"+length);
            throw e;
        }
    }

    private int length = 1;
    public  void stackLeak(){
        length++;
        stackLeak();
    }


    /**
     *  方法区：元空间
     *  java.lang.OutOfMemoryError: Metaspace
     *  0000
     */
    @Test
    public void testMetaSpace(){
        List<String > list = new ArrayList<>();
        int i=1;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * 都为false，就是返回字符串常量池的引用
     */
    @Test
    public void testIntern(){
        String str1 = new StringBuilder("计算机软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("java").toString();
        String str3 = new String("java");
        System.out.println(str2.intern() == str2);
        System.out.println(str3.intern() == str3);
    }

    @Test
    public void testMetaSpaceOOM(){
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(TestObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o,objects));
            enhancer.create();
        }
    }

    /**
     * 本机直接内存溢出:特征，dump文件很小
     */
    @Test
    public void testDirection() throws Exception{
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true){
            unsafe.allocateMemory(1024*1024);
        }
    }
}
