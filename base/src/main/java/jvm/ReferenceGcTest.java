package jvm;

import org.junit.Test;

/**
 * @author dmf
 * @date 2018/4/21
 */
public class ReferenceGcTest {

    static class GcReference{
        private Object instance = null;
        private static final int MB_1 = 1024*1024;

        private byte[] bigSize = new byte[2*MB_1];
    }

    /**
     * 打印gc详情  -XX:+PrintGCDetails
     */
    @Test
    public void testGC(){
        GcReference objA = new GcReference();
        GcReference objB = new GcReference();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }
}
