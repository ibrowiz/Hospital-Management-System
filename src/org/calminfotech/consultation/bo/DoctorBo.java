package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.forms.VisitDoctorForm;
import org.calminfotech.consultation.models.Doctor;
import org.calminfotech.lab.forms.LabTestForm;

public interface DoctorBo {
	
	public void save(Doctor doctor);
	
	public Doctor getDoctorById(Integer id);
	
	public void update(VisitDoctorForm visitDoctorForm);

	public List<Doctor> fetchAllByUIdAndOrg(int userId, Integer organisationId);

}
