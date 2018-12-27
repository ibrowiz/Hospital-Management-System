package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitPayment;
import org.calminfotech.utils.models.Organisation;


public interface VisitPaymentDao {
	
	public List<VisitPayment> fetchAll();
	
	public List<VisitPayment> fetchAllByOrgainsation(
			Organisation organisation);

	public List<VisitPayment> fetchAllByVisit(Visit visit);
	
	public VisitPayment getVisitPaymentById(int id);
	
	public void save(VisitPayment visitPayment);

	public void delete(VisitPayment visitPayment);

	public void update(VisitPayment visitPayment);
	
	





}
