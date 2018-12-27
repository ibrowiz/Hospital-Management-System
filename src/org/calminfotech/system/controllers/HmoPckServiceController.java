package org.calminfotech.system.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.system.boInterface.HmoPckServiceBo;
import org.calminfotech.system.forms.HmoPckServiceForm;
import org.calminfotech.system.models.HmoPckService;
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
public class HmoPckServiceController {
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private HmoPckServiceBo hmoPckServiceBo;
	
	@Layout(value = "layouts/datatable")
	@RequestMapping(value="/hmoservice/index", method = RequestMethod.GET)
	public String indexAction(Model model){
		
		List<HmoPckService> hmoPckService = hmoPckServiceBo.fetchAll();
		model.addAttribute("hmoPckService", hmoPckService);	
		System.out.println("Pack services is : " + hmoPckServiceBo.fetchAll().size());
		return "system/hmoservice/index";
		
	}
	
	
	@RequestMapping(value="/hmoservice/add", method = RequestMethod.GET)
	public String addAction(Model model){		
		
		HmoPckServiceForm hmoPckServiceForm = new HmoPckServiceForm();
		model.addAttribute("hmoPckServiceForm", hmoPckServiceForm);
		//model.addAttribute("hmoPckServiceForm", new HmoPckServiceForm());		
		return "system/hmoservice/add";		
	}
	
	@RequestMapping(value = "/hmoservice/add", method = RequestMethod.POST)
	public String saveAction(@Valid @ModelAttribute("hmoPckServiceForm") HmoPckServiceForm hmoPckServiceForm,
							  BindingResult result, RedirectAttributes redirectAttributes,
							  Model model){
		
		if(result.hasErrors()){
			return "system/hmoservice/add";
		}		
		
		HmoPckService hmoPckService = hmoPckServiceBo.save(hmoPckServiceForm);
				//hmoPckServiceBo.save(hmoPckServiceForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! Hmo Service Package Created");
		return "redirect:/hmoservice/index";		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
