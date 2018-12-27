package org.calminfotech.system.controllers;

import java.util.List;
import java.util.Set;

import org.calminfotech.system.boInterface.HmoPckServiceBo;
import org.calminfotech.system.boInterface.HmoPckSubServiceBo;
import org.calminfotech.system.models.HmoPckService;
import org.calminfotech.system.models.HmoPckSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/utilities/servciesandsubservices")
public class ServiceAndSubServiceController {
	
	@Autowired
	private HmoPckServiceBo hmoPckServiceBo;
	
	@Autowired
	private HmoPckSubServiceBo hmoPckSubServiceBo;
	
	@RequestMapping(value = "/services", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getService(){
		String hmoServiceStr = "<option value='0'>Select Services</option>";
		List<HmoPckService> hmoServiceList = hmoPckServiceBo.fetchAll();
		for(HmoPckService hmoPckService : hmoServiceList){
			hmoServiceStr += "<option value='" + hmoPckService.getServiceId() + "'> " + hmoPckService.getServiceName() + "</option>";
		}		
		return hmoServiceStr;		
	}
	
	
	@RequestMapping(value = "/subservice", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getSubservice(){
		String hmoSubserviceStr = "<option value='0'>Select SubServices</option>";
		List<HmoPckSubService> hmoSubserviceList = hmoPckSubServiceBo.fetchAll();
		for(HmoPckSubService hmoPckSubService : hmoSubserviceList){
			hmoSubserviceStr += "<option value='" + hmoPckSubService.getId() + "'> " + hmoPckSubService.getSubserviceName() + "</option>";
		}
		return hmoSubserviceStr;
		
	}
	
	@RequestMapping(value ="/servicebysubservice/{serviceId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getSubserviceByServiceId(@PathVariable("serviceId") Integer serviceId){
		String hmoPckSubserviceStr = "<option value='0'>Select Hmo SubService</option>";
		
		HmoPckService hmoPckService = hmoPckServiceBo.getHmoPckServiceById(serviceId);
		
		if(hmoPckService == null)
			return hmoPckSubserviceStr;
		
		Set<HmoPckSubService> list = hmoPckService.getHmoPckSubService();		
		for(HmoPckSubService hmoPckSubService : list){
			hmoPckSubserviceStr += "<option value='" + hmoPckSubService.getId() + "'>" + hmoPckSubService.getSubserviceName() + "</option>";
		}				
		return hmoPckSubserviceStr;
		
	}
	

}































































