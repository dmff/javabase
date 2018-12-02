package java8;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 并行streams测试
 */
public class ParallelStreams {
    List<String > values = new ArrayList<>(1000000);

    public void init(){
        for(int i=0;i<1000000;i++){
            values.add(UUID.randomUUID().toString());
        }
    }
    @Test
    public void serial(){
        init();
        long start = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long end = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(end-start);
        System.out.println(String.format("serial sort take:%d",millis)); //1053
    }


    @Test
    public void parallel(){
        init();
        long start = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long end = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(end-start);
        System.out.println(String.format("parallel sort take:%d",millis)); //691
    }

    /**
     * java8  map的使用用法
     */
    @Test
    public void map(){
        Map<Integer,String> map = new HashMap<>();
        for(int i=0;i<10;i++){
            map.putIfAbsent(i,"val"+i);// putIfAbsent 不需要我们做额外的存在性检查,如果不存在则put
        }
        map.forEach((id,val)->System.out.println(id+":"+val));

        map.computeIfPresent(3,(num,val)->val+ num);
        System.out.println(map.get(3));              //33

        map.computeIfPresent(9,(num,val)->null);
        System.out.println(map.containsKey(9));     //false

        map.computeIfAbsent(23,num->"val"+num);
        System.out.println(map.containsKey(23));    //true

        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));             // val33

        //map删除key-value都匹配
        map.remove(3, "val3");
        System.out.println(map.get(3));             //null

        map.remove(3, "val33");
        System.out.println(map.get(3));             //val33

        System.out.println(map.getOrDefault(42,"not found"));  //not found，没有查到会返回默认值

        //map和元素合并
        map.merge(9,"valn9",(val,newval)->val.concat(newval));
        System.out.println(map.get(9));  //val9

        map.merge(9,"cpncat",(val,newval)->val.concat(newval));
        System.out.println(map.get(9));  //val9

    }
}
