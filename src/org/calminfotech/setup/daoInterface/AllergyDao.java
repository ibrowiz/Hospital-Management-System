package org.calminfotech.setup.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.utils.models.Organisation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface AllergyDao {

	public List<Allergy> fetchAll();

	public Allergy getAllergyById(int id);
	
	public List<Allergy> fetchAllergyById(Integer id);
	
	public List<Allergy> fetchAllByOrganisation(int organisationId);

	public void save(Allergy allergy);

	public void delete(Allergy allergy);

	public void update(Allergy allergy);

}
