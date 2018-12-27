package org.calminfotech.consultation.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillUnitOfMeasureBo;
import org.calminfotech.billing.models.BillUnitOfMeasure;
import org.calminfotech.consultation.bo.ConsultationBo;
import org.calminfotech.consultation.bo.RescheduledVisitBo;
import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.consultation.bo.VisitTimelineBo;
import org.calminfotech.consultation.dao.VisitDao;
import org.calminfotech.consultation.forms.BillingForm;
import org.calminfotech.consultation.forms.PaymentForm;
import org.calminfotech.consultation.forms.RescheduleVisitForm;
import org.calminfotech.consultation.forms.VisitCloseForm;
import org.calminfotech.consultation.forms.VisitPaymentForm;
import org.calminfotech.consultation.forms.VisitWorkflowUserConfigurationForm;
import org.calminfotech.consultation.forms.VisitationForm;
import org.calminfotech.consultation.models.Consultation;
import org.calminfotech.consultation.models.PaymentItemType;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitTimeline;
import org.calminfotech.hr.boInterface.ClockinBoInterface;
import org.calminfotech.hr.forms.AssignForm;
import org.calminfotech.hr.models.Clockin;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHmo;
import org.calminfotech.patient.models.PatientHmoBillSchemeView;
import org.calminfotech.setup.boInterface.UnitBo;
import org.calminfotech.setup.boInterface.UnitCategoryBo;
import org.calminfotech.setup.boInterface.UnitListBo;
import org.calminfotech.setup.models.HrUnit;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.setup.models.UnitList;
import org.calminfotech.staffreg.boInterface.StaffRegBoInterface;
import org.calminfotech.staffreg.models.StaffRegistration;
import org.calminfotech.system.boInterface.BillingInvoiceBo;
import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.ConsultationStatusBo;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemPointBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.boInterface.HmoBalanceBo;
import org.calminfotech.system.boInterface.HmoSubserviceItemBo;
import org.calminfotech.system.boInterface.LoginSectionBo;
import org.calminfotech.system.boInterface.PaymentLogBo;
import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.forms.RaiseBillForm;
import org.calminfotech.system.models.BillingInvoice;
import org.calminfotech.system.models.ConsultationStatus;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemPoint;
import org.calminfotech.system.models.HmoBalance;
import org.calminfotech.system.models.HmoSubserviceItem;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.AutoGenerate;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.views.boInterface.VwItemBo;
import org.calminfotech.views.models.VwItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.calminfotech.livestock.form.PenForm;

@Controller
@RequestMapping(value = "/consultations/visits")
public class VisitsController {
	
	@Autowired
    private StaffRegBoInterface staffRegBo;
	
	@Autowired
	private UnitListBo unitListBo;
	
	@Autowired
	private PatientHmoBo patientHmoBo;
	
	@Autowired
	private UnitCategoryBo unitCatBo;
	
	@Autowired
	private UnitBo unitBo;
	

	@Autowired
	private ClockinBoInterface clockinBo;
	
	@Autowired
	private GlobalItemPointBo globalItemPointBo;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private RescheduledVisitBo rescheduledVisitBo;
	
	@Autowired
	private BillUnitOfMeasureBo billUnitOfMeasureBo;

	@Autowired
	private Auditor auditor;

	@Autowired
	private VisitBo visitBo;
	
	@Autowired
	private VisitDao visitDao;
	
	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;

	@Autowired
	private ConsultationBo consultationBo;

	@Autowired
	private VisitWorkflowPointBo wfPointBo;
	
	@Autowired
	private VisitTimelineBo visitTimelineBo;

	@Autowired
	private VisitStatusBo visitStatusBo;

	@Autowired
	private VwItemBo vwItemBo;

	@Autowired
	private LoginSectionBo loginSectionBo;

	@Autowired
	private BillingSchemeBo billingSchemeBo;

	@Autowired
	private ConsultationStatusBo consultationStatusBo;

	@Autowired
	private VisitWorkflowPointBo VWfPointBo;
	@Autowired
	private UserBo userBo;

	@Autowired
	private GlobalItemBo itemBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;

	@Autowired
	private BillingInvoiceBo billingInvoiceBo;

	@Autowired
	private PatientBo patientBo;

	@Autowired
	private HmoSubserviceItemBo hmoSubserviceItemBo;

	@Autowired
	private PaymentLogBo paymentLogBo;

	@Autowired
	private HmoBalanceBo hmoBalanceBo;

