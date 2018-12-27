package org.calminfotech.system.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.calminfotech.system.boInterface.LoginSectionBo;
import org.calminfotech.system.boInterface.LoginSectionPointBo;
import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.forms.ItemPriceDetailForm;
import org.calminfotech.system.forms.LoginSectionForm;
import org.calminfotech.system.forms.LoginSectionPointForm;
import org.calminfotech.system.forms.BillingSchemeItemForm;
import org.calminfotech.system.models.BillingItemPriceDetail;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.system.models.LoginSectionPoint;
import org.calminfotech.system.models.BillingSchemeItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.GlobalItemTypeList;
import org.calminfotech.utils.SessionList;
import org.calminfotech.utils.WorkflowPointList;
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
@RequestMapping(value = "/system/setting/visitworkflows/section")
public class LoginSectionController {
	
	
	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;
		
	@Autowired
	private LoginSectionBo loginSectionBo;
	
	@Autowired
	private BillingSchemeBo paymentSchemeBo;
	
	
	@Autowired
	private VisitWorkflowPointBo wfPointBo;
	
	
	@Autowired
	private WorkflowPointList workflowPointList;
	
	@Autowired
	private SessionList sessionList;
	
	
	@Autowired
	private LoginSectionPointBo LoginSectionPointBo;
	
	
	
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = { "", "/index" })
	public String indexAction(Model model) {

		model.addAttribute("loginsectiontable",
				this.loginSectionBo.fetchAllByOrganisation());

		return "system/setting/visitworkflows/section/index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model, LoginSectionForm loginSectionForm){	
			

	
	LoginSection LoginSection = new LoginSection();
	

	LoginSection.setSession_name(loginSectionForm.getSession_name())	;
	

		model.addAttribute("pForm", loginSectionForm);
		model.addAttribute("schemes", this.paymentSchemeBo.fetchAllByOrganisation());
	
		
		return "system/setting/visitworkflows/section/add";
	}
	
	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(@Valid @ModelAttribute("pForm") LoginSectionForm loginSectionForm,
							BindingResult result, Model model,
							RedirectAttributes redirectAttributes){		
	
		
		if (result.hasErrors()) {			
			return "system/setting/visitworkflows/section/add";
		}
	

		LoginSection loginSection = this.loginSectionBo.save(loginSectionForm);
		
		
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
			" Success! New section Succesfully Added! " );

	return "redirect:/system/setting/visitworkflows/section/view/" + loginSection.getId() ;
	
	
}
	
	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

	
		
		LoginSection loginSection = this.loginSectionBo.getLoginSectionById(id);	
		
		
		if (null == loginSection) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/setting/visitworkflows/section/add";
			
		}
				
		model.addAttribute("LS", loginSection);
		model.addAttribute("pointList", this.workflowPointList.fetchAll());
		model.addAttribute("sessionList", this.sessionList.fetchAll());
		return "system/setting/visitworkflows/section/view";
	}
	
	
	// this is for price detail inside payment scheme item
		@RequestMapping(value = "/point/{id}")
		public String peicedetailAction(@PathVariable("id") Integer id,
				Model model, RedirectAttributes redirectAttributes) {

		
			LoginSection loginSection = this.loginSectionBo.getLoginSectionById(id);
			
			
			if (null == loginSection) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/system/setting/visitworkflows/section/point";
			}
			
			LoginSectionPointForm LS = new LoginSectionPointForm();
			LS.setLoginSection_id(loginSection.getId());
			

			model.addAttribute("LSForm", LS);
			model.addAttribute("pointList", this.workflowPointList.fetchAll());
			model.addAttribute("sessionList", this.sessionList.fetchAll());
			model.addAttribute("LSP", loginSection);

			return "system/setting/visitworkflows/section/point";
		}
	
		
		
		@RequestMapping(value = "/point/{id}", method = RequestMethod.POST)
		public String savePRICE(
				@Valid @ModelAttribute("LSForm") LoginSectionPointForm loginSectionPointForm,
				BindingResult result, Model model,
				RedirectAttributes redirectAttributes) throws IOException {

			
			LoginSection loginSection =this.loginSectionBo.getLoginSectionById(loginSectionPointForm.getLoginSection_id());
	
			
		
			if (result.hasErrors()) {
				model.addAttribute("loginSection", loginSection);
				return "system/setting/visitworkflows/section/point";
			}

			if (null == loginSection) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Could not attach point. Invalid resource");
				return "redirect:/system/setting/visitworkflows/section";
			}

			
			LoginSectionPoint loginSectionPoint = new LoginSectionPoint();
			
			loginSectionPoint.setLoginSection(loginSection);
		
			VisitWorkflowPoint visitWorkflowPoint = this.workflowPointList.getVisitWorkflowPointById(loginSectionPointForm.getPoint_id());
			loginSectionPoint.setWorkflowPoint(visitWorkflowPoint);
			
		
			loginSectionPoint.setOrganisation(loginSection.getOrganisation());

			// Saving 
	
			
			this.LoginSectionPointBo.save(loginSectionPoint);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! saved successfully");

			return "redirect:/system/setting/visitworkflows/section/point/" + loginSectionPoint.getLoginSection().getId();
			
	
		}
		
		
}
