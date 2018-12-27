package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.setup.daoInterface.UnitCategoryDao;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.HrUnitCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnitCategoryDaoImpl implements UnitCategoryDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HrUnitCategory> fetchAll() {
		List<HrUnitCategory> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from HrUnitCategory").list();
		return list;
	}

	@Override
	public HrUnitCategory getCategoryById(Integer categoryId) {
		List<HrUnitCategory> list = sessionFactory.getCurrentSession()
                .createQuery("from HrUnitCategory where unitCategoryId = ? ")
                .setParameter(0, categoryId).list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	
	}

	@Override
	public void save(HrUnitCategory unitcategory) {
		this.sessionFactory.getCurrentSession().save(unitcategory);
	}

	@Override
	public void update(HrUnitCategory unitcategory) {
		this.sessionFactory.getCurrentSession().update(unitcategory);
	}

	@Override
	public void delete(HrUnitCategory unitcategory) {
		this.sessionFactory.getCurrentSession().delete(unitcategory);
		
	}

	@Override
	public List<HrUnitCategory> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from HrUnitCategory u where u.organisationId = ? AND u.status = 'active' order by u.unitCategoryId desc")
				.setParameter(0,organisationId).list();
		
			return list;
	}
	
	

}
