package improve.annotation;

public class JdbcUtils2 extends JdbcUtils {

	//声明了继承属性，方法上的注解依然在
	@Override
	public void aa() {
		super.aa();
	}

	
}
