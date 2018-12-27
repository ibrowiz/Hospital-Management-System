package org.calminfotech.system.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.system.boInterface.HmoPckServiceBo;
import org.calminfotech.system.boInterface.HmoPckSubServiceBo;
import org.calminfotech.system.forms.HmoPckSubServiceForm;
import org.calminfotech.system.models.HmoPckSubService;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HmoPckSubServiceController {
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private HmoPckSubServiceBo hmoPckSubServiceBo;
	
	@Autowired
	private HmoPckServiceBo hmoPckServiceBo;
	
	@Layout(value = "layouts/datatable")
	@RequestMapping(value="/hmosubservice/index", method = RequestMethod.GET)
	public String indexAction(Model model){
		
		List<HmoPckSubService> hmoPckSubService = hmoPckSubServiceBo.fetchAll();
		model.addAttribute("hmoPckSubService", hmoPckSubService);		
		return "system/hmosubservice/index";
		
	}
	
	
	@RequestMapping(value="/hmosubservice/add", method = RequestMethod.GET)
	public String addAction(Model model){		
		
		HmoPckSubServiceForm hmoPckSubServiceForm = new HmoPckSubServiceForm();
		model.addAttribute("hmoPckSubServiceForm", hmoPckSubServiceForm);
		model.addAttribute("hmoPckServ", hmoPckServiceBo.fetchAll());		
		return "system/hmosubservice/add";		
	}
	
	@RequestMapping(value = "/hmosubservice/add", method = RequestMethod.POST)
	public String saveAction(@Valid @ModelAttribute("hmoPckSubServiceForm") HmoPckSubServiceForm hmoPckSubServiceForm,
							  BindingResult result, RedirectAttributes redirectAttributes,
							  Model model){
		
		if(result.hasErrors()){
			model.addAttribute("hmoPckService", hmoPckServiceBo.fetchAll());
			return "system/hmosubservice/add";
		}		
		
		HmoPckSubService hmoPckSubService = hmoPckSubServiceBo.save(hmoPckSubServiceForm);
			
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! Hmo SubService Package Created");
		return "redirect:/hmosubservice/index";		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
