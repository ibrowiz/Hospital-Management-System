package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.PaymentLog;
import org.calminfotech.utils.models.Organisation;


public interface PaymentLogDao {

	List<PaymentLog> fetchAll();
	List<PaymentLog> fetchAllByOrganisation(Organisation organisation);
	void save(PaymentLog paymentLog);
	void delete(PaymentLog paymentLog);
	void update(PaymentLog paymentLog);
	public PaymentLog getPaymentLogById(int id);
}
