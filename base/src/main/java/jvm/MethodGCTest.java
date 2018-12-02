package jvm;

import org.junit.Test;

/**
 * @author dmf
 * @date 2018/4/23
 */
public class MethodGCTest {

    /**
     *  在执行gc时，变量在作用范围内，所以虚拟机没有回收
     *  [GC (System.gc())  74331K->67152K(125952K), 0.0079105 secs]
     *  [Full GC (System.gc())  67152K->67068K(125952K), 0.0108579 secs]
     */
    @Test
    public void test1(){
        byte[] bytes = new byte[1024*1024*64];
        System.gc();
    }

    /**
     * 执行gc时，变量已经不能在被访问，但是任然没有被gc
     * [GC (System.gc())  74331K->67232K(125952K), 0.0035112 secs]
     * [Full GC (System.gc())  67232K->67069K(125952K), 0.0268947 secs]
     */
    @Test
    public void test2(){
        {
            byte[] bytes = new byte[1024 * 1024 * 64];
        }
        System.gc();
    }
    /**
     * 变量能被gc的真正原因是局部变量表是否含有变量的引用
     * 所以可以赋空值操作来优化内存回收，或者打断变量的引用
     * [GC (System.gc())  74332K->67152K(125952K), 0.0086763 secs]
     * [Full GC (System.gc())  67152K->1534K(125952K), 0.0126887 secs]
     */
    @Test
    public void test3(){
        {
            byte[] bytes = new byte[1024 * 1024 * 64];
            //手动置空也可以被gc
            bytes = null;
        }
//        int a=0;
        System.gc();
    }
}
