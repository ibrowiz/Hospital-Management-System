package org.calminfotech.staffreg.daoImpl;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.staffreg.daoInterface.StaffRegDao;
import org.calminfotech.staffreg.models.StaffRegistration;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRegDaoImpl implements StaffRegDao  {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public StaffRegistration getStaffById(int id) {
		List<StaffRegistration> list = sessionFactory.getCurrentSession()
                .createQuery("from StaffRegistration where id = ? ")
                .setParameter(0, id).list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	}

	@Override
	public List<StaffRegistration> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from StaffRegistration where organisationId = ? AND status = 'active'")
				.setParameter(0,organisationId).list();
		
			return list;
	}

	@Override
	public void save(StaffRegistration staffRegistration) {
		this.sessionFactory.getCurrentSession().save(staffRegistration);
	}

	@Override
	public void delete(StaffRegistration staffRegistration) {
		this.sessionFactory.getCurrentSession().delete(staffRegistration);
	}

	@Override
	public void update(StaffRegistration staffRegistration) {
	this.sessionFactory.getCurrentSession().update(staffRegistration);
	}

	@Override
	public List<StaffRegistration> fetchStaffByUnitId(int unitId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from StaffRegistration where unit = ? AND status = 'active'")
				.setParameter(0,unitId).list();
		
			return list;
	}


	
}
