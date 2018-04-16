package org.pxxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.pxxy.domain.User;
import org.pxxy.utils.ConnectionMySQL;

public class UserDao {

	static Connection connection = null;
	static PreparedStatement preparedStmt = null;
	static Statement stmt = null;
	static ResultSet resultSet = null;

	public User login(User user) {
		try {
			connection = ConnectionMySQL.getCon();
			String sql = "select * from user where userName='" + user.getUserName() + "' and password='"
					+ user.getPassword() + "'";
			preparedStmt = connection.prepareStatement(sql);
			resultSet = preparedStmt.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setUserName(resultSet.getString("userName"));
				user.setPassword(resultSet.getString("password"));
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
