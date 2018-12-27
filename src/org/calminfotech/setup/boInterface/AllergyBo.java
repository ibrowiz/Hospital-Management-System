package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.setup.forms.AllergyForm;
import org.calminfotech.setup.models.Allergy;

public interface AllergyBo {

	public List<Allergy> fetchAll();

	public Allergy getAllergyById(int id);
	
	public List<Allergy> fetchAllergyById(Integer id);
	
	public List<Allergy> fetchAllByOrganisation(int organisationId);

	public Allergy save(AllergyForm allergyForm);

	public void delete(Allergy allergy);

	public void update(AllergyForm allergyForm);

}
