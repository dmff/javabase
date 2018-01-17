package matcher;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherTest2 {

	/**
	 * 高级的正则表达式部分
	 */
	
	@Test
	public void test1(){
		/**
		 * 匹配一个数字，可能含有+或者-，也有可能是小数
		 */
		boolean b = "8.333".matches("^[\\-\\+]?(0|[1-9][0-9]*)(\\.[0-9]*)?");
		System.out.println(b);//true
		boolean b2 = "-0.88".matches("^[\\-\\+]?(0|[1-9][0-9]*)(\\.[0-9]*)?");
		System.out.println(b2);//true
		boolean b3 = "+8".matches("^[\\-\\+]?(0|[1-9][0-9]*)(\\.[0-9]*)?");
		System.out.println(b3);//true
		Pattern pattern = Pattern.compile("[\\-\\+]?(0|[1-9][0-9]*)(\\.[0-9]*)?");
		String str = "fdkfjdk5444ffjkjgfkgjkj+343.2jkgfjgk-343234.2djffd0.0fjkgj  -q fdfjk+88.2 dfk23afdf";
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.groupCount());//由于正则表达式有2个括号，所以这里有两个子正则序列
		while(matcher.find()){
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));//由于第二个括号子正则可以不存在，不存在就输出为null
		}
	}
	
	@Test
	public void test2(){
		/**
		 * 研究正则表达式的三种量词匹配方式
		 * 
		 * 1,贪婪型（最常用），就是不断尽可能多的往后匹配，非常贪婪，直到不能匹配为止，如果贪婪到发现无法再吃入为止，
		 * 这个时候开始停止吃入了，再来看整个正则是否匹配，如果能匹配，就匹配，否则，就回吐，再判断。
		 * 
		 * [\\-\\+]?
		 * 0|[1-9][0-9]*
		 * \\.[0-9]*
		 * (\\.[0-9]*)?
		 */
		Pattern pattern = Pattern.compile("[\\-\\+]?(0|[1-9][0-9]*)(\\.[0-9]*)?");
		String str = "fdkfjdk5444ffjkjgfkgjkj+343.2jkgfjgk-343234.2djffd0.0fjkgj  -q fdfjk+88.2 dfk23afdf";
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
		}
		
		System.out.println("------------------------------------------------------");
		
		/**
		 * 勉强型，就是只要检测到刚好匹配，立即停止匹配，不再试探，立即报告匹配成功。
		 */
		Pattern pattern2 = Pattern.compile("[\\-\\+]??(0|[1-9][0-9]*?)(\\.[0-9]*?)??");
		String str2 = "fdkfjdk5444ffjkjgfkgjkj+343.2jkgfjgk-343234.2djffd0.0fjkgj  -q fdfjk+88.2 dfk23afdf";
		Matcher matcher2 = pattern2.matcher(str2);
		while(matcher2.find()){
			System.out.println(matcher2.group());
			System.out.println(matcher2.group(1));
			System.out.println(matcher2.group(2));
		}
		
		System.out.println("------------------------------------------------------");
		
		/**
		 * 进取型，和贪婪型一样，区别是贪婪过后，发现无法再吃入的时候，和正则整体匹配的时候，如果
		 * 发现无法匹配，那么，无法回吐了，无力回天了，只有报告无法匹配成功。
		 */
		Pattern pattern3 = Pattern.compile("[\\-\\+]?+(0|[1-9][0-9]*+)(\\.[0-9]*+)?+");
		String str3 = "fdkfjdk5444ffjkjgfkgjkj+343.2jkgfjgk-343234.2djffd0.0fjkgj  -q fdfjk+88.2 dfk23afdf";
		Matcher matcher3 = pattern3.matcher(str3);
		while(matcher3.find()){
			System.out.println(matcher3.group());
			System.out.println(matcher3.group(1));
			System.out.println(matcher3.group(2));
		}
		
		/**
		 * 下面这个就是无法匹配成功了。
		 */
		Pattern pattern4 = Pattern.compile("a.*+c");
		Matcher matcher4 = pattern4.matcher("abccd");
