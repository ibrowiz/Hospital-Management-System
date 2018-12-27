package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.consultation.forms.BillingForm;
import org.calminfotech.system.models.BillingInvoice;

public interface BillingInvoiceBo {

	List<BillingInvoice> fetchAll();
	List<BillingInvoice> fetchAllByOrganisation();
	void save(BillingInvoice billingInvoicex);
	void delete(BillingInvoice billingInvoice);
	void update(BillingInvoice billingInvoice);
	public BillingInvoice getBillingInvoiceById(int id);
	void raiseInvoice(BillingForm billingForm);
	void raiseInvoiceFromMenu(BillingForm billingForm);
}