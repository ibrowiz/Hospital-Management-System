package org.calminfotech.system.dao.impl;

import java.util.List;



import org.calminfotech.system.daoInterface.HrUserDepartmentDao;
import org.calminfotech.system.daoInterface.HrUserUnitDao;
import org.calminfotech.system.models.GetUnitDetails;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserSection;
import org.calminfotech.system.models.HrUserUnit;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HrUserUnitDaoImpl implements HrUserUnitDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HrUserUnit> fetchAll() {
		List<HrUserUnit> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HrUserUnit").list();
		return list;
	}

	@Override
	public List<HrUserUnit> fetchAllByOrgainsation(
			Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HrUserUnit.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	@Override
	public List<HrUserUnit> fetchAllByloginsectn(LoginSection loginSection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HrUserUnit> getHrUserUnitByUserId(User user) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from HrUserUnit where user = ? ")
				.setParameter(0, user).list();
		return list;
	}

	@Override
	public void save(HrUserUnit hrUserUnit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(hrUserUnit);
	}

	@Override
	public HrUserUnit getHrUserUnitById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM HrUserUnit WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (HrUserUnit) list.get(0);
		return null;
	}

	@Override
	public void delete(HrUserUnit hrUserUnit) {
		this.sessionFactory.getCurrentSession().delete(hrUserUnit);
	}

	@Override
	public void update(HrUserUnit hrUserUnit) {
		this.sessionFactory.getCurrentSession().update(hrUserUnit);
	}

	@Override
	public List<GetUnitDetails> getHrUserUnitForDep(int deptId, int userId) {
		// TODO Auto-generated method stub


		 Session session = sessionFactory.openSession();
		  
		 
		 //  Query query = session.createSQLQuery("select unit_id,unit_name from dbo.hr_unit a inner join hr_user_unit b on a.id=b.unit_idwhere a.department_id= ? and b.user_id=? and a.status='Active' and b.status='Active'")
		 Query query = session.createSQLQuery("select unit_id, a.unit_name from dbo.hr_unit a inner join hr_user_unit b on a.id=b.unit_id where a.department_id= ? and b.user_id=? and a.status='Active' and b.status='Active'")           
		 .addEntity(GetUnitDetails.class)
		             .setParameter(0, deptId)
		             .setParameter(1, userId);
		   
		 
		    List<GetUnitDetails> list = query.list();
		    
		    return list;
		    }
	}

