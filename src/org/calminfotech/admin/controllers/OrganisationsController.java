package org.calminfotech.admin.controllers;

import java.io.IOException;
import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.HospitalDirectorBo;
import org.calminfotech.admin.boInterface.OrganisationBo;
import org.calminfotech.admin.forms.HospitalDirectorForm;
import org.calminfotech.admin.forms.OrganisationEditForm;
import org.calminfotech.admin.forms.OrganisationForm;
import org.calminfotech.admin.models.HospitalDirector;
import org.calminfotech.system.boInterface.ResourceBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.UserTypesListDao;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Hibernate;
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
@RequestMapping(value = "/admin/organisations")
public class OrganisationsController {

	@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private ResourceBo resourceBo;

	@Autowired
	private UserTypesListDao typeList;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private StatesList stateBo;
	
	@Autowired
	private LocalGovernmentAreaList lgaBo;
	
	@Autowired
	private HospitalDirectorBo directorBo;
	
	

	@RequestMapping(value = { "", "/" })
	@Layout("layouts/datatable")
	public String index(Model model) {

		model.addAttribute("organisations", this.organisationBo.fetchAll());

		return "admin/organisations/index";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Organisation organisation = this.organisationBo.getOrganisationById(id);

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/organisations";
		}
		model.addAttribute("organisation", organisation);
		return "admin/organisations/view";
	}

	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		model.addAttribute("oForm", new OrganisationForm());
		model.addAttribute("types", this.typeList.fetchAll());
		model.addAttribute("stateList", stateBo.fetchAll());
		model.addAttribute("lgaList", lgaBo.fetchAll());
		
		return "admin/organisations/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("oForm") OrganisationForm organisationForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "admin/organisations/add";
		}

		Organisation organisation = this.organisationBo.save(organisationForm);

		// Run the resource management system to keep the system up
		this.resourceBo.buildResourcesForOrganisation(organisation);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Organisation has been created!");

		return "redirect:/admin/organisations/view/" + organisation.getId();
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Organisation organisation = this.organisationBo.getOrganisationById(id);
		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/organisations/";
		}

		OrganisationEditForm organisationForm = new OrganisationEditForm();
		
		//State state = stateBo.getStateById(organisation.getState().getStateId());
		//LocalGovernmentArea lga = lgaBo.getLgaById(organisation.getLga().getLocalGovernmentAreaId());
		
		
		organisationForm.setId(organisation.getId());
		organisationForm.setName(organisation.getName());
		organisationForm.setHospitalType(organisation.getHospitalType());
		organisationForm.setAddress(organisation.getAddress());
		organisationForm.setDescription(organisation.getDescription());
		organisationForm.setSystemEmail(organisation.getSystemEmail());
		organisationForm.setConsultationCode(organisation.getConsultationCode());
		/*organisationForm.setAreaOfSpecialisation(organisation.getAreaOfSpecialisation());
		organisationForm.setEstablishedYear(organisation.getEstablishedYear());*/
		
		//model.addAttribute("lgas", this.lgaBo.fetchAll());
		model.addAttribute("states", this.stateBo.fetchAll());
		model.addAttribute("organisation", organisation);
		model.addAttribute("oForm", organisationForm);

		this.auditor.before(request, "Organisation Admin Form",
				organisationForm);

		return "admin/organisations/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("oForm") OrganisationEditForm organisationForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("types", this.typeList.fetchAll());
			return "admin/organisations/edit";
		}

		this.organisationBo.update(organisationForm);

		this.auditor.after(request, "Organisation Admin Form",
				organisationForm, this.userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Organisation updated");

		return "redirect:/admin/organisations/view/" + organisationForm.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Organisation organisation = this.organisationBo.getOrganisationById(id);
		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/organisations/";
		}

		OrganisationForm organisationForm = new OrganisationForm();
		organisationForm.setId(organisation.getId());

		model.addAttribute("organisation", organisation);
		model.addAttribute("oForm", organisationForm);
		return "admin/organisations/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("oForm") OrganisationForm organisationForm,
			RedirectAttributes redirectAttributes) {

		Organisation organisation = this.organisationBo.getOrganisationById(organisationForm.getId());

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/organisations/";
		}

		//this.organisationBo.delete(organisation);
		organisation.setDelete(true);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Organisation deleted!");
		return "redirect:/admin/organisations/";
	}
	
	//Hospital Directors 
	@RequestMapping(value = "/director/{id}")
	public String directorPage(@PathVariable("id") Integer id,
							   Model model,
							   RedirectAttributes redirectAttributes) {

		Organisation organ = organisationBo.getOrganisationById(id);
			
			if (null == organ) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/admin/organisations";
			}	
			
		HospitalDirectorForm directorForm = new HospitalDirectorForm();
		directorForm.setOrganisationId(organ.getId());		
		
		model.addAttribute("directorForm", directorForm);
		model.addAttribute("hospital", organ);
		model.addAttribute("directorList", directorBo.fetchAllDirectorByOrganisation(organ));
		return "admin/organisations/directors/index";
	}
	
	
	@RequestMapping(value = "/director/{id}", method = RequestMethod.POST)
	public String saveDirectorAction(
			@Valid @ModelAttribute("directorForm") HospitalDirectorForm directorForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {
		
		Organisation organisation = organisationBo.getOrganisationById(directorForm.getOrganisationId());				
		
		if (result.hasErrors()) {
			model.addAttribute("organisation", organisation);
			return "admin/organisations/directors/index";
		}

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not be save. Invalid resource");
			return "redirect:/admin/organisations";
		}

		HospitalDirector director = new HospitalDirector();
		director.setOrganisation(organisation);
		
		director.setDirectorLastName(directorForm.getDirectorLastName());
		director.setDirectorFirstName(directorForm.getDirectorFirstName());
		director.setDirectorEmail(directorForm.getDirectorEmail());
		director.setDirectorPhone(directorForm.getDirectorPhone());
		director.setCreatedBy(userIdentity.getUsername());
		director.setOrganisation(organisationBo.getOrganisationById(directorForm.getOrganisationId()));
		Blob blob = Hibernate.createBlob(directorForm.getDirectorAvatar().getInputStream());
		director.setDirectorAvatar(blob);
	
		String contentType =  directorForm.getDirectorAvatar().getContentType();
		director.setAvatarContentType(contentType);
	
		directorBo.save(director);		

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/admin/organisations/director/" + organisation.getId();
		
	}
	
	
	//To Edit Hospital Director
	@RequestMapping(value = "/director/edit/{id}")
	public String editDirectorPage(@PathVariable("id") Integer directorId,Model model){
		
		return "";
	}
	
	
	
	
	
	//To View Hospital Director
	@RequestMapping(value = "/director/view/{id}")
	public String viewDirector(@PathVariable("id") Integer directorId, Model model){
		
		Organisation hospital = organisationBo.getOrganisationById(directorId);		
		//System.out.println("Hospital Id is " + hospital.getId());
		
		HospitalDirector director = directorBo.getHospitalDirectorById(directorId);
		//director.setOrganisation(hospital);
		
		model.addAttribute("director", director);
		model.addAttribute("hospital", hospital);
		return "admin/organisations/directors/view";		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}