package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.consultation.forms.PaymentForm;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.system.boInterface.BillingInvoiceBo;
import org.calminfotech.system.boInterface.HmoBalanceBo;
import org.calminfotech.system.boInterface.HmoSubserviceItemBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.boInterface.PaymentLogBo;
import org.calminfotech.system.daoInterface.PaymentLogDao;
import org.calminfotech.system.models.BillingInvoice;
import org.calminfotech.system.models.HmoBalance;
import org.calminfotech.system.models.HmoSubserviceItem;
import org.calminfotech.system.models.PaymentLog;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Converter;
import org.calminfotech.utils.PercentageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("static-access")
@Service
public class PaymentLogBoImpl implements PaymentLogBo {

	@Autowired
	private PaymentLogDao paymentLogDao;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private HmoBalanceBo hmoBalanceBo;
	
	@Autowired
	private PatientBo patientBo;
	
	@Autowired
	private EHmoPackagesBo hmoPackagesBo;

	@Autowired
	private HmoSubserviceItemBo hmoSubserviceItemBo;
	
	@Autowired
	private BillingInvoiceBo billingInvoiceBo;	

	@Override
	public List<PaymentLog> fetchAll() {
		// TODO Auto-generated method stub
		return paymentLogDao.fetchAll();
	}

	@Override
	public List<PaymentLog> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return paymentLogDao.fetchAllByOrganisation(userIdentity.getOrganisation());
	}

	@Override
	public void save(PaymentLog paymentLog) {
		// TODO Auto-generated method stub
		paymentLogDao.save(paymentLog);
	}

	@Override
	public void delete(PaymentLog paymentLog) {
		// TODO Auto-generated method stub
		paymentLogDao.delete(paymentLog);
	}

	@Override
	public void update(PaymentLog paymentLog) {
		// TODO Auto-generated method stub
		paymentLogDao.update(paymentLog);
	}

	@Override
	public PaymentLog getPaymentLogById(int id) {
		// TODO Auto-generated method stub
		return paymentLogDao.getPaymentLogById(id);
	}

	@Override
	public void updateHmoBalance(PaymentForm paymentForm) {
		// TODO Auto-generated method stub
		/*check for subservice*/
		HmoSubserviceItem hmoSubserviceItem = hmoSubserviceItemBo.checksubservice(paymentForm.getpItem(), paymentForm.getPhmoPackage());
		Converter convert = new Converter();
		PercentageConverter prcnt = new PercentageConverter();
		//billing
		BillingInvoice bill = billingInvoiceBo.getBillingInvoiceById(paymentForm.getId());
		//log payment
		PaymentLog paymentLog = new PaymentLog();
		paymentLog.setBillingId(billingInvoiceBo.getBillingInvoiceById(paymentForm.getId()));
		paymentLog.setAmount(convert.toDouble(paymentForm.getPhmoAmount()));
		paymentLog.setPaymentType(paymentLog.HMO_PAYMENT_TYPE);
		paymentLog.setPackageId(hmoPackagesBo.getPackageById(paymentForm.getPhmoPackage()));
		paymentLog.setSubservice(hmoSubserviceItem.getSubserviceId());
		paymentLog.setOrganisation(userIdentity.getOrganisation());
		paymentLog.setCreatedBy(userIdentity.getUsername());
		paymentLogDao.save(paymentLog);
		//save for cash
		if(paymentForm.getpCashAmount() != null){
			//log payment
			PaymentLog paymentLog1 = new PaymentLog();
			paymentLog1.setBillingId(billingInvoiceBo.getBillingInvoiceById(paymentForm.getId()));
			paymentLog1.setAmount(convert.toDouble(paymentForm.getpCashAmount()));
			paymentLog1.setPaymentType(paymentLog.DIRECT_PAYMENT_TYPE);
			paymentLog1.setPackageId(hmoPackagesBo.getPackageById(paymentForm.getPhmoPackage()));
			paymentLog1.setSubservice(hmoSubserviceItem.getSubserviceId());
			paymentLog1.setOrganisation(userIdentity.getOrganisation());
			paymentLog1.setCreatedBy(userIdentity.getUsername());
			paymentLogDao.save(paymentLog1);
		}
		//save for ATM
		if(paymentForm.getpAtmAmount() != null){
			//log payment
			PaymentLog paymentLog2 = new PaymentLog();
			paymentLog2.setBillingId(billingInvoiceBo.getBillingInvoiceById(paymentForm.getId()));
			paymentLog2.setAmount(convert.toDouble(paymentForm.getpAtmAmount()));
			paymentLog2.setPaymentType(paymentLog.ATM_PAYMENT_TYPE);
			paymentLog2.setPackageId(hmoPackagesBo.getPackageById(paymentForm.getPhmoPackage()));
			paymentLog2.setSubservice(hmoSubserviceItem.getSubserviceId());
			paymentLog2.setOrganisation(userIdentity.getOrganisation());
			paymentLog2.setCreatedBy(userIdentity.getUsername());
			paymentLogDao.save(paymentLog2);
		}
		// hmo balance
		HmoBalance hmoBalance = hmoBalanceBo.getHmoStatus(paymentForm.getpPatient(), paymentForm.getPhmoPackage(), hmoSubserviceItem.getSubserviceId());
		double initBalance = hmoBalance.getBalance();
		hmoBalance.setBalance(convert.toDouble(paymentForm.getPhmoAmount()) + initBalance);
		hmoBalance.setModifiedBy(userIdentity.getUsername());
		hmoBalanceBo.update(hmoBalance);
		//update billing
		bill.setAmountPaid(convert.toDouble(paymentForm.getPhmoAmount() + paymentForm.getpAtmAmount() + paymentForm.getpCashAmount()));
		bill.setStatus(prcnt.percent(convert.toDouble(paymentForm.getPhmoAmount() + paymentForm.getpAtmAmount() + paymentForm.getpCashAmount()), bill.getPrice()));
		billingInvoiceBo.update(bill);
	}

	@Override
	public void saveHmoBalance(PaymentForm paymentForm) {
		/*check for subservice*/
		HmoSubserviceItem hmoSubserviceItem = hmoSubserviceItemBo.checksubservice(paymentForm.getpItem(), paymentForm.getPhmoPackage());
		Converter convert = new Converter();
		PercentageConverter prcnt = new PercentageConverter();
		//billing
		BillingInvoice bill = billingInvoiceBo.getBillingInvoiceById(paymentForm.getId());
		//log payment
		PaymentLog paymentLog = new PaymentLog();
		paymentLog.setBillingId(billingInvoiceBo.getBillingInvoiceById(paymentForm.getId()));
		paymentLog.setAmount(convert.toDouble(paymentForm.getPhmoAmount()));
		paymentLog.setPaymentType(paymentLog.HMO_PAYMENT_TYPE);
		paymentLog.setPackageId(hmoPackagesBo.getPackageById(paymentForm.getPhmoPackage()));
		paymentLog.setSubservice(hmoSubserviceItem.getSubserviceId());
		paymentLog.setOrganisation(userIdentity.getOrganisation());
		paymentLog.setCreatedBy(userIdentity.getUsername());
		paymentLogDao.save(paymentLog);
		//save for cash
		if(paymentForm.getpCashAmount() != null){
			//log payment
			PaymentLog paymentLog1 = new PaymentLog();
			paymentLog1.setBillingId(billingInvoiceBo.getBillingInvoiceById(paymentForm.getId()));
			paymentLog1.setAmount(convert.toDouble(paymentForm.getpCashAmount()));
			paymentLog1.setPaymentType(paymentLog.DIRECT_PAYMENT_TYPE);
			paymentLog1.setPackageId(hmoPackagesBo.getPackageById(paymentForm.getPhmoPackage()));
			paymentLog1.setSubservice(hmoSubserviceItem.getSubserviceId());
			paymentLog1.setOrganisation(userIdentity.getOrganisation());
			paymentLog1.setCreatedBy(userIdentity.getUsername());
			paymentLogDao.save(paymentLog1);
		}
		// hmo balance
		HmoBalance hmoBalance = new HmoBalance();
		hmoBalance.setPatientId(patientBo.getPatientById(paymentForm.getpPatient()));
		hmoBalance.setPackageId(hmoPackagesBo.getPackageById(paymentForm.getPhmoPackage()));
		hmoBalance.setSubservice(hmoSubserviceItem.getSubserviceId());
		hmoBalance.setBalance(convert.toDouble(paymentForm.getpCashAmount()));
		hmoBalance.setOrganisation(userIdentity.getOrganisation());
		hmoBalance.setCreatedBy(userIdentity.getUsername());
		hmoBalanceBo.save(hmoBalance);
		//save for ATM
		if(paymentForm.getpAtmAmount() != null){
			//log payment
			PaymentLog paymentLog2 = new PaymentLog();
			paymentLog2.setBillingId(billingInvoiceBo.getBillingInvoiceById(paymentForm.getId()));
			paymentLog2.setAmount(convert.toDouble(paymentForm.getPhmoAmount()));
			paymentLog2.setPaymentType(paymentLog.ATM_PAYMENT_TYPE);
			paymentLog2.setPackageId(hmoPackagesBo.getPackageById(paymentForm.getPhmoPackage()));
			paymentLog2.setSubservice(hmoSubserviceItem.getSubserviceId());
			paymentLog2.setOrganisation(userIdentity.getOrganisation());
			paymentLog2.setCreatedBy(userIdentity.getUsername());
			paymentLogDao.save(paymentLog2);
		}
		//update billing
		double paid = (double)(paymentForm.getPhmoAmount() + paymentForm.getpAtmAmount() + paymentForm.getpCashAmount());
		bill.setAmountPaid(paid);
		bill.setStatus(prcnt.percent(convert.toDouble(paymentForm.getPhmoAmount())+convert.toDouble(paymentForm.getpAtmAmount())+convert.toDouble(paymentForm.getpCashAmount()),bill.getPrice()));
		billingInvoiceBo.update(bill);
	}

	@Override
	public void noHmoBalance(PaymentForm paymentForm) {
		/*check for subservice*/
		HmoSubserviceItem hmoSubserviceItem = hmoSubserviceItemBo.checksubservice(paymentForm.getpItem(), paymentForm.getPhmoPackage());
		Converter convert = new Converter();
		PercentageConverter prcnt = new PercentageConverter();
		//billing
		BillingInvoice bill = billingInvoiceBo.getBillingInvoiceById(paymentForm.getId());
		//log payment
		PaymentLog paymentLog = new PaymentLog();
		//save for cash
		if(paymentForm.getpCashAmount() != null){
			paymentLog.setBillingId(billingInvoiceBo.getBillingInvoiceById(paymentForm.getId()));
			paymentLog.setAmount(convert.toDouble(paymentForm.getpCashAmount()));
			paymentLog.setPaymentType(paymentLog.DIRECT_PAYMENT_TYPE);
			paymentLog.setPackageId(hmoPackagesBo.getPackageById(paymentForm.getPhmoPackage()));
			paymentLog.setSubservice(hmoSubserviceItem.getSubserviceId());
			paymentLog.setOrganisation(userIdentity.getOrganisation());
			paymentLog.setCreatedBy(userIdentity.getUsername());
			paymentLogDao.save(paymentLog);
		}
		//save for ATM
		if(paymentForm.getpAtmAmount() != null){
			//log payment
			PaymentLog paymentLog1 = new PaymentLog();
			paymentLog1.setBillingId(billingInvoiceBo.getBillingInvoiceById(paymentForm.getId()));
			paymentLog1.setAmount(convert.toDouble(paymentForm.getpAtmAmount()));
			paymentLog1.setPaymentType(paymentLog.ATM_PAYMENT_TYPE);
			paymentLog1.setPackageId(hmoPackagesBo.getPackageById(paymentForm.getPhmoPackage()));
			paymentLog1.setSubservice(hmoSubserviceItem.getSubserviceId());
			paymentLog1.setOrganisation(userIdentity.getOrganisation());
			paymentLog1.setCreatedBy(userIdentity.getUsername());
			paymentLogDao.save(paymentLog1);
		}
		//update billing
		bill.setAmountPaid(convert.toDouble(paymentForm.getPhmoAmount() + paymentForm.getpAtmAmount() + paymentForm.getpCashAmount()));
		bill.setStatus(prcnt.percent(convert.toDouble(paymentForm.getPhmoAmount() + paymentForm.getpAtmAmount() + paymentForm.getpCashAmount()), bill.getPrice()));
		billingInvoiceBo.update(bill);
	}

}
