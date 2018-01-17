package java8;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author dmf
 * @date 2017/11/24
 *
 * 没有lambda表达式之前，传入行函数只能使用匿名内部类，这样比较繁琐，lambda表达式取消了匿名类，取消了模板，使用函数式的进行编程
 * 重点使用stream的Api和lambda表达式对列表和集合数据进行提取、过滤和排序
 *
 * lambda和匿名类的比较：
 *    匿名类的 this 关键字指向匿名类，而lambda表达式的 this 关键字指向包围lambda表达式的类。
 *    Java编译器将lambda表达式编译成类的私有方法。使用了Java 7的 invokedynamic 字节码指令来动态绑定这个方法。
 *
 */
public class Java8Test {

    @Test
    public void test1(){
        /**
         * 1.初识lambda表达式,使用()->{}代替匿名内部类。
         * ()里面可以使用参数，参数类型会自动解析，{}里面编写逻辑代码，可以有返回值，如果只有一行的化，可以简写
         * 例如：()->sys..  (a+b)->a+b
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("在java8之前启动线程的方式....");
            }
        }).start();

        new Thread(()->System.out.println("在java8之后启动线程的方式....")).start();
        //注意：如果没有这句代码的话，可能main线程会关闭，就看不到输出
        System.out.println("test");
    }

    @Test
    public void test2(){
        /**
         * 2.使用lambda表达式对事件进行处理
         */
        JButton jButton = new JButton("show");
        jButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });
        jButton.addActionListener((e)->System.out.println("Light, Camera, Action !! Lambda expressions Rocks"));

    }

    @Test
    public void test3(){
        /**
         * 使用lambda对集合进行迭代
         */
        List<String> lists = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String str:lists) {
            System.out.print(str+",");
        }
//        lists.forEach((e)->System.out.println(e));
        lists.forEach(System.out::println);
    }

    @Test
    public void test4(){
        /**
         * 4.Predicate测试接口
         *
         */
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        filter(languages, (str)->{return ((String)str).startsWith("J");});

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->((String)str).endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->((String)str).length() > 4);
    }

    private void filter(List<String> lists, Predicate conditon){
        /*for (String str:lists) {
            if (conditon.test(str)) {
                System.out.println(str + " ");
            }
        }*/
        //使用lambda表达式的用法
        lists.stream().filter((str)->(conditon.test(str))).forEach((str)->System.out.println(str+" "));
    }

    @Test
    public void test5(){
        /**
         * 5.Predicate测试接口
         *  甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
         *  例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
         */
        List<String> languages = Arrays.asList("Java1", "Scala", "C++", "Haskell", "Lisp");
        Predicate<String> condition1 =(s)->s.startsWith("J");
        Predicate<String> condition12 =(s)->s.length()>4;
        languages.stream().filter(condition1.and(condition12)).forEach((n)->System.out.println(n+" "));
    }

    @Test
    public void test6_1(){
        /**
         * map操作：
         */
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
      /*  double price=0;
        for (int i:costBeforeTax) {
            price = i+.12*i;
            System.out.println(price);
        }*/

        costBeforeTax.stream().map(i->i+0.12*i).forEach(System.out::println);
    }

    @Test
    public void test6_2(){
        /**
         * reduce操作：
         * 流API定义的 reduceh() 函数可以接受lambda表达式，并对所有值进行合并。
         * IntStream这样的类有类似 average()、count()、sum() 的内建方法来做 reduce 操作，也有mapToLong()、mapToDouble() 方法来做转换。
         * 这并不会限制你，你可以用内建方法，也可以自己定义。
         */

        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        /*double total = 0;
        double price = 0;
        for (int i:costBeforeTax) {
            price = i+0.12*i;
            total +=price;
        }
        System.out.println(total);*/

        Double total = costBeforeTax.stream().map((e) -> e + 0.12 * e).reduce((sum, cost) -> sum + cost).get();
        System.out.println(total);

    }

    @Test
    public void test7(){
        List<String> lists = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        List<String> stringList = lists.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", lists, stringList);
    }

    @Test
    public void test8(){
        List<String> lists = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        List<String> stringList = lists.stream().map((s) -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(stringList);
    }

    @Test
    public void test9(){
        /**
         * 复制不同的值，创建一个子列表，并使用distinct去重
         */
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> collect = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test10(){
        /**
         * 计算集合元素的最大值、最小值、总和以及平均值;使用summaryStatistics()
         */
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

    }
}
