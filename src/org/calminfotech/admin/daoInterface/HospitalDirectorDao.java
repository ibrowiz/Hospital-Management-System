package org.calminfotech.admin.daoInterface;

import java.util.List;

import org.calminfotech.admin.models.HospitalDirector;
import org.calminfotech.utils.models.Organisation;

public interface HospitalDirectorDao {

	public List<HospitalDirector> fetchAll();
	
	public List<HospitalDirector> fetchAllDirectorByOrganisation(Organisation organisation);
	
	public HospitalDirector getHospitalDirectorById(int directorId);

	public void save(HospitalDirector director);

	public void delete(HospitalDirector director);

	public void update(HospitalDirector director);	

}
