package org.pxxy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

	// 驱动名
	static final String driverName = "com.mysql.jdbc.Driver";
	// 数据库路径
	static final String dbURL = "jdbc:mysql://localhost:3306/infopub";
	// 数据库用户名
	static final String userName = "root";
	// 数据库密码
	static final String userPwd = "root";
	// 数据库连接对象
	static Connection dbConn = null;

	// 加载数据库驱动
	static {
		try {
			Class.forName(driverName);
			System.out.println("加载数据库驱动成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("加载数据库驱动失败");
		}
	}

	// 得到数据库连接对象
	public static Connection getCon() throws SQLException {
		if (dbConn == null) {
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return dbConn;
		}
		return dbConn;
	}

}
