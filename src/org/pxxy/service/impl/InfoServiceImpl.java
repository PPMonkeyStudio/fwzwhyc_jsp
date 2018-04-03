package org.pxxy.service.impl;

import java.util.List;

import org.pxxy.dao.InfoDao;
import org.pxxy.domain.Info;
import org.pxxy.domain.PageBean;
import org.pxxy.service.InfoService;

public class InfoServiceImpl implements InfoService {

	private InfoDao infoDao;

	@Override
	public List<Info> findAllInfo() {
		return infoDao.findAllInfo();
	}

	@Override
	public void addInfo(Info info) {
		infoDao.addInfo(info);
	}

	@Override
	public void delInfo(Info info) {
		infoDao.delInfo(info);

	}

	@Override
	public Info findInfoByInfoId(Integer infoId) {
		return infoDao.findInfoByInfoId(infoId);
	}

	@Override
	public void updateInfo(Info info) {
		infoDao.updateInfo(info);

	}

	@Override
	public List<Info> findAllInfo(Integer cid) {
		return infoDao.findAllInfo(cid);
	}

	@Override
	public List<Info> findFyjjInfos() {
		return infoDao.findFyjjInfos();
	}

	@Override
	public List<Info> findFyzxInfos() {
		return infoDao.findFyzxInfos();
	}

	@Override
	public List<Info> findXsjlInfos() {
		return infoDao.findXsjlInfos();
	}

	@Override
	public List<Info> findHzptInfos() {
		return infoDao.findHzptInfos();
	}

	@Override
	public List<Info> findFycrInfos() {
		return infoDao.findFycrInfos();
	}

	@Override
	public List<Info> findFyjtInfos() {
		return infoDao.findFyjtInfos();
	}

	@Override
	public List<Info> findInfosByPage(Integer cid) {
		return infoDao.findInfosByPage(cid);
	}

	@Override
	public List<Info> findAllInfo(String keywords) {
		return infoDao.findAllInfo(keywords);
	}

	@Override
	public PageBean<Info> findInfosByCid(int currentPage, int pageSize, int cid) {
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
		int count = infoDao.getInfoCount(keywords); // 求当前类别信息数量
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);// 求总页数
		List<Info> list = infoDao.findByPage(currentPage, pageSize, keywords); // 求当前页的集合数据
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
	public Info findXsjlInfo() {
		return infoDao.findXsjlInfo();
	}

}
