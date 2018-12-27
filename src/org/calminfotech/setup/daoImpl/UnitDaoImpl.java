package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.setup.daoInterface.UnitCategoryDao;
import org.calminfotech.setup.daoInterface.UnitDao;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.HrUnit;
import org.calminfotech.setup.models.HrUnitCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnitDaoImpl implements UnitDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HrUnit> fetchAll() {
		List<HrUnit> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from HrUnit").list();
		return list;
	}

	@Override
	public HrUnit getUnitById(int unitId) {
		List<HrUnit> list = sessionFactory.getCurrentSession()
                .createQuery("from HrUnit where unitId = ? ")
                .setParameter(0, unitId).list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	
	}

	@Override
	public void save(HrUnit hrUnit) {
		this.sessionFactory.getCurrentSession().save(hrUnit);
	}

	@Override
	public void update(HrUnit hrUnit) {
		this.sessionFactory.getCurrentSession().update(hrUnit);
	}

	@Override
	public void delete(HrUnit hrUnit) {
		this.sessionFactory.getCurrentSession().delete(hrUnit);
		
	}

	@Override
	public List<HrUnit> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from HrUnit u where u.organisationId = ? AND u.status = 'active' order by u.unitId desc")
				.setParameter(0,organisationId).list();
			return list;
	}
	
	

}
