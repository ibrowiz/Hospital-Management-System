package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.HmoPckSubServiceForm;
import org.calminfotech.system.models.HmoPckSubService;

public interface HmoPckSubServiceBo {
	
	public List<HmoPckSubService> fetchAll();
	
	public HmoPckSubService save(HmoPckSubServiceForm hmoPckSubServiceForm);
	
	public void update(HmoPckSubServiceForm hmoPckSubServiceForm);
	
	public void delete(HmoPckSubService hmoPckSubService);
	
	public HmoPckSubService getHmoPckSubServiceById(int subserviceId);

}
