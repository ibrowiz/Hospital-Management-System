package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.BGlobalCategoryDao;
import org.calminfotech.system.models.BGlobalCategory;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.OuterRecursive;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BGlobalCategoryDaoImpl implements BGlobalCategoryDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BGlobalCategory> fetchAll() {
		// TODO Auto-generated method stub
		
		List<BGlobalCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from BGlobalCategory").list();
		return list;
	}

	@Override
	public BGlobalCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		List<BGlobalCategory> list = sessionFactory.getCurrentSession()
                .createQuery("from BGlobalCategory where categoryId = ? ")
                .setParameter(0, categoryId).list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	}

	@Override
	public void save(BGlobalCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().save(category);
		
	}

	@Override
	public void update(BGlobalCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(BGlobalCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().delete(category);
	}

	@Override
	public List<OuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		try{

			Query query = sessionFactory.getCurrentSession().createSQLQuery("exec dbo.outerrecursivenew")
					.addEntity(OuterRecursive.class);
			
			 List<OuterRecursive> list = query.list();
						 
					return  list;
					
			}catch (Exception e){
				
				e.printStackTrace();
			}
			 
			return null;
		}

}
