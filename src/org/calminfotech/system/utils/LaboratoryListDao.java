package org.calminfotech.system.utils;

import java.util.List;

import org.calminfotech.lab.models.LabTest;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LaboratoryListDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<LabTest> fetchAll() {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LabTest.class);

		return criteria.list();
	}

	public LabTest getLaboratoryTestById(int id) {

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LabTest.class)
				.add(Restrictions.eq("id", id));

		List list = criteria.list();
		if (list.size() > 0)
			return (LabTest) list.get(0);
		return null;
	}
}