//		matcher4.find();//
		System.out.println(matcher4.group());
		System.out.println(matcher4.start() + "-" + matcher4.end());
		
		
		/**
		 * ?和*和+和{m,n}的意思是能够匹配越大的优先贪婪匹配更多个，如果后面无法匹配的，可以回溯，调整到匹配少数个数。
		 * ??和*?和+?和{m,n}?的意思是能匹配少数个的优先匹配少数个，如果后面无法匹配，可以回溯，调整到匹配更多数个。
		 * ?+和*+和++和{m,n}+的意思是和贪婪型一致，但是后面无法匹配的，无法回溯，无力回天了。
		 */
	}
	
	
	@Test
	public void test3(){
	
		/**
		 * \b代表单词（a-z，A-Z, 0-9）边界，什么意思呢？\b是零宽度的，也就是说，匹配模式的子序列的右边的零宽度缝隙位置，
		 * 左右两边一边是单词字符，另一边是非单词字符。
		 * 这个\b有什么用呢？好比说，有一个字符串，里面有一个人名单词，我想找这个人名单词，就要用\b来做零宽度的分割。
		 * \bliuyang\b这样的正则表达式。
		 * 
		 * \B代表非单词边界，和\b相反，匹配的子序列右边的零宽度缝隙两边都是单词字符。
		 */
		Pattern pattern = Pattern.compile("[\\-\\+]?(0|[1-9][0-9]*)(\\.[0-9]*)?\\b");
		String str = "fdkfjdk5444ffjkjgfkgjkj+343.2jkgfjgk-343234.2djffd0.0fjkgj  -q fdfjk+88.2 dfk23afdf666a";
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		
		Pattern pattern2 = Pattern.compile("[\\-\\+]?(0|[1-9][0-9]*)(\\.[0-9]*)?\\B");
		String str2 = "fdkfjdk5444ffjkjgfkgjkj+343.2jkgfjgk-343234.2djffd0.0fjkgj  -q fdfjk+88.2 dfk23afdf666a";
		Matcher matcher2 = pattern2.matcher(str2);
		while(matcher2.find()){
			System.out.println(matcher2.group());
		}
		
		
		System.out.println("-------------------------------------------");
		/**
		 * \\A代表匹配的子序列的左边零宽度缝隙是开头的位置。
		 * \\Z代表输入的结束符的缝隙。
		 * \\z代表匹配的子序列右边零宽度缝隙是结尾的位置。
		 * 
		 * 和^和$类似，但是在多行模式下，^和$只是代表一行的开始和结束，而不是整个文本的开始和结束了。在单行模式
		 * 下，他们是相同的意思。
		 */
		Pattern pattern3 = Pattern.compile("\\A[\\-\\+]?(0|[1-9][0-9]*)(\\.[0-9]*)?");
		String str3 = "234fdkfjdk5444ffjkjgfkgjkj+343.2jkgfjgk-343234.2djffd0.0fjkgj  -q fdfjk+88.2 dfk23afdf666a";
		Matcher matcher3 = pattern3.matcher(str3);
		while(matcher3.find()){
			System.out.println(matcher3.group());
		}
		
		Pattern pattern4 = Pattern.compile("[\\-\\+]?(0|[1-9][0-9]*)(\\.[0-9]*)?\\z");
		String str4 = "234fdkfjdk5444ffjkjgfkgjkj+343.2jkgfjgk-343234.2djffd0.0fjkgj  -q fdfjk+88.2 dfk23afdf666";
		Matcher matcher4 = pattern4.matcher(str4);
		while(matcher4.find()){
			System.out.println(matcher4.group());
		}
		
		System.out.println("-----------------------------------------------");
		
		/**
		 * \\G的意思是什么呢？就是说要匹配的缝隙，一定是上一个匹配的结束的地方的缝隙。也就是本轮start（上轮end）所在的地方。
		 * 
		 * 下面看一个例子：邮政编码是5位数字一组，下面的一串数字代表一系列的邮政编码，提取出以44开头的邮政编码
		 * 03824531449411615213441829505344272752010217443235
		 */
		Pattern pattern5 = Pattern.compile("\\G(?:(?!44)\\d{5})*(44\\d{3})");
		String str5 = "03824531449411615213441829505344272752010217443235";
		Matcher matcher5 = pattern5.matcher(str5);
		while(matcher5.find()){
			System.out.println(matcher5.group());
			System.out.println(matcher5.group(1));
		}
		
	}
	
	
	
	@Test
	public void test4(){
		/**
		 * 匹配unicode字符集，这里用中文来匹配
		 */
		Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]*");
		Matcher matcher = pattern.matcher("发动机扩建放得开了解更多考虑国家科技感颗粒");
		boolean b = matcher.matches();
		System.out.println(b);//true
		
		/**
		 * 我们知道，java的字符串是采用unicode字符集的，那么一共有65536个字符，这么多的字符可以用
		 * 用字符本身来代替，也可以用\\u后面加上4位16进制来表示。
		 */
	}
	
	
	@Test
	public void test5(){
		/**
		 * 只分组，但是不捕获，下面虽然有两层括号，看起来有两个捕获组，但是由于第一个括号加
		 * 上了?:，那么这个括号就被排除出捕获组了。这样做的目的是提高了效率。
		 */
		Pattern p1 = Pattern.compile("w(?:(abc)+d)");
		Matcher m1 = p1.matcher("wabcd");
		System.out.println(m1.groupCount());//1
		while(m1.find()){
			System.out.println(m1.group(1));
		}
	}
	
	
	@Test
	public void test6(){
		/**
		 * 用\s来代表空白字符，包括空格符，制表符，回车符，换行符等等
		 */
		Pattern p1 = Pattern.compile("aa[^\\s]*");
		Matcher m1 = p1.matcher("aafdfdfdfdfaa dffaa ffdfdfaafd aafdfdfaa  ");
		while(m1.find()){
			System.out.println(m1.group());
		}
	}
	
	
	@Test
	public void test7(){
		/**
		 * 前面通过\b见识到了零宽度这个概念，也就是说正则表达式可以匹配指定模式前后的空隙
		 * 
		 * 用(?=xxx)来匹配零宽度，这个写法是从左往右来顺序匹配，匹配的还是\\d本身，但是对这个\\d还做了后续空隙
		 * 的要求，就是空隙的右边一定是abc。
		 */
		Pattern p1 = Pattern.compile("\\d(?=abc)");
		Matcher m1 = p1.matcher("dff1abcjfkj3abcjfdkjgfkj8abjkjkg9acjkdj6abcjkkj");
		while(m1.find()){
			System.out.println(m1.group());
		}
		
		System.out.println("------------------------------------------------");
		
		/**
		 * 用(?<=xxx)这样的方式，是从右往左看，匹配的还是\\d，但是这个\\d还做了前面空隙的要求，就是空隙的左边一定是abc
		 */
		Pattern p2 = Pattern.compile("(?<=j)\\d");
		Matcher m2 = p2.matcher("dff1abcjfkj3abcjfdkjgfkj8abjkjkg9acjkdj6abcjkkj");
		while(m2.find()){
			System.out.println(m2.group());
		}
		
		System.out.println("------------------------------------------------");
		
		/**
		 * 综合使用以上两个零宽度写法 ，将\\bjeffs\\b改成\\bjeff's\\b
		 * 
		 * 这个就很牛逼了，可以在指定的一个缝隙中插入一个单引号，太强大了。
		 * 正则表达式不仅仅可以匹配字符序列，还可以匹配一个缝隙位置，(?<=\\bjeff)(?=s\\b)就是匹配了这样一个缝隙位置。
		 */
		Pattern p3 = Pattern.compile("(?<=\\bjeff)(?=s\\b)");
		Matcher m3 = p3.matcher("jeffs child a good boy named jeffsB.jeff loves his wife and jeffs wife loves him too.i think jeff has a happy family");
		String newStr = m3.replaceAll("'");
		System.out.println(newStr);
		
		/**
		 * ^代表了行的开头的缝隙，$代表了行结尾的缝隙。下面这个就是在行开头插入BS:
		 * 现在明白^agdgd$这样的正则表达式的含义了 吧。
		 */
		Pattern p4 = Pattern.compile("^");
		Matcher m4 = p4.matcher("jeffs child a good boy named jeffsB.jeff loves his wife and jeffs wife loves him too.i think jeff has a happy family");
		String newStr2 = m4.replaceAll("BS:");
		System.out.println(newStr2);
		System.out.println("------------------------------------------------");
		
		/**
		 * 给数字添加逗号，比如1343443434334变成1,343,443,434,334，那个?:就不捕获了，为了提高效率嘛。
		 */
		Pattern p5 = Pattern.compile("(?<=\\d)(?=(?:\\d{3})+$)");
		Matcher m5 = p5.matcher("1343443434334");
		String newStr3 = m5.replaceAll(",");
		System.out.println(newStr3);
		
		/**
		 * 如果插入的逗号是下面这样的字符串呢？那么，在(?:\\d{3})+后面的空隙的右侧一旦不是一个数字，所以用这样的表达式
		 * 空隙后面不能是什么，空隙前面不能是什么，这个时候需要用否定方式(?!X)和(?<!X)，很简单啦，和前面的类似。
		 * 
		 * 注意，这里不能用(?=\\D)，因为这样会强制要求缝隙后面有一个不是数字的字符，但是如果缝隙后面没有任何字符就不匹配了，
		 * 这是不正确的，所以(?=\\D)和(?!\\d)并不等价。
		 */
		Pattern p7 = Pattern.compile("(?<=\\d)(?=(?:\\d{3})+(?!\\d))");
		Matcher m7 = p7.matcher("afdfdfdf1343443434334adfdffdfgghg");
		String newStr5 = m7.replaceAll(",");
		System.out.println(newStr5);
	}
	
	
	
	@Test
	public void test8(){
		/**
		 * 把afdfdfdf18672993770fdjfkdfj18717027336djfkdj15874316251fjdf中的数字手机号提取出来，
		 * 并添加上<tel></tel>标签:,例如变成<tel>18672993770</tel>
		 */
		Pattern pattern = Pattern.compile("1[358][0-9]{9}");
		Matcher matcher = pattern.matcher("afdfdfdf18672993770fdjfkdfj18717027336djfkdj15874316251fjdf");
		String newStr = matcher.replaceAll("<tel>$0</tel>");
		System.out.println(newStr);
		/**
		 * 这里的$0代表的就是捕获组中的被匹配的序列本身，加上标签！当然，如果有子捕获组，也可以使用$1,$2等。
		 * 使用String的replaceAll也是一样的。当然也可以使用\0和\1这样，和$的意思是一样的。
		 */
		String newStr2 = "afdfdfdf18672993770fdjfkdfj18717027336djfkdj15874316251fjdf".replaceAll("1[358][0-9]{9}", "<tel>$0</tel>");
		System.out.println(newStr2);
		
		/**
		 * 还有1个地方也可以这样用，就是appendReplacement，注意这些地方替换的字符串中不能出现$和\,
		 * 因此，添加的字符串需要用Matcher.quoteReplacement来转义一下。
		 */
	}
	
	
	@Test
	public void test9(){
		/**
		 * 反向引用，这个可高级了！！！可以生成一个和给定字符串相匹配的pattern出来！！
		 * 
		 * 默认是用\\Q和\\E包裹起来的String的字面量，就是此字面量的pattern，可以将特殊字符自动转义
		 */
		System.out.println(Pattern.quote("abc"));//\Qabc\E
		System.out.println(Pattern.quote("/abc/ddfd/*/adb/**/dffd"));//\Q/abc/ddfd/*/adb/**/dffd\E
		
		/**
		 * 我们在做替换字符串的时候，如果字符串中包含$和\两个字符，那么会出错~
		 * 
		 * 为了解决这个问题，我们在做替换字符串的时候，可以将字符串中包含的这两个字符转义
		 * 用Matcher类的静态方法quoteReplacement方法
		 */
		System.out.println(Matcher.quoteReplacement("fdgjk$fdfdfdfdffd"));
		System.out.println("redjkgjdfdfd".replaceAll("[ej]", Matcher.quoteReplacement("a")));
		
	}
	
	
	
}
