package org.calminfotech.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.forms.UnitOfMeasurementForm;
import org.calminfotech.system.models.GlobalUnitofMeasure;
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
@RequestMapping(value = "/system/unitofmeasurement")
public class UnitOfMeasureController {
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private GlobalUnitofMeasureBo measureBo;
	
	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String index(Model model){		
		model.addAttribute("measurementList", measureBo.fetchAll());
		return "system/measurement/index";		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, UnitOfMeasurementForm measurementForm){		
				
		GlobalUnitofMeasure measure = new GlobalUnitofMeasure();
		
		measurementForm.setUnitOfMeasure(measure.getUnit_of_measure());
		measurementForm.setUnitAbbreviate(measure.getUnit());			
		model.addAttribute("measurementForm", measurementForm);		
		return "system/measurement/add";		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(@Valid @ModelAttribute("measurement") UnitOfMeasurementForm measureForm,
							BindingResult result, Model model,
							RedirectAttributes redirectAttributes){		
	
		if (result.hasErrors()) {			
			return "system/measurement/add";
		}
		
		GlobalUnitofMeasure measure = new GlobalUnitofMeasure();
		measure.setUnit(measureForm.getUnitAbbreviate());
		measure.setUnit_of_measure(measureForm.getUnitOfMeasure());
		measureBo.save(measure);		
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Unit Added Successfully! ");
		return "redirect:/system/unitofmeasurement";
		
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model){
		GlobalUnitofMeasure measurement = measureBo.getUnitofMeasureById(id);
		model.addAttribute("measurement", measurement);
		return "system/measurement/view";		
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") int unitId, Model model,
					   RedirectAttributes redirectAttributes, 
					   HttpServletRequest request){
		
		GlobalUnitofMeasure measure = measureBo.getUnitofMeasureById(unitId);
		if(null == measure){
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/unitofmeasurement";
		}
		
		UnitOfMeasurementForm mForm = new UnitOfMeasurementForm();
		
		mForm.setUnitId(measure.getId());
		mForm.setUnitOfMeasure(measure.getUnit_of_measure());
		mForm.setUnitAbbreviate(measure.getUnit());		
		
		model.addAttribute("measurement", measure);
		model.addAttribute("measurementForm", mForm);
		auditor.before(request, "Unit of Measurement Form", mForm);
		
		return "system/measurement/edit";
		
	}
	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("measurementForm") UnitOfMeasurementForm mForm,
						BindingResult result, Model model,
						RedirectAttributes redirectAttributes, HttpServletRequest request){		
		
		GlobalUnitofMeasure measurement = measureBo.getUnitofMeasureById(mForm.getUnitId());
		measurement.setUnit(mForm.getUnitAbbreviate());
		measurement.setUnit_of_measure(mForm.getUnitOfMeasure());
		
		measureBo.update(measurement);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Unit Of Measurement Updated Successfully! ");
		
		return "redirect:/system/unitofmeasurement";		
				
	}
	
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		
		GlobalUnitofMeasure measurement = measureBo.getUnitofMeasureById(id);

		if (null == measurement) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/unitofmeasurement";
		}
		UnitOfMeasurementForm mForm = new UnitOfMeasurementForm();
		mForm.setUnitId(measurement.getId());
		model.addAttribute("measurementForm", mForm);
		model.addAttribute("measurement", measurement);
		return "system/measurement/delete";
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("measurementForm") UnitOfMeasurementForm mForm,
			RedirectAttributes redirectAttributes) {
		GlobalUnitofMeasure measurement = measureBo.getUnitofMeasureById(mForm.getUnitId());
		if (null == measurement) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/unitofmeasurement";
		}
		measureBo.delete(measurement);		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Unit Of Measurement profile deleted");
		return "redirect:/system/unitofmeasurement";
	}
}
