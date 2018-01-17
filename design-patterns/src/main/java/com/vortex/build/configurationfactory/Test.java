package com.vortex.build.configurationfactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Test {

	public static void main(String[] args) throws SQLException {
	  Configuration.ConnectionFactory connectionFactory	= new Configuration().build();
	  Connection connection = connectionFactory.openConnection();
	  
	  //ConnectionFactory connectionFactory = new ConnectionFactoryBuilder().build();
	  //Connection connection = connectionFactory.openConnection();
	  
	  PreparedStatement statement = connection.prepareStatement("insert into users(name,sex,age) values(?,?,?);");
	  statement.setString(1, "dx");
	  statement.setString(2, "female");
	  statement.setInt(3, 18);
	  
	  statement.executeUpdate();
	  
	  statement.close();
	  connection.close();
	  
	}
}
