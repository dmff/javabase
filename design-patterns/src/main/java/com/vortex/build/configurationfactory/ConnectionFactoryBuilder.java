package com.vortex.build.configurationfactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionFactoryBuilder {

	private static Properties properties = new Properties();
	static{
		try {
			properties.load(ConnectionFactoryBuilder.class.getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			throw new RuntimeException("classpath缺失jdbc.properties配置文件!",e);
		}
	}
	
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	
	public ConnectionFactoryBuilder(){
		url = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
		driverClassName = properties.getProperty("driver");
	}
	
	public String getUrl() {
		return url;
	}

	public ConnectionFactoryBuilder setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public ConnectionFactoryBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public ConnectionFactoryBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public ConnectionFactoryBuilder setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
		return this;
	}

	
	
	
	public ConnectionFactory build(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		ConnectionFactory connectionFactory =  new ConnectionFactory();
		connectionFactory.setDataSource(dataSource);
		return connectionFactory;
	}

	
	public static class ConnectionFactory {

		private DataSource dataSource;
		
		private ConnectionFactory(){}
		
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
		public Connection openConnection() throws SQLException{
			return dataSource.getConnection();
		}
		
		public DataSource getDataSource() {
			return dataSource;
		}
	}

}