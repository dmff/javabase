package jdk.compara;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * @author: dmf
 * @date: 2018/4/3 15:42
 */
public class GroupTest {

    static class Apple {
        public String color;
        public int weight;

        public Apple(String color, int weight) {
            super();
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "Apple [color=" + color + ", weight=" + weight + "]";
        }
    }

    //两层循环还不如用map ruduce
    public static <T> List<List<T>> divider(Collection<T> datas, Comparator<? super T> c) {
        List<List<T>> result = new ArrayList<>();
        for (T t : datas) {
            boolean isSameGroup = false;
            for (int j = 0; j < result.size(); j++) {
                if (c.compare(t, result.get(j).get(0)) == 0) {
                    isSameGroup = true;
                    result.get(j).add(t);
                    break;
                }
            }
            if (!isSameGroup) {
                // 创建
                List<T> innerList = new ArrayList();
                result.add(innerList);
                innerList.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple("红", 205));
        list.add(new Apple("红", 131));
        list.add(new Apple("绿", 248));
        list.add(new Apple("绿", 153));
        list.add(new Apple("黄", 119));
        list.add(new Apple("黄", 224));
        List<List<Apple>> byColors = divider(list, Comparator.comparing(Apple::getColor));
        System.out.println("按颜色分组" + byColors);
        List<List<Apple>> byWeight = divider(list, (o1,o2)->(o1.weight / 100 == o2.weight / 100) ? 0 : 1);
        System.out.println("按重量级分组" + byWeight);
    }

}
