package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * lambda表达式
 */
public class Java8Lambda {
    public static void main(String[] args){
        List<String > list = Arrays.asList("a","d","b","c");
        //传统方式
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(list);

        //java8方式
        /*Collections.sort(list,(String a,String b)->{
            return a.compareTo(b);
        });*/
        Collections.sort(list,(a,b)->{
            return a.compareTo(b);
        });
        System.out.println(list);

    }
}
