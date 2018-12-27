package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.billing.models.BillUnitOfMeasure;
import org.calminfotech.system.daoInterface.GlobalItemUnitOfMeasureDao;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitOfMeasure;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class GlobalItemUnitOfMeasureDaoImpl implements GlobalItemUnitOfMeasureDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GlobalItemUnitOfMeasure> fetchAll() {
		List<GlobalItemUnitOfMeasure> list = this.sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemUnitOfMeasure").list();
		return list;
	}

	@Override
	public GlobalItemUnitOfMeasure getGlobalItemMeasureById(int id) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(GlobalItemUnitOfMeasure.class)
				.add(Restrictions.eq("globalItemUnitOfMeasureId", id));
		List list = criteria.list();
		if(list.size()>0){
			return (GlobalItemUnitOfMeasure)list.get(0);
		}
		return null;
	}

	@Override
	public void save(GlobalItemUnitOfMeasure measure) {
		this.sessionFactory.getCurrentSession().save(measure);
	}

	@Override
	public void delete(GlobalItemUnitOfMeasure measure) {
		this.sessionFactory.getCurrentSession().delete(measure);
	}

	@Override
	public void update(GlobalItemUnitOfMeasure measure) {
		this.sessionFactory.getCurrentSession().update(measure);
	}
	
	
	public GlobalItem fetchUnitOfMesureViaGlobalItem(int id) {
		List<GlobalItem> list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalItem where id = ? ").list();
		if(list.size()>0)
			return (GlobalItem) list.get(0);
		//list.get(0).getGlobalItem();
		return null;
		//return list;
	}

	

	@Override
	public List<BillUnitOfMeasure> listGlobalItemViaMeasure(Integer id) {
		List<BillUnitOfMeasure> list = sessionFactory.getCurrentSession()
				.createQuery("from BillUnitOfMeasure where unitOfMeasureId = ? ").setParameter(0, id).list();
		return list;
	}

	@Override
	public BillUnitOfMeasure fetchGlobalItemViaMeasure(Integer id) {
		List<BillUnitOfMeasure> list = sessionFactory.getCurrentSession()
				.createQuery("from BillUnitOfMeasure where unitOfMeasureId = ? ").setParameter(0, id).list();
		if(list.size()>0)
			return (BillUnitOfMeasure) list.get(0);
		//list.get(0).getGlobalItem();
		return null;
	}	

}
