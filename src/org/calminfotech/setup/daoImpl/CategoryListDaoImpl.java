package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.setup.daoInterface.CategoryListDao;
import org.calminfotech.setup.models.AllergyCategoryList;
import org.calminfotech.setup.models.UnitList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryListDaoImpl implements CategoryListDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<AllergyCategoryList> fetchAll() {
		// TODO Auto-generated method stub
		
		List<AllergyCategoryList> list = sessionFactory.getCurrentSession()
				   .createQuery("from AllergyCategoryList").list();
		return list;
	}

	@Override
	public List<AllergyCategoryList> fetchAllByOrganisation(Integer organisationId) {
		List<AllergyCategoryList> list = sessionFactory.getCurrentSession()
				   .createQuery("from AllergyCategoryList where organisationId = ?").setParameter(0,organisationId).list();
		return list;
	}

}
