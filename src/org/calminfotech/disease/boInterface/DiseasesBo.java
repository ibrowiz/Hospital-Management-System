package org.calminfotech.disease.boInterface;

import java.util.List;

import org.calminfotech.disease.forms.DiseaseForm;
import org.calminfotech.disease.models.Diseases;

public interface DiseasesBo {
	
	public Diseases getDiseaseById(int id);
	
public List<Diseases> fetchDiseaseById(Integer id);
	
	public List<Diseases> fetchAllByOrganisation(int organisationId);

	public Diseases save(DiseaseForm diseasesForm);

	public void delete(Diseases diseasess);

	public void update(DiseaseForm diseasesForm);


}
