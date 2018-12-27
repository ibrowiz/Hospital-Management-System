package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.HmoPckSubService;

public interface HmoPckSubServiceDao {
	
	public List<HmoPckSubService> fetchAll();
	
	public void save(HmoPckSubService hmoPckSubService);
	
	public void update(HmoPckSubService hmoPckSubService);
	
	public void delete(HmoPckSubService hmoPckSubService);
	
	public HmoPckSubService getHmoPckSubServiceById(int subserviceId);

}
