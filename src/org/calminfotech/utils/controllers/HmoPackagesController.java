package org.calminfotech.utils.controllers;

import org.calminfotech.admin.boInterface.HmoBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.models.Hmo;
import org.calminfotech.system.models.HmoPackageService;
import org.calminfotech.system.models.EhmoPackages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/utilities/hmopackage")
public class HmoPackagesController {

	@Autowired
	private HmoBo hmoBo;

	@Autowired
	private EHmoPackagesBo hmoPackageBo;

	@ResponseBody
	@RequestMapping(value = "/packagebyhmo/{id}")
	public String getPackageById(@PathVariable("id") Integer id) {

		String response = "";
		Hmo hmo = this.hmoBo.getHmoById(id);

		if (hmo.getOrganisationHmoPackages().size() > 0) {
			response = "<option value''>Select a package</option>";
			for (EhmoPackages hmoPackage : hmo
					.getOrganisationHmoPackages())
				response += "<option value='" + hmoPackage.getId() + "'>"
						+ hmoPackage.getName() + "</option>";
		} else {
			response = "<option value''>No Package available</option>";
		}

		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/servicesbypackage/{id}")
	public String getServicesById(@PathVariable("id") Integer id) {

		String response = "";
		EhmoPackages hmoPackage = this.hmoPackageBo
				.getPackageById(id);
		if (hmoPackage.getPackageServices().size() > 0) {
			response = "<option value''>Select a service</option>";
			for (HmoPackageService service : hmoPackage.getPackageServices())
				response += "<option value='" + service.getId() + "'>"
						+ service.getName() + "(=N= " + service.getPrice()
						+ ")" + "</option>";
		} else {
			response = "<option value''>No Service available</option>";
		}

		return response;
	}
}
