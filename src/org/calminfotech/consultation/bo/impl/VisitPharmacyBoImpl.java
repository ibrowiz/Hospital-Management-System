package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.VisitPharmacyBo;
import org.calminfotech.consultation.dao.VisitPharmacyDao;
import org.calminfotech.consultation.models.VisitPharmacy;
import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitPharmacyBoImpl implements VisitPharmacyBo {

	@Autowired
	private VisitPharmacyDao visitPharmacyDao;

	@Autowired
	private VisitStatusBo visitStatusBo;

	@Autowired
	private VisitWorkflowPointBo pointBo;

	@Override
	public List<VisitPharmacy> fetchAllWithOpenStatusForUser() {
		// TODO Auto-generated method stub

		return this.visitPharmacyDao.fetchAllWithOpenStatusForUser(
				this.visitStatusBo.getEndPointStatus(),
				this.pointBo.getPointByName(VisitWorkflowPoint.PHARMACY));
	}

}
