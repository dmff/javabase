package math;

/**
 * @author dmf
 * @date 2018/1/25
 *
 * 二进制工具类:
 *      1.判断一个数是否为2的幂次方
 *      2.操作位代表类型
 *      3.非2次幂方转化为2次幂方
 *      4.把整数打印成二进制
 */
public class BinaryUtils {

    public static final int bitCount = 32;

    public static void main(String[] args) {
        //printBinaryInt(-16);
        System.out.println(tableForSize(151));
        System.out.println(tableForSize2(151));
    }
    /**
     * java打印整数二进制表示
     * @param a
     */
    public static void printBinaryInt(int a){
        for(int i=0;i<bitCount;i++){
            int t=(a & 0x80000000 >>> i) >>>(31-i);
            System.out.print(t);
        }
    }

    /**
     * 判断一个数是否为2的幂次方
     * 解析：如果一个数为2的幂次方，那么它高位表示1，低位为0；减1的话，高位为0，低位为1；相与的话一定为0
     * @param val
     * @return
     */
    public static boolean isPowerOfTwo(int val){
        return (val & (val-1)) == 0;
    }

    /**
     * 这是通过2的幂次方的负数，只是符号位改变，其他位没变的性质
     * @param val
     * @return
     */
    public static boolean isPowerOfTwo2(int val){
        return (val & (-val)) == val;
    }

    /**
     * 判断一个无符号数是2的n次方-1,思路和判断是否为2的幂次方的第一种思路一样
     * @param val
     * @return
     */
    public static boolean isPowerOfTwoLostOne(int val){
        return (val & (val+1)) == 0;
    }


    /***
     * jdk的nio的SelectionKey的操作位代表类型
     * 这样做的好处是可以非常方便的通过位操作来进行网络操作位的状态判断和状态修改，提升操作性能。
     * OP_READ = 1 << 0
     * OP_WRITE = 1 << 2
     * OP_CONNECT = 1 << 3
     * OP_ACCEPT = 1 << 4
     */

    /**
     * 由于集合的大小都会是2的幂次方，那么求table大小的0.75倍的时候，可以使用（n - (n >>> 2)）即可，取模的时候，可以使用hash & 0x7FFFFFFF来进行操作即可。
     * 求一个数离它最近的大于等于2的幂次方的数
     * @return
     */
    public static int tableForSize(int val){
        int n = val-1;
        n |=n>>>1;
        n |=n>>>2;
        n |=n>>>4;
        n |=n>>>8;
        n |=n>>>16;
        return (n<0) ? 1 : (n>=1024) ? 1024 : n+1;
    }

    /**
     * 求小于等于2的幂次方的数
     * @return
     */
    public static int tableForSize2(int val){
        int n = val-1;
        n |=n>>>1;
        n |=n>>>2;
        n |=n>>>4;
        n |=n>>>8;
        n |=n>>>16;
        return n-(n>>1);
    }
}
