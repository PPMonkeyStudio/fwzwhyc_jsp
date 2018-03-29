package org.pxxy.dao;

import java.util.List;

import org.pxxy.domain.Info;

public interface InfoDao {

	List<Info> findAllInfo();

	void addInfo(Info info);

	void delInfo(Info info);

	Info findInfoByInfoId(Integer infoId);

	void updateInfo(Info info);

	List<Info> findAllInfo(Integer cid);

	List<Info> findFyjjInfos();

	List<Info> findFyzxInfos();

	List<Info> findXsjlInfos();

	List<Info> findHzptInfos();

	List<Info> findFycrInfos();

	List<Info> findFyjtInfos();

	List<Info> findInfosByPage(Integer cid);

	List<Info> findAllInfo(String keywords);

	int getInfoCount(Integer cid);

	List<Info> findByCid(int currentPage, int pageSize, Integer cid);

	int getInfoCount(String keywords);

	List<Info> findByPage(int currentPage, int pageSize,String keywords);

	Info findXsjlInfo();
}
