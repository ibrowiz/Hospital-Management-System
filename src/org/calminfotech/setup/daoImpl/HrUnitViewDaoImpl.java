package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.setup.daoInterface.HrUnitViewDao;
import org.calminfotech.setup.models.HrUnitView;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class HrUnitViewDaoImpl implements HrUnitViewDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HrUnitView> fetchAll() {
		List<HrUnitView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HrUnitView").list();
		return list;
	}

	@Override
	public HrUnitView getUnitViewById(int id) {
		List<HrUnitView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HrUnitView where unitId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<HrUnitView> fetchAllByOrganisation(Integer organisationid) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from HrUnitView where organisationId = ?")
				.setParameter(0,organisationid).list();
		
			return list;
	}

}
