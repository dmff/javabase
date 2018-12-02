package matcher;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherTest {

	@Test
	public void test1(){
		/**
		 * 使用String类自带的matches和splits方法，.代表任意字符。
		 * 
		 * 转义字符在正则表达式的字符串中都应该使用双斜线，正斜线本身的正则表达式是4个斜线。
		 */
		boolean b1 = "abc".matches(".*");
		System.out.println(b1);//true
		boolean b2 = "abc".matches("a.c");
		System.out.println(b2);//true
		boolean b3 = "abc".matches(".{3}");
		System.out.println(b3);//true
		boolean b4 = "abc\t\n".matches("abc\\t\\n");
		System.out.println(b4);//true
		String[] strs = "ab,cd".split("\\,");
		System.out.println(strs[0] + strs[1]);//abcd
		/**
		 * 如果我们想表达‘\’这个字符串，那么，应该使用转义"\\"，但是在正则表达式中，就需要4个\\\\了。
		 */
		boolean b5 = "abcd\\ef".matches("abcd\\\\ef");
		System.out.println(b5);//true
		
		/**
		 * 如果想要使用点号字符串本身，需要用转义"\\."
		 * 
		 * 其实很多都需要转义的，比如=,+,{,},?,.,*,^,$,[,],&,-,,,!,_
		 */
		boolean b6 = "a.b".matches("a\\.b");
		System.out.println(b6);//true
		
		
		/**
		 * 字符串的replaceAll方法，很好用，只要匹配正则的都会被替换 
		 */
		String newStr = "abcdefd003fdfd103".replaceAll("\\d", "");
		System.out.println(newStr);//abcdefgfdfd
		
		String newStr2 = "abcdefg003fdfd133".replaceAll("\\D", "0");
		System.out.println(newStr2);//00000000030000133
		
		String newStr3 = "abcdefd003fdfd103".replaceAll("fd|03", "");
		System.out.println(newStr3);//abcdefgfdfd
	}
	
	
	@Test
	public void test2(){
		/**
		 * 测试转义字符和特殊区间
		 */
		//空白字符 /s和非空白字符\\D
		String newStr = "abc   \t  ".replaceAll("\\s", "");
		System.out.println(newStr);
		
		//数字\\d和非数字\\D,测试略。。
		
		//英文单词字符\\w和非英文单词字符\\W，测试略。
		
		//[a-zA-Z]，英文单词的26个大小写字母，可以用[d-gH-O]这样的写法
		boolean b = "efKJ".matches("[d-gH-O]{4}");
		System.out.println(b);//true
		
		
		//[0-9]，数字字符，但是也可以用[5-8]这样的写法
		boolean b2 = "678".matches("[5-8]{3}");
		System.out.println(b2);//true
		
		//[a-zA-Z_0-9]等价于\\w，但是很多时候，我们想缩短一些距离，比如[d-gH-O_5-8]
		boolean b3 = "efKJ678".matches("[d-gH-O_5-8]*");
		System.out.println(b3);//true
		
		//可是有些时候想表达a-e和h-j这样的区间，写法是[a-e[h-j]]
		boolean b4 = "acij".matches("[a-e[h-j]]*");
		System.out.println(b4);//true
		
		//不包含tfg的字符
		boolean b5 = "aaaabbb,,..".matches("[^tfg]*");
		System.out.println(b5);//true
		
		//不包括tfg的英文字符
		boolean b6 = "aaaabbb,,..".matches("[\\w&&[^tfg]]*");
		System.out.println(b6);//false
		
		//不包括a-d和0-4的英文字符
		boolean b7 = "gfgfgf6678".matches("[\\w&&[^a-d_0-4]]*");
		System.out.println(b7);//true
		
		//其实内部也可以不要这个[]分隔的，只是分隔起来看起来直观一些，0-6，a-t,H-O,_,+,逗号共4个字符合起来。
		boolean b8 = "3bI_".matches("[0-6a-tH-O\\_\\,\\+]{4}");
		System.out.println(b8);//true
		
		//注意，排除的^符号只能在[^]中使用，不可在其他地方使用。下面的模式表示ab和cd之间不能夹着任何e或者f的一个或多个字符。
		boolean b9 = "abhhcd".matches("ab[^ef]+cd");
		System.out.println(b9);//true
		
		//?代表出现一次或0次。
		boolean b10 = "color".matches("colou?r");
		System.out.println(b10);//true
		boolean b11 = "colour".matches("colou?r");
		System.out.println(b11);//true
		
		//至少出现3次
		boolean b12 = "abeeeeecd".matches("abe{3,}cd");
		System.out.println(b12);//true
		boolean b13 = "abeecd".matches("abe{3,}cd");
		System.out.println(b13);//false
		
		//以ab或者cd开头的匹配
		boolean b14 = "abeeedfdfd".matches("^(ab|cd).*");
		System.out.println(b14);//true
		boolean b15 = "cdeeedfdfd".matches("^(ab|cd).*");
		System.out.println(b15);//true
		
		//以xy结尾的
		boolean b16 = "dfdfdfdfsxy".matches(".*(xy)$");
		System.out.println(b16);//true
		
		//以ab开头，xy结尾的
		boolean b17 = "abdjfkdljdkljkdjxy".matches("^(ab).*(xy)$");
		System.out.println(b17);//true
	}
	
	
	@Test
	public void test3(){
		/**
		 * 一个正则表达式的字符串，是通过Patttern类的compile方法来编译出来的
		 * 
		 * 这个Pattern类是线程安全的，可以供多个线程使用。
		 * 
		 * matches方法是将pattern和整个字符序列进行匹配，如果完全匹配，那么返回true，否则返回false。
		 */
		Pattern pattern = Pattern.compile("a.*b");
		Matcher matcher = pattern.matcher("adddddb");
		boolean b = matcher.matches();
		System.out.println(b);//true
		
		Matcher matcher2 = pattern.matcher("afdfdbbb");
		boolean b2 = matcher2.matches();
		System.out.println(b2);//true
	}
	
	
	@Test
	public void test4(){
		Pattern pattern = Pattern.compile("abc");
		String str = "fdjkjfklgjfklgjdklajaljklabjabclbjklajklajkabcjkaljklabjkfabjaklbjkabaabc";
		//得到一个关联此str字符串的matcher匹配器
		Matcher matcher = pattern.matcher(str);
		//显然在此调用matcher.matches方法返回的是false
		System.out.println(matcher.matches());//false
		/**
		 * 有些时候，我们并不是完全匹配，既然matcher比较器有自己的模式，也包含了str字符序列，
		 * 那么显然有更高级的玩法，比如find方法
		 * 
		 * 这个find方法是什么意思呢？就是在str这个字符序列中查找是否存在匹配模式"abc"的字符子序列。显然是有的。
		 * start方法返回的int值是什么呢？是前一次find匹配成功之后，匹配子序列的起始位置。end方法返回的是什么呢？
		 * 返回的是上一次成功匹配的子序列末尾结束位置+1.
		 */
		while(matcher.find()){
			System.out.println("找到了一个子序列");
			int start = matcher.start();
			int end = matcher.end();
			System.out.println(start);
			System.out.println(end);
		}
		/**
		 * 结果是输出了三次：
		 * 找到了一个子序列
		 * 找到了一个子序列
         * 找到了一个子序列
         * 
         * 为什么会这样呢？当第一次调用find方法的时候，是从整个str这个字符序列的头部开始查找的，find方法如果
         * 找到了一个符合pattern的子序列，那么直接返回true了。而find方法内部有两个指针，记录此时查找str停留的
         * 地方。start指针是上一个find到的子序列的起始位置，而end是下一次开始查找的起始位置。
		 */
		
		/**
		 * 重置这个matcher，他还有一个重载的方法，matcher.reset(CharSequence);可以重置一个新的字符序列。
		 */
		matcher.reset();
		
		Pattern p2 = Pattern.compile("\\d{3,5}");
		String str2 = "1234";
		Matcher m2 = p2.matcher(str2);
		System.out.println(m2.matches());
		System.out.println(m2.start()+"-"+m2.end());//0-4
		/**
		 * 由于matches方法相当于是一次整体的find，如果找到了，那么，start是0，end是4
		 */
		m2.reset();
		
		
		
		/**
		 * 重点是这个了，p3是3到5位的数字，那么find方法首先找到了345，4567，56666，555
		 * 
		 * 重点是56666，因为这个find方法的end指针往后找直到找到第一个不满足pattern的字符为止。
		 */
		Pattern p3 = Pattern.compile("\\d{3,5}");
		Matcher m3 = p3.matcher("12-345-4567-5666666-555");
		System.out.println(m3.matches());
		m3.reset();
		while(m3.find()){
			System.out.println(m3.start()+"-"+m3.end());
		}
		m3.reset();
	}
	
	
	
	@Test
	public void test5(){
		/**
		 * 启动大小写不敏感的匹配
		 */
		String str = "java Java jAva IloveJava Youhatejava java JJAva JVAVAJAVA jjjvajava";
		String newStr = str.replaceAll("[Jj][Aa][Vv][Aa]", "java");//将所有符合pattern的都替换成小写java，这个写法真的是高明啊！
		System.out.println(newStr);
		
		Pattern pattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);//启用不区分大小写
		Matcher matcher = pattern.matcher(str);
		String newStr2 = matcher.replaceAll("java");//将这个matcher内部持有的字符串把所有符合pattern，也就是不区分大小写的java都替换成小写java。
		System.out.println(newStr2);
		
		/**
		 * (?i)是从此处开启不区分大小写，(?-i)是从此处关闭大小写。
		 * 
		 * (?i:abc)def这是一种局部写法，而(?!)是此开关后面都受到影响
		 */
		Pattern p1 = Pattern.compile("(?i).+");
		Matcher m1 = p1.matcher("fdfdgjdgjdgj  ggaggAGFGFGFHFHH");
		System.out.println(m1.matches());

		Pattern p2 = Pattern.compile("(?i)abc(?-i)abc");
		Matcher m2 = p2.matcher("ABCaBc");
		System.out.println(m2.matches());
		
		/**
		 * 启动行结束符,点号可以代表一切字符，包括行结束符。
		 */
		String str2 = "jdkfjdklfdfdfd" + System.getProperty("line.separator");
		Pattern pattern2 = Pattern.compile(".*", Pattern.DOTALL);
		Matcher matcher2 = pattern2.matcher(str2);
		boolean b = matcher2.matches();
		System.out.println(b);//true
		
		Pattern pattern3 = Pattern.compile(".*");
		Matcher matcher3 = pattern3.matcher(str2);
		boolean b2 = matcher3.matches();
		System.out.println(b2);//false
		
		
		/**
		 * 启动多行模式，会对^和$有行的影响。
		 */
		Pattern pattern4 = Pattern.compile("^java", Pattern.MULTILINE);
		Matcher matcher4 = pattern4.matcher("dfjkdjfdj" + System.getProperty("line.separator") + "javafjddkj" + System.getProperty("line.separator") + "javaefrjkrjt");
		while(matcher4.find()){
			System.out.println(matcher4.group());
			System.out.println(matcher4.start() + "-" + matcher4.end());
		}
		
		/**
		 * 如果想启动多个模式，用int的位运算 或 
		 * Pattern pattern5 = Pattern.compile("^java", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		 */
	}
	
	
	
	@Test
	public void test6(){
		String str = "java Java jAva IloveJava Youhatejava java JJAva JVAVAJAVA jjjvajava";
		Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		int count = 0;
		StringBuffer buf = new StringBuffer();
		while (m.find()) {
			count++;
			if (count % 2 == 1) {
				m.appendReplacement(buf, "JAVA");
			} else {
				m.appendReplacement(buf, "java");
			}
		}
		m.appendTail(buf);
		System.out.println(buf);
		/**
		 * 上面这是什么意思呢？就是说一旦find找到了，那么将整个str字符序列的这一次end - 1的内容添加到stringbuffer，并用给定的
		 * 字符串替换掉本次被匹配的子序列。为什么最后还要m.appendTail(buf)呢？就是把整个最后没有匹配内容的尾巴子序列添加到buf中。
		 * 
		 * 这个功能相当于，将每次匹配的内容替换成指定的字符串，存放到一个StringBuffer中，达到替换的目的。那直接用replaceAll不就行了吗？
		 * 但是我们看到了，用这样的方式可以指定每一次替换的不同内容，比如奇数次的替换成JAVA，偶数次的替换成java。
		 */
	}
	
	
	
	@Test
	public void test7(){
		/**
		 * 正则表达式的分组
		 */

		/**
		 * group方法返回此前一次匹配的子序列，相当于找到一个就输出一个匹配的子序列，这个group方法非常有用。
		 * 
		 * 他等价于上一次匹配成功的start到end-1之间的字符序列
		 */
		String str = "java Java jAva IloveJava Youhatejava java JJAva JVAVAJAVA jjjvajava";
		Pattern pattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);//启用不区分大小写
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.groupCount());//0个分组，0组表示整个pattern本身，他没有子正则
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		
		/**
		 * 首先要搞清楚什么是分组，在正则表达式的字符串中，如果用（）括起来一个表达式，那么这就是一个分组的子正则
		 * 
		 * 下面的a((\\d+)([a-z]+([A-Z]+)))正则表达式一共有4个子正则分组，分别是
		 * ((\\d+)([a-z]+([A-Z]+)))
		 * (\\d+)
		 * ([a-z]+([A-Z]+))
		 * ([A-Z]+)
		 * 
		 * 0组永远表示整个模式本身
		 */
		String str2 = "affda98fdfHHajbkfkda8jKdfjkjga999HHzzfa777aafdHHIfdfkja888hJ";
		Pattern pattern2 = Pattern.compile("a((\\d+)([a-z]+([A-Z]+)))");//启用不区分大小写
		Matcher matcher2 = pattern2.matcher(str2);
		System.out.println(matcher2.groupCount());//4个分组
		while(matcher2.find()){
			for(int i=0;i<=matcher2.groupCount();i++){//注意，在这里group()和group(0)是一回事
				System.out.println(matcher2.group(i));
				System.out.println(matcher2.start(i) + "-" + matcher2.end(i));
			}
			/**
			 * hitEnd方法的意思是找到的匹配项目是否已经是输入序列的最结尾部分
			 */
			System.out.println(matcher2.hitEnd());
		}
	}
}
