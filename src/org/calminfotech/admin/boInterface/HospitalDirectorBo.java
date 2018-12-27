package org.calminfotech.admin.boInterface;

import java.util.List;

import org.calminfotech.admin.forms.HospitalDirectorForm;
import org.calminfotech.admin.models.HospitalDirector;
import org.calminfotech.utils.models.Organisation;

public interface HospitalDirectorBo {

	public List<HospitalDirector> fetchAll();
	
	public List<HospitalDirector> fetchAllDirectorByOrganisation(Organisation organisation);

	public HospitalDirector getHospitalDirectorById(int id);

	public void save(HospitalDirector director);

	public void delete(HospitalDirector director);

	public void update(HospitalDirectorForm directorForm);

}
