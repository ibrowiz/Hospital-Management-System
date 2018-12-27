package org.calminfotech.hmo.daoInterface;

import java.util.List;
import org.calminfotech.hmo.models.Ehmo;

public interface EhmoDao {

	public List<Ehmo> fetchAll();
	public List<Ehmo> fetchAllByOrganisation(int organisationId);
	public List<Ehmo> fetchAllByOrganisationEdit(int organisationId);
	public Ehmo getEhmoById(int id);

	public void save(Ehmo ehmo);

	public void delete(Ehmo ehmo);

	public void update(Ehmo ehmo);



}
