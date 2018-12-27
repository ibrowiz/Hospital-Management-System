package org.calminfotech.consultation.bo;

import org.calminfotech.consultation.models.VisitPresentingComplaint;


public interface VisitPresentingCompliantBo {
	
	public void save(VisitPresentingComplaint visitPresentingComplaint);

	public void delete(VisitPresentingComplaint visitPresentingComplaint);

	public VisitPresentingComplaint getVisitPresentingComplaintyById(int id);

}
