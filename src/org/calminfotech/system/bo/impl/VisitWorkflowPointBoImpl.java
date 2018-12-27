package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.LoginSectionBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.daoInterface.VisitWorkflowPointDao;
import org.calminfotech.system.forms.VisitWorkflowPointForm;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitWorkflowPointBoImpl implements VisitWorkflowPointBo {

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private LoginSectionBo loginSectionBo;
	
	
	
	@Autowired
	private VisitWorkflowPointDao visitWorkflowPointDao;

	@Override
	public List<VisitWorkflowPoint> fetchAllByOrganisation(Integer organisationId) {
		// TODO Auto-generated method stub
		return this.visitWorkflowPointDao.fetchAllByOrganisation(this.userIdentity
				.getOrganisation());
	}

	@Override
	public VisitWorkflowPoint getWorkflowPointById(int id) {
		// TODO Auto-generated method stub
		return this.visitWorkflowPointDao.getWorkflowPointById(id);
	}

	@Override
	public VisitWorkflowPoint save(VisitWorkflowPointForm form) {
		// TODO Auto-generated method stub
		VisitWorkflowPoint point = new VisitWorkflowPoint();
		//Unit unit = this.unitBo.getUnitById(form.getUnitId());
		//point.setUnit(unit);
		point.setName(form.getPointName());
		LoginSection section = this.loginSectionBo.getLoginSectionById(form.getSection());
		point.setLoginSection(section);
		point.setOrganisation(this.userIdentity.getOrganisation());
		point.setCreatedBy(this.userIdentity.getUsername()); 
		point.setActive(true);
		this.visitWorkflowPointDao.save(point);

		return point;
	}

	@Override
	public void delete(VisitWorkflowPoint workflowPoint) {
		// TODO Auto-generated method stub
		this.visitWorkflowPointDao.delete(workflowPoint);
	}

	@Override
	public void update(VisitWorkflowPointForm form) {
		// TODO Auto-generated method stub
		VisitWorkflowPoint point = this.visitWorkflowPointDao
				.getWorkflowPointById(form.getId());
		point.setName(form.getPointName());

		this.visitWorkflowPointDao.update(point);
	}

	@Override
	public VisitWorkflowPoint getWorkflowStartPoint(Integer organisationId) {
		// TODO Auto-generated method stub
	
		return this.visitWorkflowPointDao.getWorkflowStartPoint(userIdentity.getOrganisation());
	}

	@Override
	public VisitWorkflowPoint getWorkflowEndPoint() {
		// TODO Auto-generated method stub
		return this.visitWorkflowPointDao.getWorkflowEndPoint();
	}

	@Override
	public VisitWorkflowPoint getPointByName(String string) {
		// TODO Auto-generated method stub
		return this.visitWorkflowPointDao.getPointByName(string);
	}

	@Override
	public List<VisitWorkflowPoint> fetchFontDeskPoint(int section) {
		// TODO Auto-generated method stub
		int frontDeskId = 8; 
		return this.visitWorkflowPointDao.fetchFontDeskPoint(frontDeskId, section);
	}

	@Override
	public List<VisitWorkflowPoint> getvWorkflowPointByUnitId(int unit_id) {
		
		return null;
	}

}