	@Layout("layouts/datatable")
	@RequestMapping(value = { "", "/index" })
	public String index(Model model) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
		model.addAttribute("visits", this.visitBo.fetchAll());
		return "consultations/visits/index";
	}
		
	@SuppressWarnings("unused")
	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Visit visit = this.visitBo.getVisitationById(id);
		List<VisitTimeline> visitTimeline = this.visitTimelineBo.getVTimelineByVisitId(id);
		VisitTimeline visitTimelineimg = this.visitTimelineBo.getVisitTlineByVisitId(id);
	Patient patient = this.patientBo.getPatientById(visit.getPatient().getPatientId());
		List<Visit> visit1 = this.visitBo.fetchAll(patient);
		
	//Visit visit1 = this.visitBo.getVisitByPatient(patient);
		
		
		System.out.println("Visit is here :" + visit.getId() + " Discription :"
				+ visit.getCode());
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/consultations";
		}
		VisitWorkflowUserConfigurationForm vForm = new VisitWorkflowUserConfigurationForm();
		model.addAttribute("vForm", vForm);
		model.addAttribute("visit", visit);
		model.addAttribute("visit1", visit1);
		model.addAttribute("visitTimeline", visitTimeline);
		model.addAttribute("visitTimelineimg", visitTimelineimg);
		model.addAttribute("id", id);
		return "consultations/visits/view";
	}

	// configure visit (1)
	/*@RequestMapping(value = "/configure/{id}")
	public String configureBill(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Visit visit = this.visitBo.getVisitationById(id);
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/consultations/visits";
		}
		model.addAttribute("visit", visit);
		VisitPaymentForm pForm = new VisitPaymentForm();
		pForm.setVisitId(visit.getId());
		pForm.setPaymentType(PaymentItemType.Service.toString());
		model.addAttribute("pForm", pForm);
		VisitWorkflowUserConfigurationForm vwfUserConfigForm = new VisitWorkflowUserConfigurationForm();
		vwfUserConfigForm.setVisitId(visit.getId());
		model.addAttribute("dForm", vwfUserConfigForm);
		model.addAttribute("points", this.wfPointBo.fetchAllByOrganisation());
		model.addAttribute("sections",
				this.loginSectionBo.fetchAllByOrganisation());
		return "consultations/visits/configure";
	}
	*/
	
	@RequestMapping(value = "/configureindex")
	public String configureIndex(Model model,
			@Valid @ModelAttribute("visitForm") VisitationForm visitForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		model.addAttribute("vForm",  visitForm);
		
		AssignForm assignForm = new AssignForm();
		
		model.addAttribute("assignForm", assignForm);
		
		
		
		return "consultations/visits/configureindex";
		
	}
	
	//ibrahim
	@RequestMapping(value = "/configure/{id}")
	public String configureBill(@PathVariable("id") Integer id, Model model,
			@Valid @ModelAttribute("vForm") VisitationForm visitForm,
			@Valid @ModelAttribute("assignForm") AssignForm assignForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
	//Session session = sessionFactory.openSession();
		  
		 // Transaction tx = null;
		  //try{
		   
		 // tx = session.beginTransaction();
		   
				Patient patient = this.patientBo.getPatientById(id);
				
				Visit visit = new Visit();			
				visit.setCode(new AutoGenerate().mygen());
				System.out.println("code is"+visit.getCode());
				visit.setPatient(patient);
				visit.setUserId(this.userIdentity.getUserId());
				visit.setOrganisation(this.userIdentity.getOrganisation());
				visit.setCreatedBy(this.userIdentity.getUsername());
				visit.setDeleted(false);
				// Ensure that an organisation has a start point
				visit.setStatus(this.visitStatusBo.getStartPointStatus());
				VisitWorkflowPoint point = this.wfPointBo.getWorkflowStartPoint(userIdentity.getOrganisation().getId());
				visit.setPoint(point);
				//User user = this.userBo.getUserById(this.userIdentity.getUserId());
				visit.setCreateDate(new GregorianCalendar().getTime());
				visit.setUnitId(6);
				//System.out.println("unit1 id is"+vst.getUnitId());
				 //Visit visit2 = this.visitBo.getVisitByPatient(id);
		         Visit vst=this.visitBo.save(visit);
			 // Visit visit2 = this.visitBo.getVisitByPatient(id);
				//VisitationForm visit1 = new  VisitationForm();
			  visitForm.setId(vst.getId());
			  visitForm.setCode(vst.getCode());
				
			//	Visit visit1 = this.visitBo.getVisitationById(id);
				/*if (null == visit) {
					this.alert.setAlert(redirectAttributes, Alert.ERROR,
							"Error! Invalid resource");
					return "redirect:/consultations/visits";
				}*/
				//model.addAttribute("visit1", visit1);
				//model.addAttribute("visit2", visit2);
				model.addAttribute("vForm",  visitForm);
				model.addAttribute("vst", vst);
				model.addAttribute("pid", id);
				VisitPaymentForm pForm = new VisitPaymentForm();
				pForm.setVisitId(visit.getId());
				pForm.setPaymentType(PaymentItemType.Service.toString());
				model.addAttribute("pForm", pForm);
				//AssignForm assignForm = new AssignForm();
				assignForm.setVisitId(vst.getId());
				model.addAttribute("assignForm", assignForm);
				//model.addAttribute("points", this.wfPointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
				model.addAttribute("sections",
				this.loginSectionBo.fetchAllByOrganisation());
				model.addAttribute("unit", unitListBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId()));
				VisitWorkflowPoint visitWorkflowPoint = this.globalItemPointBo.fetchGlobalItemViaPoint();
				List<GlobalItem> globalItem = this.itemBo.fetchAllByOrganisation();
				model.addAttribute("globalItemList", visitWorkflowPoint.getGlobalItem());
				List<BillUnitOfMeasure> billUnitOfMeasure = this.billUnitOfMeasureBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
				model.addAttribute("billUnitOfMeasure", billUnitOfMeasure);
				List<PatientHmoBillSchemeView> patientHmoBSV = this.patientHmoBo.fetchPatientHmoByPatient(patient.getPatientId());
				System.out.println("the view size is " + patientHmoBSV.size());
				model.addAttribute("patientHmo", patientHmoBSV);
				RaiseBillForm raiseBillForm = new RaiseBillForm();
				HrUnit hrunit = this.unitBo.getUnitById(3);
				raiseBillForm.setVisitId(vst.getId());
				raiseBillForm.setActivityId(vst.getId());
				
				/*raiseBillForm.setBillSchemeId(hrunit.getBillingSchemeId());
				raiseBillForm.setPointId(hrunit.getPoint().getId());*/
				model.addAttribute("raiseBillForm", raiseBillForm);
				VisitTimeline visitTimeline = new VisitTimeline();
				visitTimeline.setVisit(vst);
				visitTimeline.setUserId(userIdentity.getUserId());
				VisitWorkflowPoint point1 = this.wfPointBo.getWorkflowStartPoint(userIdentity.getOrganisation().getId());
				visitTimeline.setPoint(point1);
				//visitTimeline.setDepartment(vst.getDepartment());
				//visitTimeline.setLoginSection(vst.getLoginSection());
				//HrUnitCategory unitCategory = unitCatBo.getCategoryById(vst.getUnitId());
				System.out.println("unit id2 is"+vst.getUnitId());
				//visitTimeline.setUnitCategoryId(visit2.getUnitId());
				visitTimeline.setComment("No comment");
				visitTimeline.setCreatedBy(this.userIdentity.getUserId());
				visitTimeline.setCreateDate(new GregorianCalendar().getTime());
				visitTimeline.setStatus(true);
				VisitTimeline visitT = this.visitTimelineBo.save(visitTimeline);
			  
		/*  tx.commit();
		  
		  }catch(Exception e){
		   tx.rollback();
		   e.printStackTrace();
		  }finally{
		   session.close();*/
		 // }
		  
		return "consultations/visits/configure";
	}
	
	
	@RequestMapping(value = "/configure_reopen/{vid}")
	public String configure(@PathVariable("vid")Integer vid, Model model,
			@Valid @ModelAttribute("visitForm") VisitationForm visitForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		
		//Visit visit = new Visit();

		
		
          //Visit vst=this.visitBo.save(visit);
          /*System.out.println("vst");
          System.out.println(vst.getId());
          System.out.println("vst");*/

Visit visit2 = this.visitBo.getVisitByCode(vid);
		VisitationForm visit1 = new  VisitationForm();
		visit1.setId(visit2.getId());
		visit1.setCode(visit2.getCode());
		visit1.setPoint_id(visit2.getPoint().getId());
		visit1.setPoint_name(visit2.getPoint().getName());
	//	Visit visit1 = this.visitBo.getVisitationById(id);
		/*if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/consultations/visits";
		}*/
		model.addAttribute("visit1", visit1);
		model.addAttribute("visit2", visit2);
		model.addAttribute("vForm", visit1);
		//model.addAttribute("vst", vst);
		model.addAttribute("pid", vid);
		VisitPaymentForm pForm = new VisitPaymentForm();
		pForm.setVisitId(visit2.getId());
		pForm.setPaymentType(PaymentItemType.Service.toString());
		model.addAttribute("pForm", pForm);
		VisitWorkflowUserConfigurationForm vwfUserConfigForm = new VisitWorkflowUserConfigurationForm();
		vwfUserConfigForm.setVisitId(visit2.getId());
		model.addAttribute("dForm", vwfUserConfigForm);
		model.addAttribute("points", this.wfPointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("sections",
				this.loginSectionBo.fetchAllByOrganisation());
		
		
		return "consultations/visits/configure1";
	}
	
	
	@RequestMapping(value = "/savetimeline/{id}")
	public String saveTimeline(
			@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes){
		Visit visit = visitBo.getVisitationById(id);
		VisitTimeline visitTimeline = new VisitTimeline();
		visitTimeline.setVisit(visit);
		VisitWorkflowPoint point = this.wfPointBo.getWorkflowStartPoint(userIdentity.getOrganisation().getId());
		visitTimeline.setPoint(point);
		visitTimeline.setComment("No comment");
		visitTimeline.setCreatedBy(this.userIdentity.getUserId());
		visitTimeline.setCreateDate(new GregorianCalendar().getTime());
		visitTimeline.setStatus(true);
		VisitTimeline visitT = this.visitTimelineBo.save(visitTimeline);
		return "redirect:/system";
	}
	
	//ibrahim
	@Layout("layouts/datatable")
	@RequestMapping(value = "/history/{patientId}")
	public String hasOpenConsultation(
			@PathVariable("patientId") Integer patientId, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(patientId);
		List<Visit> visit1 = this.visitBo.fetchAll(patient);
		Visit visit3 = this.visitBo.getLastVisit2(patient);
		// Check if patient existview
		if (null == patient) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid Resource");
			return "redirect:/consultations/visits";
		}

		
		model.addAttribute("patient", patient);
		model.addAttribute("pID", patientId);
		model.addAttribute("pIC", visit1);
		// Get the on-going-consultation
		Visit onGoingConsultation = this.visitBo
				.getOnGoingConsultationByPatient(patient);
		model.addAttribute("consultation", onGoingConsultation);

		// Get the last visit..
		Visit visit = this.visitBo.getLastVisit1(onGoingConsultation);
		model.addAttribute("visit", visit);
		model.addAttribute("visit1", visit1);
		return "consultations/switch";
	}

	@RequestMapping(value = "/create/{patientId}")
	public String createAction(@PathVariable("patientId") int patientId,
			Model model, RedirectAttributes redirectAttributes) {
		Patient patient = this.patientBo.getPatientById(patientId);
		System.out.println("Check here :"+patient.getPatientId()+" Name: "+patient.getFirstName());
		// Check if patient exist
		if (null == patient) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid Resource");
			return "redirect:/consultations/visits";
		}

		boolean hasOpenConsultation = this.visitBo
				.hasConsultation(patient);

		if (hasOpenConsultation) {
			// Redirect to a page to ask if consultation be closed or a new
			// visitation should run on the consultation
			this.alert.setAlert(redirectAttributes, Alert.ERROR, "Patient: "
					+ patient.getSurname() + " " + patient.getOthernames()
					+ " has an ongoing consultation");
			return "redirect:/consultations/history/" + patientId;
		}

		// For consultation start point here
		Consultation consultation = this.consultationBo.create(patient);
		// Create a visit for the consultation here
		//Visit visit = this.visitBo.createVisit(consultation);
				
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Consultation is open and Visit created for patient!");
		return "redirect:/consultations/visits/configure/" + patientId;
	}


	@RequestMapping(value = "/newvisit/{vId}")
	public String newVisitAction(
			@PathVariable("vId") Integer vId,
			Model model, RedirectAttributes redirectAttributes) {
		// Ensure start point created
		if (null == this.visitStatusBo.getStartPointStatus()) {
			this.alert
					.setAlert(redirectAttributes, Alert.DANGER,
							"No visit status start point available! Create a start point before proceeding");
			return "redirect:/system/visits/statuses";
		}
		// Ensure start point created
		if (null == this.wfPointBo.getWorkflowStartPoint(userIdentity.getOrganisation().getId())) {
			System.out.println("this.wfPointBo.getWorkflowStartPoint() "+this.wfPointBo.getWorkflowStartPoint(userIdentity.getOrganisation().getId()));
			this.alert.setAlert(
							redirectAttributes,
							Alert.DANGER,
							"No visit workflow start point available! Create a start point before proceeding");
			return "redirect:/system/setting/visitworkflows";
		}
		/*Visit visit = this.visitBo
				.getVisitationById(vId);
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid Resource!");
			return "redirect:/consultations";
		}*/
		System.out.println("consultation "+vId);
		//Visit visit = this.visitBo.createVisit(visit);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Visit has been created!");
		return "redirect:/consultations/visits/configure/" + vId;
	}
	
	
	// assign visit (2)
	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	public String assignVisit(
			@Valid @ModelAttribute("assignForm") AssignForm assignForm,
			BindingResult result, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (result.hasErrors()) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Ensure the form is filled correctly");
			return "redirect:/consultations/visits/configure/"
					+ assignForm.getVisitId();
		}
		Visit visit = this.visitBo.getVisitationById(assignForm
				.getVisitId());
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resource!");
			return "redirect:/consultations/visits";
		}
		//this.visitBo.assignUserToPoint(vwfUserConfigForm);
		VisitTimeline visitTimeline = new VisitTimeline();
		visitTimeline.setVisit(visit);
		System.out.println("the assigned unit is "+assignForm.getUnitId());
		HrUnitCategory unitCategory = this.unitCatBo.getCategoryById(assignForm.getUnitId());
		
		//System.out.println("workflow here"+ unitCategory.getPoint().getId());
		//visitTimeline.setPoint(unitCategory.getPoint());
		visitTimeline.setUserId(assignForm.getUserId());
		//visitTimeline.setDepartment(visit.getDepartment());
		//visitTimeline.setLoginSection(visit.getLoginSection());
		visitTimeline.setComment("No comment");
		visitTimeline.setCreatedBy(this.userIdentity.getUserId());
		visitTimeline.setCreateDate(new GregorianCalendar().getTime());
		visitTimeline.setStatus(true);
		VisitTimeline visitT = this.visitTimelineBo.save(visitTimeline);
		this.visitTimelineBo.save(visitTimeline);
		this.visitBo.update(assignForm);
		auditor.after(request, "assignForm Form", assignForm,
				userIdentity.getUsername());
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				
				
		
				"Patient has been as assigned to "+unitCategory.getName()+ " unit");
		//return "redirect:/consultations/visits/configure/" + visit.getPatient().getPatientId();
		//return "consultations/visits/configure"; 
		 //return "redirect:/consultations/visits/configureindex";
		 return "redirect:/patients";
	}

	// Configure billing
	@RequestMapping(value = "/configurebill/{id}")
	@Layout("layouts/datatable")
	public String configurebillAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {
		Visit visit = this.visitBo.getVisitationById(id);
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/consultations/visits";
		}
		model.addAttribute("visitbill", visit);
		BillingForm billingForm = new BillingForm();
		billingForm.setVisitId(visit.getId());
		billingForm.setPaymentType(PaymentItemType.Service.toString());
		model.addAttribute("bForm", billingForm);
		VisitWorkflowUserConfigurationForm vwfUserConfigForm = new VisitWorkflowUserConfigurationForm();
		vwfUserConfigForm.setVisitId(visit.getId());
		// pass to front end
		model.addAttribute("sectionId", userIdentity.getSectionId());
		model.addAttribute("sectionName", userIdentity.getSectionName());
		model.addAttribute("billingId", userIdentity.getBillId());
		model.addAttribute("billingName", userIdentity.getBillName());
		model.addAttribute("currentPiointId", userIdentity.getCurrentPointId());
		model.addAttribute("currentPiointName",
				userIdentity.getCurrentPointName());
		// fetch item list
		int frontDesk = 8;
		VisitWorkflowPoint point = this.VWfPointBo
				.getWorkflowPointById(frontDesk);
		// get item by point
		List<VwItem> itemList = this.vwItemBo.fetchAllByPoint(point.getId());
		model.addAttribute("itemList", itemList);
		model.addAttribute("fid", id);
		model.addAttribute("dForm", vwfUserConfigForm);
		model.addAttribute("invoice", billingInvoiceBo.fetchAllByOrganisation());
		model.addAttribute("points", this.wfPointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		return "consultations/visits/configurebill";
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/configurebill", method = RequestMethod.POST)
	public String raiseInvoiceAction(
			@ModelAttribute("bForm") BillingForm bForm,
			RedirectAttributes redirectAttributes, Model model,
			BindingResult result) {
		/*
		 * if (result.hasErrors()) {
		 * 
		 * }
		 */
		this.billingInvoiceBo.raiseInvoice(bForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Invoce Raised Successfully");
		return "redirect:/consultations/visits/configurebill/"
				+ userIdentity.getVisitId();
	}

	// Menu billing
	@RequestMapping(value = "/configbill")
	@Layout("layouts/datatable")
	public String ConfigureBill(Model model,
			RedirectAttributes redirectAttributes) {
		List<Patient> patientList = patientBo.fetchAllByOrganisation();
		model.addAttribute("bForm", new BillingForm());
		model.addAttribute("pForm", new PaymentForm());
		model.addAttribute("patientList", patientList);
		model.addAttribute("invoice",
				this.billingInvoiceBo.fetchAllByOrganisation());
		return "consultations/visits/configbill";
	}

	// Menu billing submit
	@Layout("layouts/datatable")
	@RequestMapping(value = "/configbill", method = RequestMethod.POST)
	public String configbill(@ModelAttribute("bForm") BillingForm bForm,
			BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		/*
		 * if (result.hasErrors()) { return
		 * "redirect:/consultations/visits/configurebill"; }
		 */
		this.billingInvoiceBo.raiseInvoiceFromMenu(bForm);

		List<Patient> patientList = patientBo.fetchAllByOrganisation();
		model.addAttribute("bForm", new BillingForm());
		model.addAttribute("pForm", new PaymentForm());
		model.addAttribute("patientList", patientList);
		model.addAttribute("invoice",
				this.billingInvoiceBo.fetchAllByOrganisation());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Invoce Raised Successfully");
		return "consultations/visits/configbill";
	}

	// payment
	@SuppressWarnings("null")
	@Layout("layouts/datatable")
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String payment(@ModelAttribute("pForm") PaymentForm paymentForm,
			HttpServletRequest request, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		/* check for subservice */
		HmoSubserviceItem hmoSubserviceItem = hmoSubserviceItemBo
				.checksubservice(paymentForm.getpItem(),
						paymentForm.getPhmoPackage());
		/* check if patient want to use hmo */
		if (paymentForm.getPhmo() != null) {
			/* check if account exist in hmo balance table */
			HmoBalance hmoBalance = hmoBalanceBo.getHmoStatus(
					paymentForm.getpPatient(), paymentForm.getPhmoPackage(),
					hmoSubserviceItem.getSubserviceId());
			/* insufficient HMO balance */
			if (hmoBalance == null) {
				paymentLogBo.saveHmoBalance(paymentForm);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						paymentForm.getpItem() + " successfully paid for");
			} else {/* Sufficient HMO balance */
				auditor.before(request, "Payment Form", paymentForm);
				paymentLogBo.updateHmoBalance(paymentForm);
				auditor.after(request, "Payment Form", paymentForm,
						userIdentity.getUsername());
				alert.setAlert(redirectAttributes, Alert.SUCCESS, itemBo
						.getGlobalItemById(paymentForm.getpItem()).getName()
						+ " successfully paid for");
			}
		}
		/* not using hmo */
		else {
			paymentLogBo.saveHmoBalance(paymentForm);
			alert.setAlert(redirectAttributes, Alert.SUCCESS, itemBo
					.getGlobalItemById(paymentForm.getpItem()).getName()
					+ " successfully paid for");
		}
		List<Patient> patientList = patientBo.fetchAllByOrganisation();
		model.addAttribute("bForm", new BillingForm());
		model.addAttribute("pForm", new PaymentForm());
		model.addAttribute("patientList", patientList);
		model.addAttribute("invoice",
				this.billingInvoiceBo.fetchAllByOrganisation());

		return "consultations/visits/configbill";
	}

	// delete configure billing
	@RequestMapping(value = "/bill/delete/{id}")
	public String deleteBilling(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		BillingInvoice billingInvoice = this.billingInvoiceBo
				.getBillingInvoiceById(id);
		Visit visit = this.visitBo.getVisitationById(id);
		if (null == billingInvoice) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/consultations/visits/configurebill/"
					+ visit.getId();
		}
		BillingForm billingForm = new BillingForm();
		billingForm.setId(billingInvoice.getId());
		model.addAttribute("id", billingInvoice.getId());
		model.addAttribute("bill", billingInvoice);
		model.addAttribute("bForm", billingForm);
		return "consultations/visits/bill/delete";
	}

	// confirm configure delete
	@RequestMapping(value = "/bill/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("bForm") BillingForm billingForm,
			RedirectAttributes redirectAttributes) {
		BillingInvoice billingInvoice = this.billingInvoiceBo
				.getBillingInvoiceById(billingForm.getId());
		if (null == billingInvoice) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/consultations/visits/configurebill/"
					+ billingInvoice.getVisit().getId();
		}
		this.billingInvoiceBo.delete(billingInvoice);
		// bill.setDeleted(true);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Bill deleted!");
		return "redirect:/consultations/visits/configurebill/"
				+ billingInvoice.getVisit().getId();
	}

	// delete config bill
	@RequestMapping(value = "/bill/menu/delete/{id}")
	public String deleteConfigBill(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		BillingInvoice billingInvoice = this.billingInvoiceBo
				.getBillingInvoiceById(id);
		Visit visit = this.visitBo.getVisitationById(id);
		if (null == billingInvoice) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/consultations/visits/configurebill/"
					+ visit.getId();
		}
		BillingForm billingForm = new BillingForm();
		billingForm.setId(billingInvoice.getId());
		model.addAttribute("id", billingInvoice.getId());
		model.addAttribute("bill", billingInvoice);
		model.addAttribute("bForm", billingForm);
		return "consultations/visits/bill/menu/delete";
	}

	// confirm config bill delete
	@RequestMapping(value = "/bill/menu/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteConfigBill(
			@ModelAttribute("bForm") BillingForm billingForm,
			RedirectAttributes redirectAttributes) {
		BillingInvoice billingInvoice = this.billingInvoiceBo
				.getBillingInvoiceById(billingForm.getId());
		if (null == billingInvoice) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/consultations/visits/configbill";
		}
		this.billingInvoiceBo.delete(billingInvoice);
		// bill.setDeleted(true);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Bill deleted!");
		return "redirect:/consultations/visits/configbill";
	}

	/**
	 * fetch Point by Section|Arm
	 * **/
	//Shade
	/*
	@RequestMapping(value = "/request/point/{sectionId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getPointBySection(@PathVariable("sectionId") Integer Id) {
		String pointStr = "<option value='0'>Select Point</option>";
		LoginSection section = this.loginSectionBo.getLoginSectionById(Id);
		if (section == null) {
			return pointStr;
		}
		Set<VisitWorkflowPoint> wfPionts = section.getVwfwPoint();
		for (VisitWorkflowPoint wfPoint : wfPionts) {
			pointStr += "<option value='" + wfPoint.getId() + "'>"
					+ wfPoint.getName() + "</option>";
		}
		return pointStr;
	}*/
	
	/**
	 * fetch Department by Section|Arm
	 * **/
	/*@RequestMapping(value = "/request/department/{sectionId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getDepartmentBySection(@PathVariable("sectionId") Integer Id) {
		String pointStr = "<option value='0'>Select Point</option>";
		LoginSection section = this.loginSectionBo.getLoginSectionById(Id);
		if (section == null) {
			return pointStr;
		}
		
		return pointStr;
	}*/
	
	/**
	 * fetch Users by unit
	 * **/
	@RequestMapping(value = "/request/user/{unitId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getUserByUnit(@PathVariable("unitId") int Id) {
		System.out.println("unit dropdown onchange");
		String userStr = "<option value='0'>Select User</option>";
		//UnitList uList = this.unitListBo.getUnitListById(Id);
		List<Clockin> clock = this.clockinBo.fetchAllByUnitId(Id);
		System.out.println("clock size"+clock.size());
		if (clock == null) {
			return userStr;
		}
		//List<Clockin> clockin = this.clockinBo.fetchAllByUnitId(Id);
		//System.out.println("clock size"+clock.size());
		for (Clockin clk : clock) {
			userStr += "<option value='" + clk.getUserId() + "'>"
					+ clk.getUserName()+"</option>";
		System.out.println("user string"+ userStr);
		}
		return userStr;
	}
	
	/**
	 * fetch Department by Section|Arm
	 * **/
	/*@RequestMapping(value = "/request/unit/{departmentId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getUnitByDepartment(@PathVariable("departmentId") Integer Id) {
		String pointStr = "<option value='0'>Select unit</option>";
		Department department = this.departmentBo.getDeptById(Id);
		if (department == null) {
			return pointStr;
		}
		Set<Unit> units = department.getUnit();
		for (Unit depts : units) {
			pointStr += "<option value='" + depts.getId() + "'>"
					+ depts.getUnitName() + "</option>";
		}
		return pointStr;
	}*/


	/**
	 * fetch user by point
	 * **/
	/*@RequestMapping(value = "/request/user/{pointId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getUserByPoint(@PathVariable("pointId") Integer Id) {
		String userStr = "<option value='0'>Select User</option>";
		VisitWorkflowPoint vwfPoint = this.VWfPointBo.getWorkflowPointById(Id);
		if (vwfPoint == null) {
			return userStr;
		}
		Set<User> users = vwfPoint.getPointUsers();
		for (User user : users) {
			userStr += "<option value='" + user.getUserId() + "'>"
					+ user.getUserProfile().getLastName() + "</option>";
		}
		return userStr;
	}*/

	/**
	 * Workflow Point Restfully service
	 * 
	 * @param form
	 * @param result
	 * @return
	 */

	/*@ResponseBody
	@RequestMapping(value = "/workflowusers/{id}")
	public String workflowPointUsersAction(@PathVariable("id") Integer id) {
		String response = null;
		VisitWorkflowPoint point = this.wfPointBo.getWorkflowPointById(id);
		if (null == point) {
			response = "<option value=''>No Staff</option>";
			return response;
		}

		response = "";
		if (point.getPointUsers().size() > 0)
			for (User user : point.getPointUsers()) {
				response += "<option value='" + user.getUserId() + "'>"
						+ user.getUserProfile().getTitle().getAcronym() + " "
						+ user.getUserProfile().getLastName() + " "
						+ user.getUserProfile().getOtherNames() + "</option>";
			}
		else {
			response = "<option value=''>No Staff</option>";
			return response;
		}

		return response;
	}*/

	@RequestMapping(value = "/assigntopoint", method = RequestMethod.POST)
	public String pointAssignAction(
			@Valid @ModelAttribute("wfForm") VisitWorkflowUserConfigurationForm form,
			BindingResult result, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			// If the form has errors. Since the form errors cant be display at
			// point because method will be used my different points. Throw an
			// alert and make a callback to the url that made the request.
			// String callbackUrl as the application context removed from the
			// URI to reable redirect to function properly
			String callbackUrl = request.getRequestURI().substring(
					request.getContextPath().length());

			this.alert
					.setAlert(
							redirectAttributes,
							Alert.ERROR,
							"Error!. Could not transfer patient to point. "
									+ "Ensure that the assignment form is filled correctly");
			return "redirect:" + callbackUrl;
		}

		this.visitBo.assignUserToPoint(form);

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Patient assigned to new point!");

		return "redirect:/consultations/visits";
	}

	@RequestMapping(value = "/close/{id}")
	public String closeVisitAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Visit visit = this.visitBo.getVisitationById(id);
		VisitStatus status = this.visitStatusBo.getEndPointStatus();

		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resources");
			return "redirect:/consultations/visits";
		}

		if (null == status) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Visit Endpoint not set. Contact Admin");
			return "redirect:/consultations/visits";
		}

		if (visit.getStatus().getId() == status.getId()) {
			this.alert
					.setAlert(redirectAttributes, Alert.DANGER,
							"Visit Ended already. If You keep getting this message, contact admin");
			return "redirect:/consultations/visits";
		}

		VisitCloseForm form = new VisitCloseForm();
		form.setVisitId(visit.getId());
		//form.setConsultationId(visit.getConsultation().getId());

		// Get the consultation status minus the start point
		List<ConsultationStatus> consultationStatusList = this.consultationStatusBo
				.fetchAllByOrganisation();
		ConsultationStatus startPoint = this.consultationStatusBo
				.getStartPointStatus();
		List<ConsultationStatus> newList = new ArrayList<ConsultationStatus>();

		if (null == startPoint) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Consultation start point not set. Contact Admin");
		}

		for (ConsultationStatus cStatus : consultationStatusList)
			if (cStatus.getId() != startPoint.getId())
				newList.add(cStatus);

		model.addAttribute("visit", visit);
		model.addAttribute("dForm", form);
		model.addAttribute("cStatuses", newList);

		return "consultations/visits/close";
	}

	@RequestMapping(value = "/close/{id}", method = RequestMethod.POST)
	public String closeVisitAction(
			@Valid @ModelAttribute("dForm") VisitCloseForm form,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			// Get the consultation status minus the start point
			List<ConsultationStatus> consultationStatusList = this.consultationStatusBo
					.fetchAllByOrganisation();
			ConsultationStatus startPoint = this.consultationStatusBo
					.getStartPointStatus();
			List<ConsultationStatus> newList = new ArrayList<ConsultationStatus>();
			model.addAttribute("cStatuses", newList);
		}

		Visit visit = this.visitBo.getVisitationById(form.getVisitId());
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resource");
			return "redirect:/consultations/visits";
		}

		Consultation consultation = this.consultationBo
				.getConsultationById(form.getConsultationId());
		if (null == consultation) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resource");
			return "redirect:/consultations/visits";
		}

		VisitStatus status = this.visitStatusBo.getEndPointStatus();
		visit.setStatus(status);

		ConsultationStatus cStatus = this.consultationStatusBo
				.getStatusById(form.getConsultationStatus());
		consultation.setStatus(cStatus);

		//visit.setConsultation(consultation);

		// Update the visit and consultation
		this.visitBo.update(visit);

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Visit was closed successfully.");
		return "redirect:/consultations/visits";
	}

	@RequestMapping(value = "/reschedule/{id}/{initiator}")
	public String rescheduleAction(@PathVariable("id") Integer id,
			@PathVariable("initiator") String initiator, Model model,
			RedirectAttributes redirectAttributes) {
		// Get the visit...
		Visit visit = this.visitBo.getVisitationById(id);
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid resource");
			return "redirect:/consultations/visits";
		}

		RescheduleVisitForm rVisitForm = new RescheduleVisitForm();
		rVisitForm.setVisitId(visit.getId());
		rVisitForm.setInitiator(initiator);

		if (visit.getRescheduledVisit() != null) {
			rVisitForm.setReason(visit.getRescheduledVisit().getReason());
			rVisitForm.setRescheduleDate(DateUtils.formatDateToString(visit
					.getRescheduledVisit().getScheduledDate()));
		}

		model.addAttribute("visit", visit);
		model.addAttribute("dForm", rVisitForm);

		return "consultations/visits/reschedule";
	}

	@RequestMapping(value = "/reschedule/{id}/{initiator}", method = RequestMethod.POST)
	public String saveScheduledAction(
			@Valid @ModelAttribute("dForm") RescheduleVisitForm rVisitForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			if (null == rVisitForm.getVisitId()) {
				this.alert.setAlert(redirectAttributes, Alert.DANGER,
						"Invalid resource");
				return "redirect:/consultations/visits";
			}
			Visit visit = this.visitBo.getVisitationById(rVisitForm
					.getVisitId());
			model.addAttribute("visit", visit);
			return "consultations/visits/reschedule";
		}

		Visit visit = this.visitBo.getVisitationById(rVisitForm.getVisitId());
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid resource");
			return "redirect:/consultations/visits";
		}

		this.rescheduledVisitBo.save(rVisitForm);

		return "redirect:/consultations/visits/" + rVisitForm.getInitiator()
				+ "/" + rVisitForm.getVisitId();
	}

}
