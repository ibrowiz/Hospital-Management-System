package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HmoPckServiceDao;
import org.calminfotech.system.models.HmoPckService;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoPckServiceDaoImpl implements HmoPckServiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<HmoPckService> fetchAll() {	
		
		Criteria criteria = sessionFactory.getCurrentSession()
							.createCriteria(HmoPckService.class);		
		return criteria.list();
	}

	@Override
	public void save(HmoPckService hmoPckService) {
		sessionFactory.getCurrentSession().save(hmoPckService);
	}

	@Override
	public void update(HmoPckService hmoPckService) {
		sessionFactory.getCurrentSession().update(hmoPckService);
	}

	@Override
	public void delete(HmoPckService hmoPckService) {
		sessionFactory.getCurrentSession().delete(hmoPckService);
	}

	@Override
	public HmoPckService getHmoPckServiceById(int serviceId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(HmoPckService.class)
				.add(Restrictions.eq("serviceId", serviceId));
		
		List list = criteria.list();
		if(list.size() > 0)
			return (HmoPckService) list.get(0);		
		return null;
	}

}
