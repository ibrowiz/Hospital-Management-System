package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.RelatedPatientViewDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.RelatedPatientView;
import org.calminfotech.setup.models.AllergyCategoryView;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class RelatedPatientViewDaoImpl implements RelatedPatientViewDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<RelatedPatientView> fetchAll() {
		List<RelatedPatientView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from RelatedPatientView").list();
		return list;
	}

	@Override
	public RelatedPatientView getRelPatViewById(int id) {
		List<RelatedPatientView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from RelatedPatientView where patientId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<RelatedPatientView> fetchAllByOrganisation(
			Integer organisationId,Integer PatientId) {
		List<RelatedPatientView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from RelatedPatientView where organisationId = ? and patientId = ?").setParameter(0, organisationId).setParameter(1, PatientId).list();
		return list;
	}
	
	

}
