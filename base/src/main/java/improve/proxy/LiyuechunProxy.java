package improve.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LiyuechunProxy {

	private Person chunchun = new Liyuechun();
	
	public Person getProxy(){
		
		return (Person) Proxy.newProxyInstance(LiyuechunProxy.class.getClassLoader(), chunchun.getClass().getInterfaces(), new InvocationHandler() {
			
			/**
			 * proxy : 把代理对象自己传递进来
			 * method：把代理对象当前调用的方法传递进来
			 * args:把方法参数传递进来
			 */
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                 //编码指定返回的代理对象干的工作	
				if(method.getName().equals("sing")){
					System.out.println("搞一万块钱来！！");
					return method.invoke(chunchun, args);  //找春哥唱歌
				}
				if(method.getName().equals("dance")){
					System.out.println("搞2万块钱来！！");
					return method.invoke(chunchun, args);  //找春哥跳舞
				}	
				return null;
			}
		});
		
	}
}
