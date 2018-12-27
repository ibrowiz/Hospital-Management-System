package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.OrganisationDrug;
import org.calminfotech.utils.models.Organisation;

public interface OrganisationDrugDao {

	public List<OrganisationDrug> fetchAllByOrganisation(
			Organisation organisation);

	public List<OrganisationDrug> fetchAllByDrug(Drug drug);

	public void save(OrganisationDrug organisationDrug);

	public void delete(OrganisationDrug organisationDrug);

	public void update(OrganisationDrug organisationDrug);

	public OrganisationDrug getOrganisationDrugByDrugAndOrganisation(Drug drug,
			Organisation organisation);

}
