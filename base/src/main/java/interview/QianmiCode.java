package interview;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 千米网编程题：
 *      1.菲比那切数列
 *      2.字符串左移
 *      3.文件复制
 *      4.在数组中找出两个出现一次的数
 *      5.在无序数组中使用二分查找
 */
public class QianmiCode {

    //1 1 2 3 5 8 13
    public int fibinaqie(int n){
        if (n<=0)
            return 0;
        if (n==1 || n==2)
            return 1;
        int a=1,b=1;
        while(n-->2){
            a = a+b;
            b = a-b;
        }
        return a;
    }

    /**
     * 字符串左移
     * 移动的长度 abcde--->cdeab
     *
     */
    public String  converser(String str,int index){
        if (str==null || str.equals(""))
            return str;
        char[] chars = str.toCharArray();
        swap(chars,0,index-1);
        swap(chars,index,chars.length-1);
        swap(chars,0,chars.length-1);
        return new String(chars);
    }
    
    private void swap(char[] chars, int start,int end){
        while (start<end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 简单的文件复制
     * @param src
     * @param desc
     * @throws IOException
     */
    public void copy(String src,String desc) throws IOException{
        FileInputStream  in = new FileInputStream(src);
        FileOutputStream  out = new FileOutputStream(desc);

        byte[] buf = new byte[in.available()];
        int index=0;
        while((index=in.read(buf,0,buf.length))!=-1){
            out.write(buf);
            out.flush();
        }
        in.close();
        out.close();;

    }
    public void appear_once(int[] arr,int[] result){
        if (arr==null || arr.length<2)
            return;
        int sum = 0;
        for(int i=0;i<arr.length;i++){
            sum ^=arr[i];
        }

        //获取出现1的二进制的位置
        int index;
        for(index=0;index<32;index++){
            if ((sum & (1>>index))!=0)
                break;
        }
        //开始分组
        for(int i=0;i<arr.length;i++){
            if ((arr[i] & (1>>index))!=0){
                result[0] ^=arr[i];
            }else {
                result[1] ^=arr[i];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        QianmiCode qianmi = new QianmiCode();
        int fibinaqie = qianmi.fibinaqie(6);
        System.out.println(fibinaqie);

        String converser = qianmi.converser("abcde", 2);
        System.out.println(converser);

        //qianmi.copy("E:\\poi.xls","E:\\poi_copy.xls");

        int[] arr={2,2,1,3,3,4};
        int[] result = new int[2];
        qianmi.appear_once(arr,result);
        System.out.println(Arrays.toString(result));
    }
}
