package org.calminfotech.setup.daoInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategoryView;
import org.calminfotech.setup.models.AllergyView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface AllergyViewDao {
	
	public List<AllergyView> fetchAll();

	public AllergyView getAllergyViewById(int id);
	
	public List<AllergyView> fetchAllByOrganisation(int organisationid);

}
