package improve.annotation;

import java.sql.Connection;

public class JdbcUtils {

	@DbInfo(url="jdbc:mysql://localhost:3306/test",username="flx",password="root")
	public static Connection getConn(String url,String username,String password){
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		return null;
	}
	
	@DbInfo(url="jdbc:mysql://localhost:3306/test",username="flx",password="root")
	public void aa(){
		
	}
}
