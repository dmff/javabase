package jdk;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dmf
 * @date 2017/12/30
 * <p>
 * IntHashMap和HashMap的区别：
 * 1.IntHashMap可以减少gc，应为key存的是final int，减少对象的产生
 * 2.由于只是基本数据类型时，所有在获取时并不需要比较hash，值相等hash一定会相等，对象就不一定
 */
public class IntMapTest {

    static Map<Integer, String> map = new HashMap<>();
    //static IntHashMap<String> map = new IntHashMap<>();
    static int size = 50000;

    public static void add() {
        for (int i = 0; i < 10000;i++) {
            for (int j = 0; j < size; j++) {
                map.putIfAbsent(j, "aab");
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        add();
        System.out.println("耗时："+(System.currentTimeMillis()-start));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
