package java8;

import java.util.Objects;
import java.util.function.Predicate;

public class Java8Pridicate {

    public static void main(String[] args){
        //java8的Predicate 接口只有一个参数，返回boolean类型
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

    }
}
