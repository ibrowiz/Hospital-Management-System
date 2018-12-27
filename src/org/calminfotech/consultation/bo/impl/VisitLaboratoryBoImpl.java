package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.VisitLaboratoryBo;
import org.calminfotech.consultation.dao.VisitLaboratoryDao;
import org.calminfotech.consultation.models.VisitLaboratory;
import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitLaboratoryBoImpl implements VisitLaboratoryBo {

	@Autowired
	private VisitLaboratoryDao visitLaboratoryDao;

	@Autowired
	private VisitStatusBo visitStatusBo;

	@Autowired
	private VisitWorkflowPointBo pointBo;

	@Override
	public List<VisitLaboratory> fetchAllWithOpenStatusForUser() {
		// TODO Auto-generated method stub

		return this.visitLaboratoryDao.fetchAllWithOpenStatusForUser(
				this.visitStatusBo.getEndPointStatus(),
				this.pointBo.getPointByName(VisitWorkflowPoint.LABORATORY));
	}

}
