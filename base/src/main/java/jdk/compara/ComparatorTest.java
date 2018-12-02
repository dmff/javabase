package jdk.compara;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: dmf
 * @date: 2018/4/3 15:28
 *
 * Comparator是临时的排序规则，通过collections.sort或者jdk8流式编程时传入排序规则，主要用来排序和分组
 * Comparable是对象默认的排序规则，比如string和integer就实现了
 */
public class ComparatorTest {

    class Dog{
        public int age;
        public String name;
        public Dog(int age, String name) {
            super();
            this.age = age;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Dog [age=" + age + ", name=" + name + "]";
        }
    }

    public static void main(String[] args) {
        List<Dog> list= new ArrayList<>();
        list.add(new ComparatorTest().new Dog(5, "DogA"));
        list.add(new ComparatorTest().new Dog(6, "DogB"));
        list.add(new ComparatorTest().new Dog(7, "DogC"));
        //倒叙排序
        list.sort((o1, o2) -> o2.age - o1.age);
        System.out.println("给狗狗按照年龄倒序："+list);
        list.sort(Comparator.comparing(Dog::getName));
        System.out.println("给狗狗按名字字母顺序排序："+list);
    }
}
