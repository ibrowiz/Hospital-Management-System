package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyView;

public interface AllergyViewBo {
	
	public List<AllergyView> fetchAll();

	public AllergyView getAllergyViewById(int id);
	
	public List<AllergyView> fetchAllByOrganisation(int organisationid);

}
