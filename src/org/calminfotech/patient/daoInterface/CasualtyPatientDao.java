package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.CasPatient;
import org.calminfotech.utils.models.Organisation;

public interface CasualtyPatientDao {
	
	
	public List<CasPatient> fetchAll();

	public List<CasPatient> fetchAllByOrganisation(Organisation organisation);

	public CasPatient getCasPatientById(int id);

	public void save(CasPatient casPatient);

	public void delete(CasPatient casPatient);

	public void update(CasPatient casPatient);


}
