package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.RelatedPatient;
import org.calminfotech.setup.daoInterface.AllergyDao;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AllergyDaoImpl implements AllergyDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Allergy> fetchAll() {
		// TODO Auto-generated method stub
		List<Allergy> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Allergy").list();
		return list;
	}

	@Override
	public Allergy getAllergyById(int id) {
		// TODO Auto-generated method stub
		List<Allergy> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Allergy where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(Allergy allergy) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(allergy);
	}

	@Override
	public void delete(Allergy allergy) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(allergy);

	}

	@Override
	public void update(Allergy allergy) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(allergy);

	}

	/*@Override
	public List<Allergy> fetchAllByOrganisation(Organisation organisation) {
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(Allergy.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.addOrder(Order.asc("createDate")).list();
		return list;
	}*/
	
	@Override
	public List<Allergy> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from Allergy where organisationId = ?")
				.setParameter(0,organisationId).list();
		
			return list;
	}

	@Override
	public List<Allergy> fetchAllergyById(Integer id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Allergy where id = ?").setParameter(0, id)
				.list();
		return list;
	}

}
