package org.calminfotech.hmo.dao.impl;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.calminfotech.admin.forms.DataTableForm;
import org.calminfotech.hmo.daoInterface.EhmoDao;
import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.view.helpers.Button;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EhmoDaoImpl implements EhmoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override	
	public List<Ehmo> fetchAll() {
		//System.out.println("This is HMO DAO for fetch All method");
		List<Ehmo> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Ehmo ORDER BY hmoId DESC").list();
	/*	.createQuery("FROM Ehmo where status = 'active' ORDER BY createdDate DESC").list();*/

		return list;
	}

	@Override
	public Ehmo getEhmoById(int id) {
		// TODO Auto-generated method stub
		List<Ehmo> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Ehmo where hmoId = ? ORDER BY hmoId DESC").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	@Override
	public void save(Ehmo ehmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(ehmo);
	}
	@Override
	public List<Ehmo> fetchAllByOrganisation(int organisationId) {
		//System.out.println("name");
		List<Ehmo> list = sessionFactory.getCurrentSession()
				.createQuery("from Ehmo where organisation_id = ? ORDER BY hmo_id DESC")
		     .setParameter(0,organisationId)
			.list();
		//System.out.println(list.get(0).getHmoId());
			return list;
	}
	
	@Override
	public List<Ehmo> fetchAllByOrganisationEdit(int organisationId) {
		//System.out.println("name");
		List<Ehmo> list = sessionFactory.getCurrentSession()
				.createQuery("from Ehmo where organisation_id = ? AND status = 'active' ORDER BY modified_date ASC")
				.setParameter(0,organisationId).list();
		
			return list;
	}

	@Override
	public void delete(Ehmo ehmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(ehmo);
	}

	@Override
	public void update(Ehmo ehmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(ehmo);
	}
	
	
}


