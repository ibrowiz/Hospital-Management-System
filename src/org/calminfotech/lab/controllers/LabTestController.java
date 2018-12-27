package org.calminfotech.lab.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.lab.bo.LabTestBo;
import org.calminfotech.lab.bo.LabTestCategoryBo;
import org.calminfotech.lab.bo.LabTestListBo;
import org.calminfotech.lab.forms.LabTestForm;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestList;

import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;

import org.calminfotech.system.forms.LaboratoryTestForm;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/system/labtest")
public class LabTestController {

	@Autowired
	private LabTestBo labTestBo;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private LabTestListBo labTestListBo;
	
	@Autowired
	private GlobalUnitofMeasureBo gunitofMeasureBo;
	
	@Autowired
	private LabTestCategoryBo labTestCatBo;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private Auditor auditor;
	
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/labtestlist")
	public String viewAction(Model model) {
		//LabTestCategory labcat = this.labTestCatBo.getLabtestCatById(id);
		
		//System.out.println("labtest details " + labTest.size());
		//model.addAttribute("list",  this.labTestCatBo.fetchAllCatByOrganisation(userIdentity.getOrganisation().getId()));
		LabTestForm labF = new LabTestForm();
		List<LabTestList> labTestList = this.labTestListBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("list", labTestList);
		model.addAttribute("unitM", gunitofMeasureBo.fetchAll());
		model.addAttribute("labTestForm", labF);
		model.addAttribute("labTest",  labTestBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId()));
		return "lab/labtest/labtestlist";
	}
	
	@RequestMapping(value = "/addtest")
	@Layout(value = "layouts/form_wizard_layout")
	public String addTetsAction(Model model) {
		model.addAttribute("unitM", gunitofMeasureBo.fetchAll());
		model.addAttribute("labTestForm", new LaboratoryTestForm());
		return "system/labtest/addtest";
		
	}
	
	@RequestMapping(value = "/savetest", method = RequestMethod.POST)
	public String addAction(
			@Valid @ModelAttribute("labTestForm") LabTestForm labTestForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
			
		labTestBo.save(labTestForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "+labTestForm.getName()+" added Successfully!");
		return "redirect:/system/labtest/labtestlist";
	
	}
	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/showeditlabtest/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		LabTest labTest = this.labTestBo.getLabtestById(id);
		
		if (null == labTest) {

			return "redirect:/index";
		}

		LabTestForm labTestForm = new LabTestForm();

		labTestForm.setId(labTest.getId());
		labTestForm.setCatId(labTest.getlCategory().getId());//labTest.getlCategory().getId()
		labTestForm.setName(labTest.getName());
		labTestForm.setDescription(labTest.getDescription());
		/*labTestForm.setCreatedBy(labTest.getCreatedBy());
		labTestForm.setCreatedDate(labTest.getCreatedDate());
		labTestForm.setModifiedBy(labTest.getModifiedBy());
		labTestForm.setLastModifiedDate(labTest.getLastModifiedDate());
		labTestForm.setStatus(labTest.getStatus());*/
		
		//LaboratoryTest labTestList = labTestBo.getLabtestById(id);		
		
	//	model.addAttribute("labTestList", labTestList);

		model.addAttribute("labTestForm", labTestForm);
		model.addAttribute("list", this.labTestCatBo.fetchAllCatByOrganisation(userIdentity.getOrganisation().getId()));
		auditor.before(request, "LabTest Form", labTestForm);
		return "lab/labtest/editlabtest";

	}

	@RequestMapping(value = "/updateLabtest/{id}")
	public String updateAction(
			@Valid @ModelAttribute("labTestForm") LabTestForm labTestForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		
		this.labTestBo.update(labTestForm);
		auditor.after(request, "LabTest Form", labTestForm,
				userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! updated");
		 return "redirect:/system/labtest/labtestlist";
	}
	
	
}
