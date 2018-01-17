package java8;

/**
 * java8允许在接口定义default方法
 */
public interface Java8DefaultMethod {

    double calculate(int a);

    default double sqrt(int a){
        return Math.sqrt(a);
    }
    
    public static void main(String[] args){
        Java8DefaultMethod defaultMethod = new Java8DefaultMethod() {
            @Override
            public double calculate(int a) {
                return sqrt(a*a);
            }
        };

        //Java8DefaultMethod defaultMetho= (a)->{return a;};
        //Java8DefaultMethod defaultMetho= (a)-> sqrt(a);  //不可以直接访问默认方法

        System.out.println(defaultMethod.sqrt(4));
        System.out.println(defaultMethod.calculate(100));
    }


}
