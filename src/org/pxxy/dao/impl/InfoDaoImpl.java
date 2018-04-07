package org.pxxy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.pxxy.dao.InfoDao;
import org.pxxy.domain.Info;
import org.pxxy.utils.ConnectionMySQL;

public class InfoDaoImpl implements InfoDao {

	public static Connection connection = null;
	public static PreparedStatement preparedStmt = null;
	public static Statement stmt = null;
	public static ResultSet resultSet = null;

	@Override
	public List<Info> findAllInfo() {
		return null;
	}

	@Override
	public void addInfo(Info info) {
		try {

			connection = ConnectionMySQL.getCon();

			String sql = "insert into info values(null,'" + info.getAuthor() + "','" + info.getContent() + "','"
					+ info.getContentAbstract() + "','" + info.getContentTitle() + "','" + info.getPaiXu() + "','"
					+ info.getPicPath() + "','" + info.getPublishStatus() + "','" + info.getPublishTime() + "','"
					+ info.getTitle() + "','" + info.getCid() + "')";

			preparedStmt = connection.prepareStatement(sql);

			preparedStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delInfo(Info info) {
		try {
			connection = ConnectionMySQL.getCon();

			String sql = "delete from info where infoId='" + info.getInfoId() + "' ";
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Info findInfoByInfoId(Integer infoId) {

		Info info = null;

		try {

			connection = ConnectionMySQL.getCon();

			String sql = "select * from info where infoId='" + infoId + "'";

			preparedStmt = connection.prepareStatement(sql);

			resultSet = preparedStmt.executeQuery();

			if (resultSet.next()) {

				info = new Info();

				info.setInfoId(resultSet.getInt("infoId"));
				info.setAuthor(resultSet.getString("author"));
				info.setTitle(resultSet.getString("title"));
				info.setContentTitle(resultSet.getString("contentTitle"));
				info.setContentAbstract(resultSet.getString("contentAbstract"));
				info.setContent(resultSet.getString("content"));
				info.setPicPath(resultSet.getString("picPath"));
				info.setPublishTime(resultSet.getString("publishTime"));
				info.setPublishStatus(resultSet.getString("publishStatus"));
				info.setPaiXu(resultSet.getInt("paiXu"));
				info.setCid(resultSet.getInt("cid"));

				return info;

			} else {

				return null;

			}

		} catch (SQLException e) {

			e.printStackTrace();

			return null;

		}

	}

	@Override
	public void updateInfo(Info info) {

		try {

			connection = ConnectionMySQL.getCon();

			String sql = "update info set author='" + info.getAuthor() + "' , title='" + info.getTitle()
					+ "' , contentTitle='" + info.getContentTitle() + "' , contentAbstract='"
					+ info.getContentAbstract() + "' , content='" + info.getContent() + "' , publishStatus='"
					+ info.getPublishStatus() + "' , paiXu='" + info.getPaiXu() + "' , cid='" + info.getCid()
					+ "'  where infoId ='" + info.getInfoId() + "'";

			stmt = connection.createStatement();

			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Info> findAllInfo(Integer cid) {
		return null;
	}

	@Override
	public List<Info> findFyzxInfos() {
		return null;
	}

	@Override
	public List<Info> findXsjlInfos() {
		return null;
	}

	@Override
	public Info findXsjlInfo() {
		return null;
	}

	@Override
	public List<Info> findFyjjInfos() {
		return null;
	}

	@Override
	public List<Info> findFyjtInfos() {
		return null;
	}

	@Override
	public List<Info> findHzptInfos() {
		return null;
	}

	@Override
	public List<Info> findFycrInfos() {
		return null;
	}

	@Override
	public List<Info> findInfosByPage(Integer cid) {
		return null;
	}

	@Override
	public List<Info> findAllInfo(String keywords) {
		return null;
	}

	@Override
	public int getInfoCount(Integer cid) {
		return 0;
	}

	@Override
	public List<Info> findByCid(int currentPage, int pageSize, Integer cid) {
		return null;
	}

	@Override
	public int getInfoCount(String keywords) {

		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select count(*) count from info where title like '%" + keywords + "%' ";

			preparedStmt = connection.prepareStatement(sql);

			resultSet = preparedStmt.executeQuery();

			resultSet.next();

			return resultSet.getInt("count");

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Info> findByPage(int currentPage, int pageSize, String keywords) {
		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select *  from info where title like '%" + keywords + "%' order by publishTime desc limit "
					+ Integer.toString(pageSize) + " offset " + Integer.toString((currentPage - 1) * 10);

			preparedStmt = connection.prepareStatement(sql);

			resultSet = preparedStmt.executeQuery();

			List<Info> infoList = new ArrayList<Info>();

			Info info = null;

			while (resultSet.next()) {
				info = new Info();
				info.setInfoId(resultSet.getInt("infoId"));
				info.setAuthor(resultSet.getString("author"));
				info.setContent(resultSet.getString("content"));
				info.setContentAbstract(resultSet.getString("contentAbstract"));
				info.setContentTitle(resultSet.getString("contentTitle"));
				info.setPaiXu(resultSet.getInt("paiXu"));
				info.setPicPath(resultSet.getString("picPath"));
				info.setPublishStatus(resultSet.getString("publishStatus"));
				info.setPublishTime(resultSet.getString("publishTime"));
				info.setTitle(resultSet.getString("title"));
				info.setCid(resultSet.getInt("cid"));
				/*
				 * 
				 */
				infoList.add(info);
			}

			return infoList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
