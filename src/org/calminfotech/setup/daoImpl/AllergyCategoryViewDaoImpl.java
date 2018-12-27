package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.setup.daoInterface.AllergyCategoryViewDao;
import org.calminfotech.setup.models.AllergyCategoryView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class AllergyCategoryViewDaoImpl implements AllergyCategoryViewDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AllergyCategoryView> fetchAll() {
		List<AllergyCategoryView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from AllergyCategoryView").list();
		return list;
	}

	@Override
	public AllergyCategoryView getAllergyCatViewById(int id) {
		List<AllergyCategoryView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from AllergyCategoryView where allergyCategoryId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<AllergyCategoryView> fetchAllByOrganisation(int organisationid) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from AllergyCategoryView where organisationId = ?")
				.setParameter(0,organisationid).list();
		
			return list;
	}

}
