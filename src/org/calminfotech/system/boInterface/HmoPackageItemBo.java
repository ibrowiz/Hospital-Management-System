package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.HmoPackageItemForm;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.HmoPackageDrug;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.utils.models.Organisation;

public interface HmoPackageItemBo {

	public List<HmoPackageDrug> fetchAll();

	public List<HmoPackageDrug> fetchAll(EhmoPackages oHmoPackage);

	public List<HmoPackageDrug> fetchAll(Drug drug);

	public void save(HmoPackageItemForm form);

	public void delete(HmoPackageDrug packageDrug);

	public void update(HmoPackageItemForm form);

	public HmoPackageDrug getItem(Integer itemId, Integer packageId);

}
