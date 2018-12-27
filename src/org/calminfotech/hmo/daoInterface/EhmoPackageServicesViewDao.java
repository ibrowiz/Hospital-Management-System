package org.calminfotech.hmo.daoInterface;

import java.util.List;


import org.calminfotech.hmo.models.EhmoPackageServicesView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface EhmoPackageServicesViewDao {
	
public EhmoPackageServicesView getPackageServicesViewById(Integer id);
	
public List<EhmoPackageServicesView> fetchAllByOrganisation(Integer organisationid);

}
