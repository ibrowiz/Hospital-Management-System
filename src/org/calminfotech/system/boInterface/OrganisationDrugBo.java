package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.OrganisationDrugForm;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.OrganisationDrug;
import org.calminfotech.utils.models.Organisation;

public interface OrganisationDrugBo {

	public List<OrganisationDrug> fetchAllByOrganisation();

	public List<OrganisationDrug> fetchAllByOrganisation(
			Organisation organisation);

	public List<OrganisationDrug> fetchAllByDrug(Drug drug);

	public OrganisationDrug save(OrganisationDrugForm organisationDrugForm);

	public void delete(OrganisationDrug organisationDrug);

	public void update(OrganisationDrugForm organisationDrugForm);

	public OrganisationDrug getOrganisationDrugByDrugAndOrganisation(Drug drug);
}
