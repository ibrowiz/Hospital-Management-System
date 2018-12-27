package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.forms.VisitDoctorForm;
import org.calminfotech.consultation.models.Doctor;
import org.calminfotech.consultation.models.Visit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface DoctorDao {
	
	public void save(Doctor doctor);
	
	public Doctor getDoctorById(Integer id);
	
	public void update(Doctor doctor);
	
	public List<Doctor> fetchAllByUIdAndOrg(int userId, Integer organisationId);

}
