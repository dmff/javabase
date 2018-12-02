package jvm;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author dmf
 * @date 2018/12/2
 * <p>
 * java四种引用：强引用、弱引用、软引用、虚引用
 *
 * 强引用：StrongReference是Java的默认引用形式，使用时不需要显示定义。
 *        任何通过强引用所使用的对象不管系统资源有多紧张，Java GC都不会主动回收具有强引用的对象。
 * 弱引用：如果一个对象只具有弱引用，无论内存充足与否，Java GC后对象如果只有弱引用将会被自动回收。
 * 软引用：
 */
public class ReferenceTest {

    public static int SIZE = 1024 * 1024;

    public void printlnMemory(String tag) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("\n" + tag + ":");
        System.out.println(runtime.freeMemory() / SIZE + "M(free)/" + runtime.totalMemory() / SIZE + "M(total)");
    }

    @Test
    public void testStrongReference() {
        printlnMemory("1.原可用内存和总内存");

        //实例化10M的数组并与strongReference建立强引用
        byte[] strongReference = new byte[10*SIZE];
        printlnMemory("2.实例化10M的数组,并建立强引用");
        System.out.println("strongReference : "+strongReference);

        System.gc();
        printlnMemory("3.GC后");
        System.out.println("strongReference : "+strongReference);

        //strongReference = null;后,强引用断开了
        strongReference = null;
        printlnMemory("4.强引用断开后");
        System.out.println("strongReference : "+strongReference);

        System.gc();
        printlnMemory("5.GC后");
        System.out.println("strongReference : "+strongReference);
    }

    @Test
    public void testWeakReference(){
        printlnMemory("1.原可用内存和总内存");

        //创建弱引用
        WeakReference<Object> weakRerference = new WeakReference<>(new byte[10*SIZE]);
        printlnMemory("2.实例化10M的数组,并建立弱引用");
        System.out.println("weakRerference.get() : "+weakRerference.get());

        System.gc();
        printlnMemory("3.GC后");
        System.out.println("weakRerference.get() : "+weakRerference.get());
    }

    @Test
    public void testSoftReference(){
        printlnMemory("1.原可用内存和总内存");

        //建立软引用
        SoftReference<Object> softRerference = new SoftReference<>(new byte[10*SIZE]);
        printlnMemory("2.实例化10M的数组,并建立软引用");
        System.out.println("softRerference.get() : "+softRerference.get());

        System.gc();
        printlnMemory("3.内存可用容量充足，GC后");
        System.out.println("softRerference.get() : "+softRerference.get());

        //实例化一个4M的数组,使内存不够用,并建立软引用
        //free=10M=4M+10M-4M,证明内存可用量不足时，GC后byte[10*m]被回收
        SoftReference<Object> softRerference2 = new SoftReference<>(new byte[4*SIZE]);
        printlnMemory("4.实例化一个4M的数组后");
        System.out.println("softRerference.get() : "+softRerference.get());
        System.out.println("softRerference2.get() : "+softRerference2.get());
    }


    @Test
    public void testPhantomReference(){
        printlnMemory("1.原可用内存和总内存");
        byte[] object = new byte[10*SIZE];
        printlnMemory("2.实例化10M的数组后");

        //建立虚引用
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object,referenceQueue);

        printlnMemory("3.建立虚引用后");
        System.out.println("phantomReference.get() : "+phantomReference.get());
        System.out.println("referenceQueue.poll() : "+referenceQueue.poll());

        //断开byte[10*M]的强引用
        object = null;
        printlnMemory("4.执行object = null;强引用断开后");

        System.gc();
        printlnMemory("5.GC后");
        System.out.println("phantomReference.get() : "+phantomReference.get());
        System.out.println("referenceQueue.poll() : "+referenceQueue.poll());

        //断开虚引用
        phantomReference = null;
        System.gc();
        printlnMemory("6.断开虚引用后GC");
        System.out.println("phantomReference : "+phantomReference);
        System.out.println("referenceQueue.poll() : "+referenceQueue.poll());
    }
}
