package org.calminfotech.hmo.boInterface;

import java.util.List;

import org.calminfotech.hmo.forms.EhmoPackageServicesForm;
import org.calminfotech.hmo.models.EhmoPackageServices;

public interface EhmoPackageServicesBo {
	
public EhmoPackageServices save(EhmoPackageServicesForm eHmoPackageServicesForm);
	
	public void update(EhmoPackageServicesForm eHmoPackageServicesForm);
	
	public void delete(EhmoPackageServices eHmoPackageServices);
	
	public EhmoPackageServices getEhmoPackageServicesById(Integer Id);
	
	public List<EhmoPackageServices> fetchAllByOrganisation(int organisationId);

}
