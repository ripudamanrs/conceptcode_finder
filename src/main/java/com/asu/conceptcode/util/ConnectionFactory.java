package com.asu.conceptcode.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		String dbURL = "jdbc:mysql://localhost:3306/my_db?autoReconnect=true&useSSL=false";
		String user = "root";
		String pwd = "Ljunberg@08";
		DbConnectionManager connectionManager = null;
		try {
			connectionManager = new DbConnectionManager(dbURL, user, pwd);
		//	System.out.println("Db Connection initialized successfully.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connectionManager.getCon();
	}
}
