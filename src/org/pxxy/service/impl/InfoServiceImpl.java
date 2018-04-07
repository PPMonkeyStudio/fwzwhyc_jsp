package org.pxxy.service.impl;

import java.io.File;
import java.util.List;

import org.pxxy.dao.CategoryDao;
import org.pxxy.dao.InfoDao;
import org.pxxy.dao.impl.CategoryDaoImpl;
import org.pxxy.dao.impl.InfoDaoImpl;
import org.pxxy.domain.Info;
import org.pxxy.domain.PageBean;
import org.pxxy.service.InfoService;

public class InfoServiceImpl implements InfoService {

	private InfoDao infoDao;
	private CategoryDao categoryDao;

	@Override
	public List<Info> findAllInfo() {
		infoDao = new InfoDaoImpl();
		return infoDao.findAllInfo();
	}

	@Override
	public void addInfo(Info info) {
		infoDao = new InfoDaoImpl();
		infoDao.addInfo(info);
	}

	@Override
	public void delInfo(Info info) {

		infoDao = new InfoDaoImpl();

		info = infoDao.findInfoByInfoId(info.getInfoId());

		/*
		 * 1.删除图片
		 */
		(new File("C://infopub/" + info.getPicPath())).delete();
		/*
		 * 2.删除记录
		 */
		infoDao.delInfo(info);

	}

	@Override
	public Info findInfoByInfoId(Integer infoId) {
		infoDao = new InfoDaoImpl();
		return infoDao.findInfoByInfoId(infoId);
	}

	@Override
	public void updateInfo(Info info) {
		infoDao = new InfoDaoImpl();

		infoDao.updateInfo(info);

	}

	@Override
	public List<Info> findAllInfo(Integer cid) {
		infoDao = new InfoDaoImpl();
		return infoDao.findAllInfo(cid);
	}

	@Override
	public List<Info> findFyjjInfos() {
		infoDao = new InfoDaoImpl();
		return infoDao.findFyjjInfos();
	}

	@Override
	public List<Info> findFyzxInfos() {
		infoDao = new InfoDaoImpl();
		return infoDao.findFyzxInfos();
	}

	@Override
	public List<Info> findXsjlInfos() {
		infoDao = new InfoDaoImpl();
		return infoDao.findXsjlInfos();
	}

	@Override
	public List<Info> findHzptInfos() {
		infoDao = new InfoDaoImpl();
		return infoDao.findHzptInfos();
	}

	@Override
	public List<Info> findFycrInfos() {
		infoDao = new InfoDaoImpl();
		return infoDao.findFycrInfos();
	}

	@Override
	public List<Info> findFyjtInfos() {
		infoDao = new InfoDaoImpl();
		return infoDao.findFyjtInfos();
	}

	@Override
	public List<Info> findInfosByPage(Integer cid) {
		infoDao = new InfoDaoImpl();
		return infoDao.findInfosByPage(cid);
	}

	@Override
	public List<Info> findAllInfo(String keywords) {
		infoDao = new InfoDaoImpl();
		return infoDao.findAllInfo(keywords);
	}

	@Override
	public PageBean<Info> findInfosByCid(int currentPage, int pageSize, int cid) {
		infoDao = new InfoDaoImpl();
		int count = infoDao.getInfoCount(cid); // 求当前类别信息数量
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);// 求总页数
		List<Info> list = infoDao.findByCid(currentPage, pageSize, cid); // 求当前页的集合数据
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

	@Override
	public PageBean<Info> findInfosByPage(int currentPage, int pageSize, String keywords) {
		infoDao = new InfoDaoImpl();
		/*
		 * 求当前类别信息数量
		 */
		int count = infoDao.getInfoCount(keywords);
		/*
		 * 求总页数
		 */
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);
		/*
		 * 求当前页的集合数据
		 */
		List<Info> list = infoDao.findByPage(currentPage, pageSize, keywords);

		/*
		 * 将类别放进去
		 */
		categoryDao = new CategoryDaoImpl();
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

	@Override
	public Info findXsjlInfo() {
		infoDao = new InfoDaoImpl();
		return infoDao.findXsjlInfo();
	}

}
