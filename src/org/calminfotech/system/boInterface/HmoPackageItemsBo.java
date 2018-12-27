package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.HmoPackageItemsForm;
import org.calminfotech.system.models.HmoPackageItems;

public interface HmoPackageItemsBo {
	
	public List<HmoPackageItems> fetchAll();
	
	//public void save(HmoPckServiceForm hmoPckServiceForm);
	
	public HmoPackageItems save(HmoPackageItemsForm hmoAddPackageServicesForm);
	
	public void update(HmoPackageItemsForm hmoAddPackageServices);
	
	public void delete(HmoPackageItems hmoAddPackageServices);
	
	public HmoPackageItems getHmoAddPackageServicesById(int serviceId);
	
	public HmoPackageItems getSpendingLimit(int HmoPackage, int hmoPckSubservice);
}
