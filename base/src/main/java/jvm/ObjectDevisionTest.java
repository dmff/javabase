package jvm;

import org.junit.Test;

import java.lang.management.ManagementFactory;

/**
 * @author dmf
 * @date 2018/4/21
 */
public class ObjectDevisionTest {

    static final int _1MB = 1024*1024;

    /**
     *  对象优先分配在eden
     *  -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    @Test
    public void testEden(){

        System.out.println(ManagementFactory.getRuntimeMXBean().getInputArguments());
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
        allocation4 = new byte[4*_1MB];
        System.out.println("exit");
    }

    /**
     * -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     */
    @Test
    public void bigObject(){
        byte[] allocation;
        allocation = new byte[4*_1MB];
    }

    /**
     * 长期存活的对象
     * -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     */
    @Test
    public void testTenuringThreshold(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB/4];
        allocation2 = new byte[4*_1MB];
        allocation3 = null;
        allocation3 = new byte[4*_1MB];
        allocation4 = new byte[4*_1MB];
    }
}
