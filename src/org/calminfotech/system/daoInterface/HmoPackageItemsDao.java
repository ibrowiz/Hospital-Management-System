package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.HmoPackageItems;
import org.calminfotech.system.models.HmoPckSubService;
import org.calminfotech.system.models.EhmoPackages;

public interface HmoPackageItemsDao {
	
	public List<HmoPackageItems> fetchAll();
	
	public void save(HmoPackageItems hmoAddPackageServices);
	
	public void update(HmoPackageItems hmoAddPackageServices);
	
	public void delete(HmoPackageItems hmoAddPackageServices);
	
	public HmoPackageItems getHmoAddPackageServicesById(int id);
	
	public HmoPackageItems getSpendingLimit(EhmoPackages organisationHmoPackage, HmoPckSubService hmoPckSubservice);

}
