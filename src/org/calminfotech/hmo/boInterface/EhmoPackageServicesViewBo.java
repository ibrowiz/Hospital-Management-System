package org.calminfotech.hmo.boInterface;

import java.util.List;

import org.calminfotech.hmo.models.EhmoPackageServicesView;

public interface EhmoPackageServicesViewBo {

	public EhmoPackageServicesView getPackageServicesViewById(Integer id);
	
	public List<EhmoPackageServicesView> fetchAllByOrganisation(Integer organisationid);
	
}
