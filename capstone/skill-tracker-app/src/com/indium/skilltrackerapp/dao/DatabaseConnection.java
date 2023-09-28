package com.indium.skilltrackerapp.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseConnection {
	
	 private static Connection connection;

	    private DatabaseConnection() {
	    }

	    public static Connection getConnection() {
	        if (connection == null) {
	            try {
	                MysqlDataSource dataSource = new MysqlDataSource();
	                dataSource.setServerName("localhost");
	                dataSource.setDatabaseName("training");
	                dataSource.setUser("root");
	                dataSource.setPassword("Vijay@2304");

	                connection = dataSource.getConnection();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return connection;
	    }

}
