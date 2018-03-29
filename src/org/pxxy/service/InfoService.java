package org.pxxy.service;

import java.util.List;

import org.pxxy.domain.Info;
import org.pxxy.domain.PageBean;

public interface InfoService {

	List<Info> findAllInfo();
	List<Info> findAllInfo(String keywords);

	void addInfo(Info info);

	void delInfo(Info info);

	Info findInfoByInfoId(Integer integer);

	void updateInfo(Info info);

	List<Info> findAllInfo(Integer cid);

	List<Info> findFyjjInfos();

	List<Info> findFyzxInfos();

	List<Info> findXsjlInfos();

	List<Info> findHzptInfos();

	List<Info> findFycrInfos();

	List<Info> findFyjtInfos();

	List<Info> findInfosByPage(Integer cid);
	PageBean<Info> findInfosByCid(int currentPage, int pageSize, int cid);
	PageBean<Info> findInfosByPage(int currentPage, int pageSize,String keywords);
	Info findXsjlInfo();
}
