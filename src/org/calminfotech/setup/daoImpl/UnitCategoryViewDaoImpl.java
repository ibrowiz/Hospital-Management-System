package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.setup.daoInterface.UnitCategoryDao;
import org.calminfotech.setup.daoInterface.UnitCategoryViewDao;
import org.calminfotech.setup.models.AllergyCategoryView;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.setup.models.UnitCategoryView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnitCategoryViewDaoImpl implements UnitCategoryViewDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UnitCategoryView> fetchAll() {
		List<UnitCategoryView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from UnitCategoryView").list();
		return list;
		
	}

	@Override
	public UnitCategoryView getUnitCategoryViewById(int id) {
		List<UnitCategoryView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from UnitCategoryView where unitCategoryId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<UnitCategoryView> fetchAllByOrganisation(int organisationid) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from UnitCategoryView where organisationId = ?")
				.setParameter(0,organisationid).list();
		
			return list;
	}
	
	

}
