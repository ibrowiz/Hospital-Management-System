package org.calminfotech.lab.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.lab.bo.LabResultSetupBo;
import org.calminfotech.lab.bo.LabTestBo;
import org.calminfotech.lab.forms.LabResultSetupForm;
import org.calminfotech.lab.models.LabResultSetup;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
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
@RequestMapping(value = "/system/labresult")
public class LabResultSetupController {


	@Autowired
	private LabResultSetupBo labResultBo;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private LabTestBo labTestBo;
	
	@Autowired
	GlobalUnitofMeasureBo gunitofMeasure;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@RequestMapping(value = "/resultdetail/{id}")
	@Layout("layouts/datatable")
	public String viewDet(@PathVariable("id") Integer id,Model model,
			RedirectAttributes redirectAttributes) {
		LabTest labTest = this.labTestBo.getLabtestById(id);
		List<LabResultSetup> labResult = this.labResultBo.getLabResultSetupByTestId(id);
		model.addAttribute("list",  this.labResultBo.fetchAll());
		LabResultSetupForm labF = new LabResultSetupForm();
		labF.setTestId(labTest.getId());
		model.addAttribute("lab", labTest);
		model.addAttribute("lTestResult", labResult);
		model.addAttribute("labResultForm", labF);
		model.addAttribute("List",  labResultBo.getLabResultSetupById(id));
		model.addAttribute("unitM", gunitofMeasure.fetchAll());
		return "lab/labresult/details";
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/labresultlist")
	public String viewActn(Model model) {
		model.addAttribute("List",  labResultBo.fetchAll());
		return "system/labresult/labresultlist";
	}
	
	@RequestMapping(value = "/addresult")
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(Model model) {
		//LaboratoryTest labTest = this.labTestBo.getLabtestById(id);
		LabResultSetupForm resultForm = new LabResultSetupForm();
		model.addAttribute("labResultForm",resultForm);
		return "system/labresult/addresult";	
	}
	
	@RequestMapping(value = "/saveresult")
	@Layout("layouts/datatable")
	public String addAction(
			@Valid @ModelAttribute("labResultForm") LabResultSetupForm labResultForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		
		labResultBo.save(labResultForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "+labResultForm.getResultName()+" added Successfully!");

		return "redirect:/system/labresult/resultdetail/"+labResultForm.getTestId();
	}

	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/showeditlabresult/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		LabResultSetup labResultSetup = this.labResultBo.getLabResultSetupById(id);
		if (null == labResultSetup) {

			return "redirect:/index";
		}

		LabResultSetupForm labRsetupForm = new LabResultSetupForm();

		labRsetupForm.setResultId(labResultSetup.getResultId());
		labRsetupForm.setTestId(labResultSetup.getLabTest().getId());
		labRsetupForm.setResultName(labResultSetup.getResultName());
		labRsetupForm.setMinimumValue(labResultSetup.getMinimumValue());
		labRsetupForm.setMaximumValue(labResultSetup.getMaximumValue());
		labRsetupForm.setType(labResultSetup.getType());
		labRsetupForm.setResultDescription(labResultSetup.getResultDescription());
		
		
		LabResultSetup labResultSetupList = this.labResultBo.getLabResultSetupById(id);
		
		model.addAttribute("labRsetupList", labResultSetupList);

		model.addAttribute("labRSetupForm", labRsetupForm);
		model.addAttribute("unitM", gunitofMeasure.fetchAll());
		
		auditor.before(request, "LabResultSetup Form", labRsetupForm);

		return "lab/labresult/editresult";

	}

	@RequestMapping(value = "/updatelabresultsetup/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("labRSetupForm") LabResultSetupForm labRSetupForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		
		
		this.labResultBo.update(labRSetupForm);
		auditor.after(request, "LabResultSetup Form", labRSetupForm,
				userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! updated");
		
		 return "redirect:/system/labresult/resultdetail/"+labRSetupForm.getTestId();
	}
	
	
}
