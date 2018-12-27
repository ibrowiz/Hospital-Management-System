package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.consultation.forms.BillingForm;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.system.boInterface.BillingInvoiceBo;
import org.calminfotech.system.boInterface.LoginSectionBo;
import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.daoInterface.BillingInvoiceDao;
import org.calminfotech.system.models.BillingInvoice;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.views.boInterface.VwItemBo;
import org.calminfotech.views.models.VwItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingInvoiceBoImpl implements BillingInvoiceBo {

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private BillingInvoiceDao billingInvoiceDao;
	
	@Autowired
	private VisitBo visitBo;
	
	@Autowired
	private LoginSectionBo sectionBo;
	
	@Autowired
	private VisitWorkflowPointBo pointBo;
	
	@Autowired
	private VwItemBo itemBo;
	
	@Autowired
	private PatientBo patientBo;
	
	@Autowired
	private BillingSchemeBo paymentSchemeBo;
	
	@Override
	public List<BillingInvoice> fetchAll() {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchAll();
	}

	@Override
	public List<BillingInvoice> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchAllByOrganisation(this.userIdentity.getOrganisation());
	}

	@Override
	public void save(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.billingInvoiceDao.save(billingInvoice);
	}

	@Override
	public void delete(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.billingInvoiceDao.delete(billingInvoice);
	}

	@Override
	public void update(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.billingInvoiceDao.update(billingInvoice);
	}

	@Override
	public BillingInvoice getBillingInvoiceById(int id) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.getBillingInvoiceById(id);
	}

	@Override
	public void raiseInvoice(BillingForm billingForm) {
		// TODO Auto-generated method stub
		BillingInvoice billingInvoice = new BillingInvoice();
		LoginSection section = this.sectionBo.getLoginSectionById(userIdentity.getSectionId());
		billingInvoice.setSection(section);
		billingInvoice.setBilling(userIdentity.getBillId());
		VisitWorkflowPoint point = this.pointBo.getWorkflowPointById(userIdentity.getCurrentPointId());
		billingInvoice.setPoint(point);
		Visit visit = this.visitBo.getVisitationById(userIdentity.getVisitId());
		billingInvoice.setVisit(visit);
		billingInvoice.setPatient(visit.getPatient());
		VwItem item = this.itemBo.getVwItemById(billingForm.getItem());
		billingInvoice.setItem(item);
		billingInvoice.setQuantity(billingForm.getItemType());
		billingInvoice.setUnitId(billingForm.getUnitofmeasure());
		if(billingForm.getPrice() == null){
			billingInvoice.setPrice(0.00);
		}else{
			billingInvoice.setPrice(billingForm.getPrice());
		}
		billingInvoice.setStatus("Yet to pay");
		billingInvoice.setOrganisation(userIdentity.getOrganisation());
		billingInvoice.setCreatedby(userIdentity.getUsername());
		billingInvoice.setDeleted(false);
		this.billingInvoiceDao.save(billingInvoice);
		//flush the util bean
		/*userIdentity.setBillId(null);
		userIdentity.setBillName(null);
		userIdentity.setCurrentPointId(null);
		userIdentity.setCurrentPointName(null);*/
	}
	//save by raising bill only from menu
	@Override
	public void raiseInvoiceFromMenu(BillingForm billingForm) {
		// TODO Auto-generated method stub
		BillingInvoice billingInvoice = new BillingInvoice();
		billingInvoice.setPatient(patientBo.getPatientById(billingForm.getPatientId()));
		billingInvoice.setSection(sectionBo.getLoginSectionById(billingForm.getSectionId()));
		billingInvoice.setBilling(paymentSchemeBo.getBillingSchemeById(sectionBo.getLoginSectionById(billingForm.getSectionId()).getPscheme().getId()).getId());
		billingInvoice.setPoint(pointBo.getWorkflowPointById(billingForm.getCurrentPiointId()));
		billingInvoice.setVisit(visitBo.getVisitationById(billingForm.getVisitId()));
		billingInvoice.setItem(this.itemBo.getVwItemById(billingForm.getItem()));
		billingInvoice.setQuantity(billingForm.getItemType());
		billingInvoice.setUnitId(billingForm.getUnitofmeasure());
		//save cost amount
		if(billingForm.getPrice() == null ){
			billingInvoice.setPrice(0.00);
		}else{
			billingInvoice.setPrice(billingForm.getPrice());
		}
		//expected payment
		billingInvoice.setAmountPaid(0.00);
		billingInvoice.setStatus("0%");
		billingInvoice.setOrganisation(userIdentity.getOrganisation());
		billingInvoice.setCreatedby(userIdentity.getUsername());
		billingInvoice.setDeleted(false);
		billingInvoiceDao.save(billingInvoice);
	}

}
