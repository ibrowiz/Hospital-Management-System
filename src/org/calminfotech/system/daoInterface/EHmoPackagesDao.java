package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.system.models.EhmoPackages;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface EHmoPackagesDao {

	public List<EhmoPackages> fetchAllByOrganisation();

	public EhmoPackages getPackageById(int id);

	public void save(EhmoPackages hmoPackage);

	public void delete(EhmoPackages hmoPackage);

	public void update(EhmoPackages hmoPackage);
	
	public List<EhmoPackages> fetchAllByHMO(Ehmo hmo);

}
