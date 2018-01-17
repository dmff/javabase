package improve.generic;

/*
import org.hibernate.Session;

//声明泛型，对哪个类进行操作
public class BaseDao<T> {

	private Session session;
	private Class clazz;
	
	//哪个子类掉这个这方法，得到的class就是子类处理的类型(非常重要，通用的dao方法)
	public BaseDao(){
		Class clazz = this.getClass(); //拿到的是子类
		ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
		clazz = (Class) pt.getActualTypeArguments()[0];
		System.out.println(clazz);
	}
	
	public void add(T t){
		session.save(t);
	}
	
	public void update(T t){
		session.update(t);
	}
	
	public T find(String id){
		return (T) session.get(clazz, id);
	}
	
	public void delete(String id){
		T t = (T) session.get(clazz, id);
		session.delete(t);
	}
}*/
