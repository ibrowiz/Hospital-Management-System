package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.RelatedPatientView;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface RelatedPatientViewDao {
	
	public List<RelatedPatientView> fetchAll();

	public RelatedPatientView getRelPatViewById(int id);

	public List<RelatedPatientView> fetchAllByOrganisation(Integer organisationId,Integer PatientId);
		

}
