package org.calminfotech.lab.dao.impl;

import java.util.List;

import org.calminfotech.lab.dao.LabResultDao;
import org.calminfotech.lab.models.LabDeleteResult;
import org.calminfotech.lab.models.LabResult;
import org.calminfotech.lab.models.LabTest;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LabResultDaoImpl implements LabResultDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public LabResult getResultByTestId(LabTest labTest) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from LabResult where labTest = ? ")
				.setParameter(0, labTest).list();
	
		return (LabResult) list;
	}

	@Override
	public LabResult getResultById(int id) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LabResult.class).add(Restrictions.eq("id", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (LabResult) list.get(0);

		return null;
	}

	@Override
	public void save(LabResult laboratoryResult) {
		System.out.println("Saving");
		System.out.println(laboratoryResult.getActual_value());
		this.sessionFactory.getCurrentSession().save(laboratoryResult);
		System.out.println("Saved");
		//System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		//System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		//System.out.println("clear");
		/*sessionFactory.getCurrentSession().close();*/
	}

	@Override
	public List<LabResult> allLabResultById(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LabResult.class).add(Restrictions.eq("id", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (List<LabResult>) list.get(0);

		return null;
	}

	@Override
	public List<LabResult> allLabResultByTestId(LabTest labTest) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from LabResult where labTest = ? ")
				.setParameter(0, labTest).list();
	
		return (List<LabResult>) list;
	}

	@Override
	public void update(LabResult laboratoryResult) {
		this.sessionFactory.getCurrentSession().update(laboratoryResult);
		
	}

	@Override
	public void deleteByTestId(LabDeleteResult labDelResult) {
		this.sessionFactory.getCurrentSession().delete(labDelResult);
				/*.createQuery("delete from LaboratoryResult where labTest = ? ")
				.setParameter(0, testId);*/
	}

	
	
}
