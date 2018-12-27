package org.calminfotech.admin.boInterface;

import java.util.List;

import org.calminfotech.admin.forms.OrganisationEditForm;
import org.calminfotech.admin.forms.OrganisationForm;
import org.calminfotech.utils.models.Organisation;

public interface OrganisationBo {

	public List<Organisation> fetchAll();

	public Organisation getOrganisationById(int id);

	public Organisation save(OrganisationForm OrganisationForm);

	public void delete(Organisation organisation);

	public void update(OrganisationEditForm organisationForm);

}
