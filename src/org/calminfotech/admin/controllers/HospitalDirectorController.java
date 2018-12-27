package org.calminfotech.admin.controllers;

import org.calminfotech.admin.boInterface.HospitalDirectorBo;
import org.calminfotech.admin.boInterface.OrganisationBo;
import org.calminfotech.admin.forms.HospitalDirectorForm;
import org.calminfotech.admin.models.HospitalDirector;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.models.Organisation;
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
@RequestMapping(value = "admin/organisations")
public class HospitalDirectorController {
	
	@Autowired
	private HospitalDirectorBo directorBo;
	
	@Autowired
	private OrganisationBo hospitalBo;
	
	@Autowired
	private Alert alert;
	
	/*@RequestMapping(value =  "/{hId}/director" )
	@Layout("layouts/datatable")
	public String index(@PathVariable("hId")Integer hId, Model model) {
		
		Organisation hospital = hospitalBo.getOrganisationById(hId);
		System.out.println("Hospital Id: " + hospital.getId());
		model.addAttribute("hospital", hospital);
		model.addAttribute("director", this.directorBo.fetchAll());

		return "admin/organisations/directors/index";
	}
	
	@RequestMapping(value="/{hId}/director/add", method = RequestMethod.GET)
	public String addDirector(@PathVariable("hId") Integer id, Model model){		
		
		Organisation hosipital = hospitalBo.getOrganisationById(id);
		HospitalDirectorForm directorForm = new HospitalDirectorForm();	
		directorForm.setOrganisationId(hosipital.getId());
		model.addAttribute("directorForm", directorForm);	
		model.addAttribute("hosipital", hosipital);
		return "admin/organisations/directors/add";		
	}
	
	
	@RequestMapping(value="/{hId}/director/add", method = RequestMethod.POST)
	public String saveDirector(@Valid @ModelAttribute("directorForm") HospitalDirectorForm directorForm,
									@ModelAttribute("hosipital") Organisation hospital,
							   BindingResult result ,Model model,
							   RedirectAttributes redirectAttributes){
		
		 hospital = hospitalBo.getOrganisationById(directorForm.getOrganisationId());
		HospitalDirector director = this.directorBo.save(directorForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Hospital Director Succesfully Added!");
			
		return "redirect:/admin/organisations/" + hospital.getId() + "/director" ;		
	}	*/
	
	/*@RequestMapping(value="/director/view/{hId}/{dId}", method = RequestMethod.GET)
	public String viewDirector(@PathVariable("dId") Integer dId,
							   @PathVariable("hId") Integer hId, Model model){				
		
		Organisation hospital = hospitalBo.getOrganisationById(hId);
		System.out.println("Hospital View Id is:" + hospital.getId());
		
		HospitalDirector director = directorBo.getHospitalDirectorById(dId);		
		System.out.println("Hospital Director View Id is:" + director.getDirectorId());
		System.out.println("Hospital Name is: " + director.getDirectorLastName());
		
		HospitalDirectorForm directorForm = new HospitalDirectorForm();		
		directorForm.setOrganisationId(hospital.getId());		
		directorForm.setDirectorId(director.getDirectorId());	
		 
		System.out.println("Director Form id is: " + directorForm.getDirectorId());
		System.out.println("Director's Name: " + directorForm.getDirectorLastName());
		
		model.addAttribute("directorForm", directorForm);		
		
		return "admin/organisations/directors/view";		
	}*/
	
	
	@RequestMapping(value="/director/edit/{hId}/{dId}", method = RequestMethod.GET)
	public String editDirector(@PathVariable("dId") Integer dId,
							   @PathVariable("hId") Integer hId, Model model){				
		
		Organisation hospital = hospitalBo.getOrganisationById(hId);
		System.out.println("Hospital Id is :" + hospital.getId());
		
		HospitalDirector director = directorBo.getHospitalDirectorById(dId);
		System.out.println("Directors Id is :" + director.getDirectorId());
		
		HospitalDirectorForm directorForm = new HospitalDirectorForm();
		
		directorForm.setOrganisationId(hospital.getId());
		directorForm.setDirectorId(director.getDirectorId());
		directorForm.setDirectorLastName(director.getDirectorLastName());
		directorForm.setDirectorFirstName(director.getDirectorFirstName());
		directorForm.setDirectorEmail(director.getDirectorEmail());
		directorForm.setDirectorPhone(director.getDirectorPhone());
		
		model.addAttribute("directorForm", directorForm);
		
		
		return "admin/organisations/directors/edit";		
	}
	
	@RequestMapping(value="/director/edit/{hId}/{dId}", method = RequestMethod.POST)
	public String updateDirector(@ModelAttribute("directorForm") HospitalDirectorForm directorForm, 
								 BindingResult result ,Model model,
								 RedirectAttributes redirectAttributes){
		
		Organisation hospital = hospitalBo.getOrganisationById(directorForm.getOrganisationId());
		HospitalDirector director = directorBo.getHospitalDirectorById(directorForm.getDirectorId());
		this.directorBo.update(directorForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Hospital Director Updated Succesfully!");
		
		return "redirect:/admin/organisations/" + hospital.getId() + "/director" ;			
		
	}
	
	
	//Delete Hospital Director
	@RequestMapping(value="/director/delete/{hId}/{dId}", method = RequestMethod.GET)
	public String deleteDirector(@PathVariable("dId") Integer dId,
							   @PathVariable("hId") Integer hId, Model model){				
		
		Organisation hospital = hospitalBo.getOrganisationById(hId);
		System.out.println("Hospital View Id is:" + hospital.getId());
		
		HospitalDirector director = directorBo.getHospitalDirectorById(dId);		
		System.out.println("Hospital Director View Id is:" + director.getDirectorId());
		
		HospitalDirectorForm directorForm = new HospitalDirectorForm();		
		directorForm.setOrganisationId(hospital.getId());		
		directorForm.setDirectorId(director.getDirectorId());	
		
		System.out.println("Director's Name: " + directorForm.getDirectorLastName());
		
		model.addAttribute("directorForm", directorForm);		
		
		return "admin/organisations/directors/view";		
	}
	
	
	
	
	
	
	

}
