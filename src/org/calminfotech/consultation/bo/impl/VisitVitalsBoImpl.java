package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.VisitVitalsBo;
import org.calminfotech.consultation.dao.VisitVitalsDao;
import org.calminfotech.consultation.models.VisitVital;
import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitVitalsBoImpl implements VisitVitalsBo {

	@Autowired
	private VisitVitalsDao visitVitalsDao;

	@Autowired
	private VisitStatusBo visitStatusBo;

	@Autowired
	private VisitWorkflowPointBo pointBo;

	@Override
	public List<VisitVital> fetchAllWithOpenStatusForUser() {
		// TODO Auto-generated method stub

		return this.visitVitalsDao.fetchAllWithOpenStatusForUser(
				this.visitStatusBo.getEndPointStatus(),
				this.pointBo.getPointByName(VisitWorkflowPoint.VITALS));
	}

	


	
	

	
	
	
	

}
