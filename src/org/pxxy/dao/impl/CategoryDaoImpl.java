package org.pxxy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.pxxy.dao.CategoryDao;
import org.pxxy.domain.Category;
import org.pxxy.utils.ConnectionMySQL;

public class CategoryDaoImpl implements CategoryDao {

	/*
	 * 
	 */

	public static Connection connection = null;
	public static PreparedStatement preparedStmt = null;
	public static Statement stmt = null;
	public static ResultSet resultSet = null;

	/*
	 * 
	 */

	@Override
	public List<Category> findAllCategory() {

		List<Category> categoryList = new ArrayList<Category>();

		Category category = null;

		try {

			connection = ConnectionMySQL.getCon();

			String sql = "select * from category";

			preparedStmt = connection.prepareStatement(sql);

			resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {

				category = new Category();

				category.setCid(resultSet.getInt("cid"));

				category.setCname(resultSet.getString("cname"));

				categoryList.add(category);

			}

			return categoryList;

		} catch (SQLException e) {

			e.printStackTrace();

			return categoryList;

		}
	}

	@Override
	public boolean addCategory(Category category) {

		try {

			connection = ConnectionMySQL.getCon();

			String sql = "insert into category values('" + category.getCid() + "','" + category.getCname() + "')";

			preparedStmt = connection.prepareStatement(sql);

			preparedStmt.execute();

			return true;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean delCategory(Category category) {

		try {

			connection = ConnectionMySQL.getCon();

			String sql = "delete from category where cid='" + category.getCid() + "' ";

			stmt = connection.prepareStatement(sql);

			stmt.executeUpdate(sql);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Category findCategoryByCid(Integer cid) {
		return null;
	}

	@Override
	public boolean updateCategory(Category category) {
		return true;
	}

	@Override
	public List<Category> findNaviCategory() {
		return null;
	}

}
