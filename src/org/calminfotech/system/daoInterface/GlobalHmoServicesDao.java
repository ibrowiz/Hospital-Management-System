package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalHmoServices;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface GlobalHmoServicesDao {
	
public void save(GlobalHmoServices globalHmoServices);
	
	public void update(GlobalHmoServices globalHmoServices);
	
	public void delete(GlobalHmoServices globalHmoServices);
	
	public GlobalHmoServices getGlobalHmoServiesById(Integer serviceId);
	
	public List<GlobalHmoServices> fetchAllByOrganisation(int organisationId);

}
