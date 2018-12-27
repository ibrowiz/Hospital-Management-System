package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.HmoAddPackageServicesForm;
import org.calminfotech.system.forms.HmoPckServiceForm;
import org.calminfotech.system.models.HmoAddPackageServices;

public interface HmoAddPackageServicesBo {
	
	public List<HmoAddPackageServices> fetchAll();
	
	//public void save(HmoPckServiceForm hmoPckServiceForm);
	
	public HmoAddPackageServices save(HmoAddPackageServicesForm hmoAddPackageServicesForm);
	
	public void update(HmoPckServiceForm hmoAddPackageServices);
	
	public void delete(HmoAddPackageServices hmoAddPackageServices);
	
	public HmoAddPackageServices getHmoAddPackageServicesById(int serviceId);

}
