package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.hr.forms.GetClockingUnitProcForm;
import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.system.forms.ClockingForm;

import org.calminfotech.system.models.Clocking;
import org.calminfotech.user.models.UserLoginSession;


public interface ClockingBo {

/*	public Clocking save(ClockingForm clockingform);
	
	public Clocking saveOrUpdate(ClockingForm clockingform, UserLoginSession userLoginSession);

	public Clocking getClockingById(int id);

	public void delete(Clocking clocking);
	
	
	public List<Clocking> fetchAllByOrganisation();

	public List<Clocking> fetchAll();*/
	
	
	public void save(Clocking clocking);

	public void delete(Clocking clocking);
	
	public void update(Clocking clocking);

	public Clocking getClockingById(int id);
	
	
	
}
