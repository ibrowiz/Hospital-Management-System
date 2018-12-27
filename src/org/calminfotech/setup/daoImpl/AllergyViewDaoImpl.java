package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.setup.daoInterface.AllergyViewDao;
import org.calminfotech.setup.models.AllergyCategoryView;
import org.calminfotech.setup.models.AllergyView;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AllergyViewDaoImpl implements AllergyViewDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<AllergyView> fetchAll() {
		List<AllergyView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from AllergyView").list();
		return list;
	}

	@Override
	public AllergyView getAllergyViewById(int id) {
		List<AllergyView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from AllergyView where allergyId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<AllergyView> fetchAllByOrganisation(int organisationid) {
		/*List list = sessionFactory.getCurrentSession()
				.createQuery("from AllergyView where organisationId = ?")
				.setParameter(0,organisationid).list();
		
			return list;*/
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(AllergyView.class)
				//.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisationId", organisationid))
				.addOrder(Order.desc("createDate")).list();
		return list;
	}

}
