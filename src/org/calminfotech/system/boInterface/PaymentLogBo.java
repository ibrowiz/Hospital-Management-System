package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.consultation.forms.PaymentForm;
import org.calminfotech.system.models.PaymentLog;

public interface PaymentLogBo {

	List<PaymentLog> fetchAll();
	List<PaymentLog> fetchAllByOrganisation();
	void save(PaymentLog paymentLog);
	void delete(PaymentLog paymentLog);
	void update(PaymentLog paymentLog);
	void updateHmoBalance(PaymentForm paymentForm);
	void saveHmoBalance(PaymentForm paymentForm);
	void noHmoBalance(PaymentForm paymentForm);
	public PaymentLog getPaymentLogById(int id);
}
