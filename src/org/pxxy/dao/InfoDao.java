package org.pxxy.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.pxxy.domain.Info;
import org.pxxy.domain.PageBean;
import org.pxxy.utils.ConnectionMySQL;

public class InfoDao {

	static Connection connection = null;
	static PreparedStatement preparedStmt = null;
	static Statement stmt = null;
	static ResultSet resultSet = null;

	public List<Info> findAllInfo() {
		return null;
	}

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

	public void delInfo(Info info) {

		// 通过获取id获取到要删除的info
		info = findInfoByInfoId(info.getInfoId());

		/*
		 * 1.删除图片
		 */
		(new File("C://infopub/" + info.getPicPath())).delete();

		/*
		 * 2.删除记录
		 */
		try {
			connection = ConnectionMySQL.getCon();

			String sql = "delete from info where infoId='" + info.getInfoId() + "' ";
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

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

	public List<Info> findAllInfo(Integer cid) {
		return null;
	}

	public List<Info> findFyzxInfos() {

		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select *  from info where cid = '101' order by publishTime desc ";

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

	public List<Info> findXsjlInfos() {
		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select *  from info where cid = '102' order by publishTime desc ";

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

	public Info findXsjlInfo() {
		return null;
	}

	public List<Info> findFyjjInfos() {
		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select *  from info where cid = '103' order by publishTime desc ";

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

	public List<Info> findFyjtInfos() {

		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select *  from info where cid = '104' order by publishTime desc ";

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

	public List<Info> findHzptInfos() {
		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select *  from info where cid = '105' order by publishTime desc ";

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

	public List<Info> findFycrInfos() {
		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select *  from info where cid = '106' order by publishTime desc ";

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

	public List<Info> findInfosByPage(Integer cid) {
		return null;
	}

	public List<Info> findAllInfo(String keywords) {
		return null;
	}

	public int getInfoCount(Integer cid) {

		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select count(*) count from info where cid = '" + cid + "' ";

			preparedStmt = connection.prepareStatement(sql);

			resultSet = preparedStmt.executeQuery();

			resultSet.next();

			return resultSet.getInt("count");

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public PageBean<Info> findInfosByCid(int currentPage, int pageSize, int cid) {
		/*
		 * 求当前类别信息数量
		 */
		int count = getInfoCount(cid); //
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);// 求总页数
		/*
		 * 求当前页的集合数据
		 */
		List<Info> list = findByCid(currentPage, pageSize, cid);
		/*
		 * 将类别放进去
		 */
		CategoryDao categoryDao = new CategoryDao();
		for (Info thisInfo : list) {
			thisInfo.setCategory(categoryDao.findCategoryByCid(thisInfo.getCid()));
		}
		/*
		 * 
		 */
		PageBean<Info> pb = new PageBean<>();
		pb.setCount(count);
		if (currentPage == 0)
			currentPage = 1;
		pb.setCurrentPage(currentPage);
		pb.setList(list);
		pb.setPageSize(pageSize);
		pb.setTotalPage(totalPage);
		return pb;
	}

	public List<Info> findByCid(int currentPage, int pageSize, Integer cid) {

		try {
			connection = ConnectionMySQL.getCon();

			String sql = "select *  from info where cid = '" + cid + "' order by publishTime desc limit "
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

	public PageBean<Info> findInfosByPage(int currentPage, int pageSize, String keywords) {
		/*
		 * 求当前类别信息数量
		 */
		int count = getInfoCount(keywords);
		/*
		 * 求总页数
		 */
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);
		/*
		 * 求当前页的集合数据
		 */
		List<Info> list = findByPage(currentPage, pageSize, keywords);

		/*
		 * 将类别放进去
		 */
		CategoryDao categoryDao = new CategoryDao();
		for (Info thisInfo : list) {
			thisInfo.setCategory(categoryDao.findCategoryByCid(thisInfo.getCid()));
		}
		/*
		 * 装进PageBean
		 */
		PageBean<Info> pb = new PageBean<Info>();
		pb.setCount(count);
		pb.setCurrentPage(currentPage);
		pb.setList(list);
		pb.setPageSize(pageSize);
		pb.setTotalPage(totalPage);
		return pb;
	}

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
