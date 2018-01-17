package com.vortex.build.configurationfactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;



/**
 * 模拟hibernate通过工厂获取数据库的连接
 * @author dmf
 *
 */
public class Configuration {

	private static Properties properties = new Properties();
	
	static{
		
		try {
			properties.load(Configuration.class.getResourceAsStream("jdbc.properties"));	
		} catch (IOException e) {
			throw new RuntimeException("不能读取当前class下的配置文件", e);
		}	
	}
	
	private String url;
	private String username;
	private String password;
	private String driver;
	
	public Configuration() {
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
	}
	
	
	//------------------采用链式编程--------------------------//
	public String getUrl() {
		return url;
	}

	public Configuration setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public Configuration setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Configuration setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getDriver() {
		return driver;
	}

	public Configuration setDriver(String driver) {
		this.driver = driver;
		return this;
	}
	
	public ConnectionFactory build(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driver);
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setDataSource(dataSource);
		
		return connectionFactory;
	}

	//连接工厂
	public static class ConnectionFactory{
		private DataSource dataSource;
		
		private ConnectionFactory(){}

		public DataSource getDataSource() {
			return dataSource;
		}

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
		public Connection openConnection() throws SQLException{
			return dataSource.getConnection();
		}
		
	}
}
