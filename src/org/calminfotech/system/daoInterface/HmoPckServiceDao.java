package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.HmoPckService;

public interface HmoPckServiceDao {
	
	public List<HmoPckService> fetchAll();
	
	public void save(HmoPckService hmoPckService);
	
	public void update(HmoPckService hmoPckService);
	
	public void delete(HmoPckService hmoPckService);
	
	public HmoPckService getHmoPckServiceById(int serviceId);

}
