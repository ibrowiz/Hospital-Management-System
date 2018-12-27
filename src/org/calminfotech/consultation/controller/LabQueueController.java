package org.calminfotech.consultation.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.consultation.bo.ConsultationBo;
import org.calminfotech.consultation.bo.RescheduledVisitBo;
import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.consultation.forms.SearchQueueForm;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.patient.boInterface.PatientBo;
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
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.views.boInterface.VwItemBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/consultations/labqueue")
public class LabQueueController {

	@Autowired
	private RescheduledVisitBo rescheduledVisitBo;

	@Autowired
	private Auditor auditor;

	@Autowired
	private VisitBo visitBo;

	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;

	@Autowired
	private ConsultationBo consultationBo;

	@Autowired
	private VisitWorkflowPointBo wfPointBo;

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
	private GlobalItemPointBo globalItemPointBo;

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

	@RequestMapping(value = "/searchqueue")
	@Layout(value = "layouts/form_wizard_layout")
	public String searchAction(
			@Valid @ModelAttribute("searchQueueForm") SearchQueueForm searchQueueForm,
			BindingResult result,Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
		Date date = new Date();
		//date.toString()
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(date);
		System.out.println(date);
		
		searchQueueForm.setFrom(formattedDate);
		searchQueueForm.setTo(formattedDate);
		searchQueueForm.setMstatus(2);
		model.addAttribute("statusList",  visitStatusBo.fetchAll());
		model.addAttribute("searchQueueForm",searchQueueForm);
		System.out.println(userIdentity.getUserId());
		return "consultations/visits/searchqueue";
		
	}

	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/searchqueueresult1")
	public String Labqueue(Model model,@Valid @ModelAttribute("searchQueueForm") SearchQueueForm searchQueueForm,
			BindingResult result,
			RedirectAttributes redirectAttributes) throws java.text.ParseException {
		
		
	//int uid =userIdentity.getUserId();
		/*if (searchQueueForm.getChkothers()!= null)
				{
			
			//uid = (Integer) null;
			uid = 50;
				}*/
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateFrom = searchQueueForm.getFrom();
		String dateTo = searchQueueForm.getTo();
		try {
			

			Date date1 = formatter.parse(dateFrom);
			Date date2 = formatter.parse(dateTo);
			model.addAttribute("queue", this.visitBo.fetchAllByThese(userIdentity.getUserId(), date1,date2, searchQueueForm.getMstatus(), searchQueueForm.getChkothers()));
			System.out.println("iam here");
			
			System.out.println(userIdentity.getUserId());
			System.out.println(searchQueueForm.getFrom());
			System.out.println(searchQueueForm.getTo());
			System.out.println("value of open her");
			System.out.println(searchQueueForm.getMstatus());
			System.out.println(searchQueueForm.getChkothers());
			System.out.println("value of open stop");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "consultations/visits/searchqueueresult";
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/viewtest/{vid}")
	public String viewTest(@PathVariable("vid") Integer vid,Model model){
		
		Visit g =this.visitBo.getVisitationById(vid);
		 model.addAttribute("queue", g);
		return "consultations/visits/viewtest";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/searchqueueresult")
	public String indexLab(Model model) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
		//model.addAttribute("pForm", new PatientForm());
		//model.addAttribute("searchForm", new SearchQueueForm());
		model.addAttribute("queue", this.visitBo.fetchAllByUId(userIdentity.getUserId()));
		return "consultations/visits/searchqueueresult";
	}
	
}
