package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.HmoPckServiceForm;
import org.calminfotech.system.models.HmoPckService;

public interface HmoPckServiceBo {
	
	public List<HmoPckService> fetchAll();
	
	//public void save(HmoPckServiceForm hmoPckServiceForm);
	
	public HmoPckService save(HmoPckServiceForm hmoPckServiceForm);
	
	public void update(HmoPckServiceForm hmoPckServiceForm);
	
	public void delete(HmoPckService hmoPckService);
	
	public HmoPckService getHmoPckServiceById(int serviceId);

}
