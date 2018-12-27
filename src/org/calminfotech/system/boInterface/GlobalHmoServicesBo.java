package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.GlobalHmoServicesForm;
import org.calminfotech.system.models.GlobalHmoServices;

public interface GlobalHmoServicesBo {
	
public GlobalHmoServices save(GlobalHmoServicesForm globalHmoServicesForm);
	
	public void update(GlobalHmoServicesForm globalHmoServicesForm);
	
	public void delete(GlobalHmoServices globalHmoServices);
	
	public GlobalHmoServices getGlobalHmoServiesById(Integer serviceId);
	
	public List<GlobalHmoServices> fetchAllByOrganisation(int organisationId);

}
