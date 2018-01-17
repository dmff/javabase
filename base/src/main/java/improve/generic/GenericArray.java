package improve.generic;

import java.lang.reflect.Array;

/**
 * @author dmf
 * @date 2018/1/7
 */
public class GenericArray {

    /**
     * 根据数组类型的class创建对应类型的数组
     * @param <T> 目标类型
     * @param clazz
     * @param length 数组长度
     * @return
     */
    public static <T> T[] newArrayByArrayClass(Class<T[]> clazz, int length) {
        return (T[]) Array.newInstance(clazz.getComponentType(), length);
    }

    /**
     * 根据普通类型的class创建数组
     * @param <T> 目标类型
     * @param clazz
     * @param length 数组长度
     * @return
     */
    public static <T> T[] newArrayByClass(Class<T> clazz, int length) {
        return (T[]) Array.newInstance(clazz, length);
    }

    public static void main(String[] args) {
        // 判断一个Class是否是数组类型，可以用Class实例的isArray方法。
        String[] byArray = newArrayByArrayClass(String[].class, 10);
        String[] byOne = newArrayByClass(String.class, 10);
    }
}
