package math;

/**
 * @author dmf
 * @date 2018/1/24
 *
 * java进制表示测试
 * 不能直接表示二进制
 * 8进制：09
 * 10进制：不需要前缀
 * 16进制：0x或者0X
 */
public class BinaryTest {

    public static void main(String[] args) {
        //16进制的表示方法,可以表示10相当于16
        System.out.println(0x25);
        System.out.println(0X12);

        //8进制的表示方法,10相当于8
        System.out.println(012);

        //10进制的表示方法
        System.out.println(15);

        //表示：1640531527；(long) ((1L << 31) * (Math.sqrt(5) - 1))可以得到2654435769
        System.out.println(0x61c88647);

        System.out.println(Float.toHexString(-9.0f));
        printBinaryInt(4);
    }

    /**
     * java打印整数二进制表示
     * @param a
     */
    public static void printBinaryInt(int a){
        for(int i=0;i<32;i++){
            int t=(a & 0x80000000 >>> i) >>>(31-i);
            System.out.print(t);
        }
    }

}
