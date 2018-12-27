package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.BillingInvoice;
import org.calminfotech.utils.models.Organisation;

public interface BillingInvoiceDao {

	List<BillingInvoice> fetchAll();
	List<BillingInvoice> fetchAllByOrganisation(Organisation organisation);
	void save(BillingInvoice billingInvoice);
	void delete(BillingInvoice billingInvoice);
	void update(BillingInvoice billingInvoice);
	public BillingInvoice getBillingInvoiceById(int id);
}
