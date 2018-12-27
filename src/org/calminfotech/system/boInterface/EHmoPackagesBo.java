package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.EHmoPackagesForm;
import org.calminfotech.system.models.Hmo;
import org.calminfotech.system.models.EhmoPackages;

public interface EHmoPackagesBo {

	public List<EhmoPackages> fetchAllByOrganisation();

	public EhmoPackages getPackageById(int id);

	public EhmoPackages save(EHmoPackagesForm form);

	public void delete(EhmoPackages hmoPackage);

	public void update(EHmoPackagesForm form);	
	
	public List<EhmoPackages> fetchAllByHMO(Integer hmoId);
}
