package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.system.models.Clocking;
import org.calminfotech.patient.models.Patient;

import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserLoginSession;
import org.calminfotech.utils.models.Organisation;

public interface ClockingDao {
	
	
	public List<Clocking> fetchAll();

	public List<Clocking> fetchAllByOrgainsation(
			Organisation organisation);

	/*public List<Clocking> fetchAllByloginsectn(Clocking clocking);*/
	
	public List<Clocking> fetchAllByUser(User user);

	public void save(Clocking clocking);

	public Clocking getClockingById(int id);

	public void delete(Clocking clocking);


	

}
