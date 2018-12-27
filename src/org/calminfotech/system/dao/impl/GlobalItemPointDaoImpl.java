package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.GlobalItemPointDao;
import org.calminfotech.system.models.GlobalItemPoint;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GlobalItemPointDaoImpl implements GlobalItemPointDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<GlobalItemPoint> fetchAll() {
		// TODO Auto-generated method stub
		List<GlobalItemPoint> list = this.sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemPoint").list();
		return list;
	}

	@Override
	public GlobalItemPoint getGlobalItemPointById(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(GlobalItemPoint.class)
				.add(Restrictions.eq("id", id));
		List list = criteria.list();
		if(list.size()>0){
			return (GlobalItemPoint)list.get(0);
		}
		return null;
	}

	@Override
	public void save(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(point);
	}

	@Override
	public void delete(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(point);
	}

	@Override
	public void update(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(point);
	}

	@Override
	public GlobalItemPoint getByPointAndItem(Integer itemId,
			VisitWorkflowPoint point) {
		// TODO Auto-generated method stub
		List list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemPoint where itemId = ? and point = ? ")
				.setParameter(0, itemId)
				.setParameter(1, point).list();
		if(list.size()>0)
			return (GlobalItemPoint) list.get(0);
		return null;
	}

	/*@Override
	public List<GlobalItemPoint> fetchByPoint(Integer pointId) {
		List<GlobalItemPoint> list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemPoint where point = ? ")
				.setParameter(0, point).list();
		return list;
	}*/

	/*@Override
	public List<GlobalItemPoint> fetchByFrontDesk() {
		List<GlobalItemPoint> list = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from globalitem_point where globalitem_point = 1 ").list();
		return list;
	}*/
	
	/*@Override
	public GlobalItemPoint fetchGobalViaGLobalItemPoint() {
		List<GlobalItemPoint> list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemPoint where point.id = 1 ").list();
		if(list.size()>0)
			return (GlobalItemPoint) list.get(0);
		list.get(0).g.getGlobalItem();
		return null;
		//return list;
	}*/
	@Override
	public VisitWorkflowPoint fetchGlobalItemViaPoint() {
		List<VisitWorkflowPoint> list = sessionFactory.getCurrentSession()
				.createQuery("from VisitWorkflowPoint where id = 1 ").list();
		if(list.size()>0)
			return (VisitWorkflowPoint) list.get(0);
		//list.get(0).getGlobalItem();
		return null;
		//return list;
	}
	
}
