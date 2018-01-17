package java8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stream {
    List<String> list;
    @Before
    public  void before(){
        //System.out.println("before....");
        list = new ArrayList<>();
        list.add("ddd2");
        list.add("aaa2");
        list.add("bbb1");
        list.add("aaa1");
        list.add("bbb3");
        list.add("ccc");
        list.add("bbb2");
        list.add("ddd1");
        //可以通过 Collection.stream() 或者 Collection.parallelStream() 来创建一个Stream
    }
    
    @After
    public void after(){
        //System.out.println("after....");
        list.clear();
    }
    
    @Test
    public void filter(){
        //过滤操作，过滤通过一个predicate接口来过滤并只保留符合条件的元素
        list.stream()
                .filter(s -> s.startsWith("a"))
                .forEach(s->System.out.print(s+" "));
    }

    @Test
    public void sort(){
       //排序操作,没有指定方法，使用默认的排序规则
        list.stream()
                .sorted()
                //.filter((s)->s.startsWith("a"))
                .forEach(s->System.out.print(s+" "));
    }

    @Test
    public void map(){
        //中间操作map会将元素根据指定的Function接口来依次将元素转成另外的对象
        list.stream()
                .map(String::toUpperCase)
                //使用倒叙排序
                .sorted((a,b)->{return b.compareTo(a);})
                .forEach(s->System.out.print(s+" "));
    }

    @Test
    public void match(){
        //匹配操作，允许检测指定的Predicate是否匹配整个Stream
        boolean match = list.stream().anyMatch((s) -> s.startsWith("a"));
        System.out.println(match);

        boolean match1 = list.stream().allMatch((s) -> s.startsWith("a"));
        System.out.println(match1);

        boolean match2 = list.stream().noneMatch((s) -> s.startsWith("z"));
        System.out.println(match2);
    }

    @Test
    public void count(){
        //计数是一个最终操作，返回Stream中元素的个数，返回值类型是long。
        long count = list.stream().filter((s) -> s.startsWith("b")).count();
        System.out.println(count);
    }

    @Test
    public void reduce(){
        //这是一个最终操作，允许通过指定的函数来讲stream中的多个元素规约为一个元素
        Optional<String> reduce = list.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(s->System.out.println(s));
    }


}
