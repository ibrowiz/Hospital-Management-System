package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitPayment;
import org.calminfotech.utils.models.Organisation;

public interface VisitPaymentBo {
	

	
	public VisitPayment getVisitPaymentById(int id);
	
	public void save(VisitPayment visitPayment);

	public void delete(VisitPayment visitPayment);

	public void update(VisitPayment visitPayment);
}
