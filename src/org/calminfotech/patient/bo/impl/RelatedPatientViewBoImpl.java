package org.calminfotech.patient.bo.impl;

import java.util.List;

import org.calminfotech.patient.boInterface.RelatedPatientViewBo;
import org.calminfotech.patient.daoInterface.RelatedPatientViewDao;
import org.calminfotech.patient.models.RelatedPatientView;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class RelatedPatientViewBoImpl implements RelatedPatientViewBo {
	
	@Autowired
	private RelatedPatientViewDao relatedPatientViewDao;
	
	@Autowired
	UserIdentity userIdentity;

	@Override
	public List<RelatedPatientView> fetchAll() {
		// TODO Auto-generated method stub
		return this.relatedPatientViewDao.fetchAll();
	}

	@Override
	public RelatedPatientView getRelPatViewById(int id) {
		// TODO Auto-generated method stub
		return this.relatedPatientViewDao.getRelPatViewById(id);
	}

	@Override
	public List<RelatedPatientView> fetchAllByOrganisation(
			Integer organisationId,Integer PatientId) {
		return this.relatedPatientViewDao.fetchAllByOrganisation(organisationId,PatientId);
	}

}
