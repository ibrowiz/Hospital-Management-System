package org.calminfotech.consultation.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.consultation.bo.DoctorBo;
import org.calminfotech.consultation.dao.DoctorDao;
import org.calminfotech.consultation.forms.VisitDoctorForm;
import org.calminfotech.consultation.models.Doctor;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorBoImpl implements DoctorBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private DoctorDao doctorDao;

	@Override
	public void save(Doctor doctor) {	
		this.doctorDao.save(doctor);
	}

	@Override
	public List<Doctor> fetchAllByUIdAndOrg(int userId, Integer organisationId) {
		// TODO Auto-generated method stub
		return this.doctorDao.fetchAllByUIdAndOrg(userId, organisationId);
	}

	@Override
	public Doctor getDoctorById(Integer id) {
		// TODO Auto-generated method stub
		return this.doctorDao.getDoctorById(id);
	}

	@Override
	public void update(VisitDoctorForm visitDoctorForm) {
		//LabTestCategory labtestcat =  this.labTestCategoryDao.getLabtestCatById(labTestForm.getCatId());
		//LaboratoryTest labTest = new LaboratoryTest();
		Doctor doctor = this.doctorDao.getDoctorById(visitDoctorForm.getSummaryId());
		
		
		//labTest.setId(labTestForm.getId());
		doctor.setComment(doctor.getComment());
		doctor.setModifiedBy(userIdentity.getUserId());
		doctor.setModifiedDate(new GregorianCalendar().getTime());
		doctorDao.update(doctor);
		
	}

}
