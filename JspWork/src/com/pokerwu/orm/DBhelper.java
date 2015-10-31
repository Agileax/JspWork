package com.pokerwu.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author pokerWu
 *
 * @email pokerwu1994@gmail.com
 */
public class DBhelper {
	private final static String URL = "jdbc:mysql://192.168.2.103:3306/jspwork?useUnicode=true&amp;characterEncoding=UTF-8";
	private final static String NAME = "root";
	private final static String PASSWORD = "root";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
