package improve.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmf
 * @date 2018/1/7
 */
public class GenericDemo {
    
    public static void main(String[] args) {
        ArrayList list = new ArrayList<Integer>();
        list.add(1);
        //Object o = list.get(0);

        //只能对al集合中的元素调用Object类中的方法，具体子类型的方法都不能用，因为子类型不确定,一般在设计类的时候使用
        ArrayList<? extends Object> arrayList = new ArrayList();
        //arrayList.add(((Object) "aa"));//会报错

        List<String> l1 = new ArrayList<String>();
        l1.add("String");
        List<?> l2 = l1;
        Object o = l2.get(0);
        System.out.println(l1.get(0));

        //ParameterizedType type = (ParameterizedType) l1.getClass().getGenericSuperclass();
        //System.out.println(type.getActualTypeArguments()[0]);

        //List<String>[] lists = new ArrayList<String>[10]; //会报错
        List<String>[] lists = new ArrayList[10];

        List<?>[] lsa = new List<?>[10];
        Object o1 = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li;
        String s = (String) lsa[1].get(0);
    }


}
