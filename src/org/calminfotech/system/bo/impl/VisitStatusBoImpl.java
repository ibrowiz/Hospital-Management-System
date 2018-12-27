package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.daoInterface.VisitStatusDao;
import org.calminfotech.system.forms.VisitStatusForm;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitStatusBoImpl implements VisitStatusBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private VisitStatusDao visitStatusDao;

	@Override
	public List<VisitStatus> fetchAll() {
		// TODO Auto-generated method stub
		return this.visitStatusDao.fetchAll();
	}

	@Override
	public List<VisitStatus> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.visitStatusDao.fetchAll(userIdentity.getOrganisation());
	}

	@Override
	public VisitStatus getStatusById(int id) {
		// TODO Auto-generated method stub
		return this.visitStatusDao.getStatusById(id);
	}

	@Override
	public VisitStatus save(VisitStatusForm statusForm) {
		// TODO Auto-generated method stub
		VisitStatus status = new VisitStatus();
		status.setType(statusForm.getType());
		status.setOrganisation(this.userIdentity.getOrganisation());
		status.setCreatedBy(this.userIdentity.getUsername());

		this.visitStatusDao.save(status);
		return status;
	}

	@Override
	public void delete(VisitStatus status) {
		// TODO Auto-generated method stub
		this.visitStatusDao.delete(status);
	}

	@Override
	public void update(VisitStatusForm statusForm) {
		// TODO Auto-generated method stub
		VisitStatus status = this.visitStatusDao.getStatusById(statusForm
				.getId());
		status.setType(statusForm.getType());

		this.visitStatusDao.update(status);
	}

	@Override
	public void changeStartPoint(VisitStatusForm form) {
		// TODO Auto-generated method stub
		// Get and change the current start point
		VisitStatus currentStartPoint = this.getStartPointStatus();
		if (null != currentStartPoint) {
			currentStartPoint.setStartPoint(false);
			this.visitStatusDao.update(currentStartPoint);
		}
		// Get and change target start point
		VisitStatus targetStartPoint = this.getStatusById(form.getId());
		targetStartPoint.setStartPoint(true);
		this.visitStatusDao.update(targetStartPoint);

	}

	@Override
	public void changeEndPoint(VisitStatusForm form) {
		// TODO Auto-generated method stub
		// Get and change current end point
		VisitStatus currentEndPoint = this.getEndPointStatus();
		if (null != currentEndPoint) {
			currentEndPoint.setEndPoint(false);
			this.visitStatusDao.update(currentEndPoint);
		}

		// Get and change target end point
		VisitStatus targetEndPoint = this.getStatusById(form.getId());
		targetEndPoint.setEndPoint(true);
		this.visitStatusDao.update(targetEndPoint);

	}

	@Override
	public VisitStatus getStartPointStatus() {
		// TODO Auto-generated method stub
		return this.visitStatusDao.getStartPointStatus(this.userIdentity
				.getOrganisation());
	}

	@Override
	public VisitStatus getEndPointStatus() {
		// TODO Auto-generated method stub
		return this.visitStatusDao.getEndPointStatus(this.userIdentity
				.getOrganisation());
	}

	@Override
	public VisitStatus getNotEndPointStatus() {
		// TODO Auto-generated method stub
		return this.visitStatusDao.getNotEndPointStatus(this.userIdentity
				.getOrganisation());
	}

}
