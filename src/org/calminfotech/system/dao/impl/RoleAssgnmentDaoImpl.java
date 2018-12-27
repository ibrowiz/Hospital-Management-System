package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.RoleAssgnmentDao;
import org.calminfotech.system.models.RoleAssgnment;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleAssgnmentDaoImpl implements RoleAssgnmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public RoleAssgnment getRoleAssgnmentById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from RoleAssgnment where roleId = ?")
				.setParameter(0, id).list();
		if (list.size() > 0) {
			return (RoleAssgnment) list.get(0);
		}
		return null;
	}

	@Override
	public void save(RoleAssgnment roleAssignment) {
		this.sessionFactory.getCurrentSession().save(roleAssignment);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}

	@Override
	public List<RoleAssgnment> deleteAllCheckedValues(Integer roleId) {
		// call stored procedure using native createSQLQuery() method
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetDeleteCheckedVal")
					.setParameter("roleid", roleId);
			List result = query.list();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}

}
