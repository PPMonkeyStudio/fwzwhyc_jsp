package org.pxxy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.pxxy.dao.InfoDao;
import org.pxxy.domain.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("infoDao")
public class InfoDaoImpl implements InfoDao {
	@Autowired //按类型注入
	private HibernateTemplate hibernateTemplate;
	@Override
	public List<Info> findAllInfo() {
		//return (List<Info>) hibernateTemplate.find("from Info order by publishTime desc,paiXu asc");
		//离线查询对象
				DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
				//criteria.add(Restrictions.eq("pflag", 0));  //追加条件  where username='tom'
				criteria.addOrder(Order.desc("publishTime"));
				return (List<Info>) hibernateTemplate.findByCriteria(criteria);		
	}

	@Override
	public void addInfo(Info info) {
		hibernateTemplate.save(info);

	}

	@Override
	public void delInfo(Info info) {
		hibernateTemplate.delete(info);

	}

	@Override
	public Info findInfoByInfoId(Integer infoId) {
		return hibernateTemplate.get(Info.class, infoId);
	}

	@Override
	public void updateInfo(Info info) {
		hibernateTemplate.update(info);

	}

	@Override
	public List<Info> findAllInfo(Integer cid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		criteria.add(Restrictions.eq("category.cid", cid));
		criteria.addOrder(Order.desc("publishTime"));
		criteria.addOrder(Order.asc("paiXu"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria);
	}


	@Override
	public List<Info> findFyzxInfos() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		criteria.add(Restrictions.eq("category.cid", 101));
		criteria.add(Restrictions.eq("publishStatus", "1"));
		criteria.addOrder(Order.desc("publishTime"));
		criteria.addOrder(Order.asc("paiXu"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria , 0, 4);
	}

	@Override
	public List<Info> findXsjlInfos() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		criteria.add(Restrictions.eq("category.cid", 102));
		criteria.add(Restrictions.eq("publishStatus", "1"));
		criteria.addOrder(Order.desc("publishTime"));
		criteria.addOrder(Order.asc("paiXu"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria , 0, 6);
	}
	
	@Override
	public Info findXsjlInfo() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		criteria.add(Restrictions.eq("category.cid", 102));
		criteria.add(Restrictions.eq("publishStatus", "1"));
		criteria.add(Restrictions.neOrIsNotNull("picPath", ""));
		criteria.addOrder(Order.desc("publishTime"));
		criteria.addOrder(Order.asc("paiXu"));
		return (Info) hibernateTemplate.findByCriteria(criteria , 0, 1);
	}
	
	@Override
	public List<Info> findFyjjInfos() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		criteria.add(Restrictions.eq("category.cid", 103));
		criteria.add(Restrictions.eq("publishStatus", "1"));
		criteria.addOrder(Order.desc("publishTime"));
		criteria.addOrder(Order.asc("paiXu"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria , 0, 8);
	}

	@Override
	public List<Info> findFyjtInfos() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		criteria.add(Restrictions.eq("category.cid", 104));
		criteria.add(Restrictions.eq("publishStatus", "1"));
		criteria.addOrder(Order.desc("publishTime"));
		criteria.addOrder(Order.asc("paiXu"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria , 0, 1);
	}
	
	@Override
	public List<Info> findHzptInfos() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		criteria.add(Restrictions.eq("category.cid", 105));
		criteria.add(Restrictions.eq("publishStatus", "1"));
		criteria.addOrder(Order.desc("publishTime"));
		criteria.addOrder(Order.asc("paiXu"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria , 0, 1);
	}

	@Override
	public List<Info> findFycrInfos() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		criteria.add(Restrictions.eq("category.cid", 106));
		criteria.add(Restrictions.eq("publishStatus", "1"));
		criteria.addOrder(Order.desc("publishTime"));
		criteria.addOrder(Order.asc("paiXu"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria , 0, 1);
	}

	@Override
	public List<Info> findInfosByPage(Integer cid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		criteria.add(Restrictions.eq("category.cid", cid));
		criteria.add(Restrictions.eq("publishStatus", "1"));
		criteria.addOrder(Order.desc("publishTime"));
		criteria.addOrder(Order.asc("paiXu"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria , 0, 12);
	}

	@Override
	public List<Info> findAllInfo(String keywords) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		if(keywords !=null && keywords.length()>0){
		criteria.add(Restrictions.like("title", "%"+keywords+"%"));  
		}
		criteria.addOrder(Order.desc("publishTime"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public int getInfoCount(Integer cid) {
		String sql = "select count(*) from Info where 1=1";
		List list1 = new ArrayList<>();
		if(cid != null && cid>0){
			sql+=" and cid=?";
			list1.add(cid);
		}		
		List<Long> list = (List<Long>) hibernateTemplate.find(sql, list1.toArray());		
		return list.get(0).intValue();
	}

	@Override
	public List<Info> findByCid(int currentPage, int pageSize, Integer cid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		if(cid!=null && cid>0){
			criteria.add(Restrictions.eq("category.cid", cid));
		}
		criteria.addOrder(Order.desc("publishTime"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria , (currentPage-1)*pageSize, pageSize);
	}

	@Override
	public int getInfoCount(String keywords) {
		String sql = "select count(*) from Info where 1=1";
		List list1 = new ArrayList<>();
		if(keywords !=null && keywords.length()>0){
			sql+=" and title like '%"+keywords+"%'";
		}
		List<Long> list = (List<Long>) hibernateTemplate.find(sql, list1.toArray());		
		return list.get(0).intValue();
	}

	@Override
	public List<Info> findByPage(int currentPage, int pageSize,String keywords) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Info.class);
		if(keywords !=null && keywords.length()>0){
			criteria.add(Restrictions.like("title", "%"+keywords+"%"));  
		}
		criteria.addOrder(Order.desc("publishTime"));
		return (List<Info>) hibernateTemplate.findByCriteria(criteria , (currentPage-1)*pageSize, pageSize);
	}
	
}
