package org.calminfotech.staffreg.bo.impl;

import java.util.Date;
import java.util.List;
import org.calminfotech.staffreg.boInterface.StaffRegBoInterface;
import org.calminfotech.staffreg.daoInterface.StaffRegDao;
import org.calminfotech.staffreg.forms.StaffRegForm;
import org.calminfotech.staffreg.models.StaffRegistration;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AutoGenStaffCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class StaffRegBoImpl implements StaffRegBoInterface {
	
	@Autowired
	private StaffRegDao staffRegDao;

	@Autowired
	UserIdentity userIdentity;
	
	@Override
	public List<StaffRegistration> fetchAllByOrganisation(int organisationId) {
		return this.staffRegDao.fetchAllByOrganisation(organisationId);
	}

	@Override
	public StaffRegistration save(StaffRegForm staffRegForm) {
		StaffRegistration staffRegistration = new StaffRegistration();
		staffRegistration.setStaffCode(new AutoGenStaffCode().genCode());
		staffRegistration.setFirstName(staffRegForm.getFirstName());
		staffRegistration.setLastName(staffRegForm.getLastName());
		staffRegistration.setEmail(staffRegForm.getEmail());
		staffRegistration.setDob(staffRegForm.getDob());
		staffRegistration.setAddress(staffRegForm.getAddress());
		staffRegistration.setQualifications(staffRegForm.getQualifications());
		staffRegistration.setUnit(staffRegForm.getUnit());
		staffRegistration.setCreatedBy(userIdentity.getUsername());
		staffRegistration.setCreateDate(new Date(System.currentTimeMillis()));
		staffRegistration.setDeleted(false);
		staffRegistration.setOrganisationId(userIdentity.getOrganisation().getId());
		staffRegistration.setStatus("active");
		System.out.println("dob" + staffRegForm.getDob());
		this.staffRegDao.save(staffRegistration);
		return staffRegistration;
		
	}

	@Override
	public void delete(StaffRegistration staffRegistration) {
		this.staffRegDao.delete(staffRegistration);
		
	}

	@Override
	public void update(StaffRegForm staffRegForm) {
		StaffRegistration staffRegistration = this.staffRegDao.getStaffById(staffRegForm.getId());
		staffRegistration.setFirstName(staffRegForm.getFirstName());
		staffRegistration.setLastName(staffRegForm.getLastName());
		staffRegistration.setEmail(staffRegForm.getEmail());
		staffRegistration.setDob(staffRegForm.getDob());
		staffRegistration.setAddress(staffRegForm.getAddress());
		staffRegistration.setQualifications(staffRegForm.getQualifications());
		staffRegistration.setUnit(staffRegForm.getUnit());
		staffRegistration.setModifiedBy(userIdentity.getUsername());
		staffRegistration.setModifiedDate(new Date(System.currentTimeMillis()));
		
		staffRegDao.update(staffRegistration);
	}

	@Override
	public StaffRegistration getStaffById(int id) {
		return this.staffRegDao.getStaffById(id);	}

	@Override
	public List<StaffRegistration> fetchStaffByUnitId(int unitId) {
		return this.staffRegDao.fetchStaffByUnitId(unitId);
	}

}
