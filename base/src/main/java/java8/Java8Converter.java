package java8;

@FunctionalInterface
public interface Java8Converter<F,T> {
    T converter(F f);
    
    public static void main(String[] args){
        //Java8Converter<String,Integer> converter = (from)->{return Integer.valueOf(from);};
        //最简版本
        /*Java8Converter<String,Integer> converter = (from)->Integer.valueOf(from);
        Integer integer = converter.converter("88");*/

        //通过静态方法引用
        Java8Converter<String,Integer> converter = Integer::valueOf;
        Integer integer = converter.converter("123");
        System.out.println(integer);
    }
}
