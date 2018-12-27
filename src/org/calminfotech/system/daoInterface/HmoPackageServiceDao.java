package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.HmoPackageService;

public interface HmoPackageServiceDao {

	public List<HmoPackageService> fetchAll();

	public List<HmoPackageService> fetchAllByPackageId(int id);

	public HmoPackageService getServiceById(int id);

	public void save(HmoPackageService service);

	public void delete(HmoPackageService service);

	public void update(HmoPackageService service);

}
