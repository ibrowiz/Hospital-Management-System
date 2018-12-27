package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HmoPckSubServiceDao;
import org.calminfotech.system.models.HmoPckSubService;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoPckSubServiceDaoImpl implements HmoPckSubServiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<HmoPckSubService> fetchAll() {	
		
		Criteria criteria = sessionFactory.getCurrentSession()
							.createCriteria(HmoPckSubService.class);		
		return criteria.list();
	}

	@Override
	public void save(HmoPckSubService hmoPckSubService) {
		sessionFactory.getCurrentSession().save(hmoPckSubService);
	}

	@Override
	public void update(HmoPckSubService hmoPckSubService) {
		sessionFactory.getCurrentSession().update(hmoPckSubService);
	}

	@Override
	public void delete(HmoPckSubService hmoPckSubService) {
		sessionFactory.getCurrentSession().delete(hmoPckSubService);
	}

	@Override
	public HmoPckSubService getHmoPckSubServiceById(int subserviceId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(HmoPckSubService.class)
				.add(Restrictions.eq("id", subserviceId));
		
		List list = criteria.list();
		if(list.size() > 0)
			return (HmoPckSubService) list.get(0);		
		return null;
	}

}
