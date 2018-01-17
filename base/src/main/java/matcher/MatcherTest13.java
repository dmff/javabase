package matcher;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherTest13 {

    /**
     * 高级匹配
     */

    @Test
    public void test(){
        /**
         * 匹配这个字符串中以44开头的数字,(?<!\\d)说明头部缝隙前面不能是数字
         * dffdjkfjdkfjnrnvm434343fgkfjgfkladf445454dfdjkgfjwee433323fjfj44554fdfd44fdflj3434445jfkjg666
         */
        Pattern pattern = Pattern.compile("(?<!\\d)44\\d*");
        Matcher matcher = pattern.matcher("dffdjkfjdkfjnrnvm434343fgkfjgfkladf445454dfdjkgfjwee433323fjfj44554fdfd44fdflj3434445jfkjg666");
        while(matcher.find()){
            System.out.println(matcher.group());
        }

        System.out.println("------------------------------");

        /**
         * 但是现在有一个需求，要找出所有不以44开头的数字怎么做？
         * 显然，^是不可以用的。
         *
         * 我们思考着一个问题，不以44开头，说明了我们要找的这个子序列的前面的空隙位置后面不能有44，同时这个缝隙前面不能有数字，
         * 所以我们要先找到空隙，然后加上两个限制条件，(?<!\\d)(?!44),然后后面是\\d+
         */
        Pattern pattern2 = Pattern.compile("(?<!\\d)(?!44)\\d+");
        Matcher matcher2 = pattern2.matcher("dffdjkfjdkfjnrnvm434343fgkfjgfkladf445454dfdjkgfjwee433323fjfj44554fdfd44fdflj3434445jfkjg666");
        while(matcher2.find()){
            System.out.println(matcher2.group());
        }
    }

    @Test
    public void test2(){
        /**
         * 不包含abc的字符串匹配，当然了，我们可以用string类的contains方法
         */
        String str1 = "fjkgl;jgfgfglf;gk;lfgflgkfkgjkjkfjdkabgjkjkabcgkfgjkgj;ljgkfjrgjntkl;ngtl";
        String str2 = "kgjkgjlj;klhgkhgklmgklm;fmsgdkg;sgdf";
        System.out.println(str1.matches("(?:(?!abc).)*"));//false
        System.out.println(str2.matches("(?:(?!abc).)*"));//true

        System.out.println(str1.contains("abc"));//true
        System.out.println(str2.contains("abc"));//false
    }
}
