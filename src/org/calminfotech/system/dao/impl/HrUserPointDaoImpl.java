package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HrUserDepartmentDao;
import org.calminfotech.system.daoInterface.HrUserPointDao;
import org.calminfotech.system.models.GetPointDetails;
import org.calminfotech.system.models.GetUnitDetails;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserPoint;
import org.calminfotech.system.models.HrUserSection;
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
public class HrUserPointDaoImpl implements HrUserPointDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HrUserPoint> fetchAll() {
		List<HrUserPoint> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HrUserPoint").list();
		return list;
	}

	@Override
	public List<HrUserPoint> fetchAllByOrgainsation(
			Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HrUserPoint.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	@Override
	public List<HrUserPoint> fetchAllByloginsectn(LoginSection loginSection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HrUserPoint> getHrUserPointByUserId(User user) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from HrUserPoint where user = ? ")
				.setParameter(0, user).list();
		return list;
	}

	@Override
	public void save(HrUserPoint hrUserPoint) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(hrUserPoint);
	}

	@Override
	public HrUserPoint getHrUserPointById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM HrUserPoint WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (HrUserPoint) list.get(0);
		return null;
	}

	@Override
	public void delete(HrUserPoint hrUserPoint) {
		this.sessionFactory.getCurrentSession().delete(hrUserPoint);
	}

	@Override
	public void update(HrUserPoint hrUserPoint) {
		this.sessionFactory.getCurrentSession().update(hrUserPoint);
	}

	@Override
	public List<GetPointDetails> getHrUserPointForUnit(int unitId, int userId) {
		Session session = sessionFactory.openSession();
		  
		 
		 //  Query query = session.createSQLQuery("select unit_id,unit_name from dbo.hr_unit a inner join hr_user_unit b on a.id=b.unit_idwhere a.department_id= ? and b.user_id=? and a.status='Active' and b.status='Active'")
		 Query query = session.createSQLQuery("select point_id, a.point_name from dbo.hr_point a inner join hr_user_point b on a.id=b.pt_id where a.unit_id= ? and b.user_id=? and a.status='Active' and b.status='Active'")           
		 .addEntity(GetPointDetails.class)
		             .setParameter(0, unitId)
		             .setParameter(1, userId);
		   
		 
		    List<GetPointDetails> list = query.list();
		    
		    return list;
	}

}
