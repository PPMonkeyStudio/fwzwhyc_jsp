package org.pxxy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.pxxy.dao.UserDao;
import org.pxxy.domain.User;
import org.pxxy.utils.ConnectionMySQL;

public class UserDaoImpl implements UserDao {

	public static Connection connection = null;
	public static PreparedStatement preparedStmt = null;
	public static Statement stmt = null;
	public static ResultSet resultSet = null;

	@Override
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
