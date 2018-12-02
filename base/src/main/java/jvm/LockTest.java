package jvm;

/**
 * @author dmf
 * @date 2018/4/22
 */
public class LockTest {

    public String concatString1(String s1, String s2, String s3) {
        return s1 + s2 + s3;
    }


    public String concatString(String s1, String s2, String s3) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        sb.append(s3);
        return sb.toString();
    }
}
