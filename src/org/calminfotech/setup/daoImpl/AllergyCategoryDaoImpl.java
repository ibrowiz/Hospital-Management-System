package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.setup.daoInterface.AllergyCategoryDao;
import org.calminfotech.setup.models.AllergyCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllergyCategoryDaoImpl implements AllergyCategoryDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AllergyCategory> fetchAll() {
		// TODO Auto-generated method stub
		
		List<AllergyCategory> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from AllergyCategory").list();
		return list;
	}

	@Override
	public AllergyCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		List<AllergyCategory> list = sessionFactory.getCurrentSession()
                .createQuery("from AllergyCategory where allergyCategoryId = ? ")
                .setParameter(0, categoryId).list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	}

	@Override
	public void save(AllergyCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().save(category);
		
	}

	@Override
	public void update(AllergyCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(AllergyCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().delete(category);
	}

	@Override
	public List<AllergyCategory> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from AllergyCategory where organisationId = ? AND status = 'active'")
				.setParameter(0,organisationId).list();
		
			return list;
	}

	

	//@Override
	/*public List<OuterRecursive> fetchAllTypes() {
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
		}*/

	

}
