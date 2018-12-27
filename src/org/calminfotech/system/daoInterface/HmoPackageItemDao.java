package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.HmoPackageDrug;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.utils.models.Organisation;

public interface HmoPackageItemDao {

	public List<HmoPackageDrug> fetchAll();

	public List<HmoPackageDrug> fetchAll(EhmoPackages oHmoPackage);

	public List<HmoPackageDrug> fetchAll(Drug drug);

	public void save(HmoPackageDrug packageDrug);

	public void delete(HmoPackageDrug packageDrug);

	public void update(HmoPackageDrug packageDrug);

	public HmoPackageDrug getItem(Integer itemId, Integer packageId);

}
