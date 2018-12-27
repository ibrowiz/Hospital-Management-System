package org.calminfotech.hmo.dao.impl;

import java.util.List;
import org.calminfotech.hmo.daoInterface.EhmoPackageDao;
import org.calminfotech.hmo.models.EhmoPackage;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EhmoPackageDaoImpl implements EhmoPackageDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<EhmoPackage> fetchAll() {
		List<EhmoPackage> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoPackage").list();
		return list;
	}

	/*public EhmoPackageV getEhmoPackageVById(int id) {
		// TODO Auto-generated method stub
		 
		Session session = sessionFactory.openSession();
		  
		 System.out.println("Im right here with the Client" +id);
		    
		    try{
		    	Query query = session.createSQLQuery("SELECT * From dbo.ehmos_packageV( :package_id)")
		    			.addEntity(EhmoPackageV.class)
		    			.setParameter("package_id", id);
		    	System.out.println("Before Query");
		    	 List <EhmoPackageV> stockObj  = query.list();
		  
		    	 System.out.println("After Query"); 
		    	 //return stockObj;
	
		}
		    catch(Exception e ){     
		        System.out.println("Print Error ");
		        e.printStackTrace();
		       }
		     return null;
		    }
	*/
	@Override
	public EhmoPackage getEhmoPackageById(int id) {
		List<EhmoPackage> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoPackage where packageId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
	@Override
	public void delete(EhmoPackage ehmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(ehmoPackage);

	}

	@Override
	public void save(EhmoPackage ehmoPackage) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().save(ehmoPackage);
	}

	@Override
	public void update(EhmoPackage ehmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(ehmoPackage);
}
	
	
}
