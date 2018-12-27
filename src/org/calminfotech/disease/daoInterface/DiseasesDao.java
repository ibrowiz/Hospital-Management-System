package org.calminfotech.disease.daoInterface;

import java.util.List;

import org.calminfotech.disease.models.Diseases;
import org.calminfotech.setup.models.Allergy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface DiseasesDao {
	
public Diseases getDiseaseById(int id);
	
	public List<Diseases> fetchDiseaseById(Integer id);
	
	public List<Diseases> fetchAllByOrganisation(int organisationId);
	

	public void save(Diseases diseases);

	public void delete(Diseases diseases);

	public void update(Diseases diseases);

}
