package org.calminfotech.utils.controllers;

import java.util.List;
import java.util.Set;

import org.calminfotech.admin.boInterface.HmoBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientHmoPackageBo;
import org.calminfotech.patient.models.PatientHmoPackage;
import org.calminfotech.system.boInterface.HmoBalanceBo;
import org.calminfotech.system.boInterface.HmoPackageItemsBo;
import org.calminfotech.system.boInterface.HmoSubserviceItemBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.models.HmoBalance;
import org.calminfotech.system.models.HmoPackageItems;
import org.calminfotech.system.models.HmoSubserviceItem;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.system.models.BillingSchemeItem;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Converter;
import org.calminfotech.utils.PaymentSchemeItemList;
import org.calminfotech.utils.PaymentSchemeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/utilities/payment")
public class PaymentSchemenItemController {

	@Autowired
	private PaymentSchemeList paymentSchemeList;
	@Autowired
	private PaymentSchemeItemList paymentSchemeItemList;
	@Autowired
	private PatientHmoPackageBo patientHmoPackageBo;
	@Autowired
	private PatientBo patientBo;
	@Autowired
	private EHmoPackagesBo hmoPackagesBo;
	@Autowired
	private HmoBo hmoBo;
	@Autowired
	private HmoSubserviceItemBo hmoSubserviceItemBo;
	@Autowired
	private HmoPackageItemsBo hmoPackageItemsBo;
	@Autowired
	private HmoBalanceBo hmoBalanceBo;
	@Autowired
	private UserIdentity userIdentity; 

	@RequestMapping(value = "/paymentscheme", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getPScheme() {
		String PSchStr = "<option value='0'>Select Payment Scheme</option>";
		List<BillingScheme> pschemeList = paymentSchemeList.fetchAll();
		for (BillingScheme psch : pschemeList) {
			PSchStr += "<option value='" + psch.getPaymentSchemeItem() + "'>"
					+ psch.getName() + "</option>";
		}
		return PSchStr;
	}

	@RequestMapping(value = "/schemeitem", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getSchemeItems() {
		String ItemStr = "<option value='0'>Select Scheme Items</option>";
		List<BillingSchemeItem> list = paymentSchemeItemList.fetchAll();
		for (BillingSchemeItem sitem : list) {
			ItemStr += "<option value='" + sitem.getId() + "'>"
					+ sitem.getGlobalItem().getName() + "</option>";
		}
		return ItemStr;
	}

	@RequestMapping(value = "/paymentscheme/id/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getitemByscheme(@PathVariable("id") Integer id) {
		String ItemStr = "<option value='0'>Select Item</option>";

		BillingScheme pscheme = paymentSchemeList.getPaymentSchemeById(id);

		if (pscheme == null)
			return ItemStr;

		Set<BillingSchemeItem> list = pscheme.getPaymentSchemeItem();

		for (BillingSchemeItem item : list) {
			ItemStr += "<option value='" + item.getId() + "'>"
					+ item.getGlobalItem().getName() + "</option>";
		}
		return ItemStr;
	}

	@RequestMapping(value = "/itembyscheme/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getitembyschemeId(@PathVariable("id") Integer id) {
		String ItemStr = "<option value='0'>Select Item</option>";

		BillingScheme paymentScheme = paymentSchemeList
				.getPaymentSchemeById(id);

		if (paymentScheme == null)
			return ItemStr;

		Set<BillingSchemeItem> list = paymentScheme.getPaymentSchemeItem();

		for (BillingSchemeItem psitem : list) {
			ItemStr += "<option value='" + psitem.getId() + "'>"
					+ psitem.getGlobalItem().getName() + "</option>";
		}
		return ItemStr;
	}

	// get hmo
	@RequestMapping(value = "/hmos/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String fetchHMOsSubscripedbyPatient(@PathVariable("id") Integer id) {
		String hmoStr = "<option value='0'>Select HMO</option>";
		List<PatientHmoPackage> hmokList = patientHmoPackageBo
				.fetchAllByPatient(patientBo.getPatientById(id));
		for (PatientHmoPackage hmo : hmokList) {
			hmoStr += "<option value='" + hmo.getPk().getHmo().getId() + "'>"
					+ hmo.getPk().getHmo().getName() + "</option>";
		}
		return hmoStr;
	}

	// get hmo package
	@RequestMapping(value = "/hmos/packages/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String fetchPackagebyHMOId(@PathVariable("id") Integer id) {
		String packStr = "<option value='0'>Select Package</option>";
		List<EhmoPackages> packList = hmoPackagesBo
				.fetchAllByHMO(hmoBo.getHmoById(id));
		for (EhmoPackages pack : packList) {
			packStr += "<option value='" + pack.getId() + "'>" + pack.getName()
					+ "</option>";
		}
		return packStr;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "patient/{patient}/item/{item}/package/{package}/amount/{amount}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String checkSubservice(@PathVariable("patient") Integer patientId,
			@PathVariable("item") Integer itemId,
			@PathVariable("package") Integer packageId,@PathVariable("amount") Integer amount) {
		Converter convert = new Converter();
		//check for subservice
		HmoSubserviceItem hmoSubserviceItem = hmoSubserviceItemBo.checksubservice(itemId, packageId);
		//if package is null
		if( hmoSubserviceItem == null ){
			return "You don't have HMO facility. ";
		}
		//check spending limit
		HmoPackageItems hmoPackageItems = hmoPackageItemsBo.getSpendingLimit(packageId, hmoSubserviceItem.getSubserviceId());
		//if spending limit is null
		if( hmoPackageItems == null ){
			return "You don't have enough money. ";
		}
		//get hmoBalance
		HmoBalance hmoBalance = hmoBalanceBo.getHmoStatus(patientId, packageId, hmoSubserviceItem.getSubserviceId());
		//check if HMO is registered
		if((hmoBalance == null) && ( convert.toDouble(amount) < hmoPackageItems.getSpendingLimit())){
			return "null";
		}else if ((hmoBalance == null) && ( convert.toDouble(amount) > hmoPackageItems.getSpendingLimit())){
		//No record in hmo balance and input amount excceed spending limit
			double returnVal = (hmoPackageItems.getSpendingLimit() > 0 ) ? hmoPackageItems.getSpendingLimit() : 0.00;
			//return "Insufficient Balance. Your spending limit is N"+hmoPackageItems.getSpendingLimit();
			return "Insufficient Balance. Your spending limit is N"+returnVal;
		}else if ((hmoBalance != null) && ( convert.toDouble(amount) < (hmoPackageItems.getSpendingLimit() - hmoBalance.getBalance()))){
			return "null"; 
		}else if ((hmoBalance != null) && ( convert.toDouble(amount) > (hmoPackageItems.getSpendingLimit() - hmoBalance.getBalance()))){
			//Record found in hmo balance and input amount excceed spending limit
			//return "Insufficient Balance. Your balance is N"+(hmoPackageItems.getSpendingLimit() - hmoBalance.getBalance());
			double returnval  = ((hmoPackageItems.getSpendingLimit() - hmoBalance.getBalance()) > 0 ) ? (hmoPackageItems.getSpendingLimit() - hmoBalance.getBalance()) : 0.00;
			return "Insufficient Balance. Your balance is N"+ returnval ;
		}
		
		return "null";
	}
}
