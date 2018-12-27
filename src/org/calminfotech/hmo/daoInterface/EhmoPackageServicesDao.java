package org.calminfotech.hmo.daoInterface;

import java.util.List;

import org.calminfotech.hmo.models.EhmoPackageServices;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface EhmoPackageServicesDao {
	
public void save(EhmoPackageServices eHmoPackageServices);
	
	public void update(EhmoPackageServices eHmoPackageServices);
	
	public void delete(EhmoPackageServices eHmoPackageServices);
	
	public EhmoPackageServices getEhmoPackageServicesById(Integer Id);
	
	public List<EhmoPackageServices> fetchAllByOrganisation(int organisationId);

}
