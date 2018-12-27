package org.calminfotech.system.controllers;

import org.calminfotech.system.boInterface.OrganisationDirectorBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "system/setting/organisation/director")
public class OrganisationController {
	
	@Autowired
	private OrganisationDirectorBo directorBo;
	
	/*@RequestMapping()
	public String index(Model model){
		model.addAttribute("directors", directorBo.fetchAll());
		return null;		
	}*/

}
