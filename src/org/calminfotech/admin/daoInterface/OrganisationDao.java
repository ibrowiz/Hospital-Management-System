package org.calminfotech.admin.daoInterface;

import java.util.List;

import org.calminfotech.utils.models.Organisation;

public interface OrganisationDao {

	public List<Organisation> fetchAll();

	public Organisation getOrganisationById(int id);

	public void save(Organisation organisation);

	public void delete(Organisation organisation);

	public void update(Organisation organisation);

}
