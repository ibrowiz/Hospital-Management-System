package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.HmoAddPackageServices;

public interface HmoAddPackageServicesDao {
	
	public List<HmoAddPackageServices> fetchAll();
	
	public void save(HmoAddPackageServices hmoAddPackageServices);
	
	public void update(HmoAddPackageServices hmoAddPackageServices);
	
	public void delete(HmoAddPackageServices hmoAddPackageServices);
	
	public HmoAddPackageServices getHmoAddPackageServicesById(int id);

}
