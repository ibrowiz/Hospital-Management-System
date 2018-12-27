package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.RescheduledVisit;

public interface RescheduledVisitDao {

	public List<RescheduledVisit> fetchAll();

	public List<RescheduledVisit> fetchAllByOrganisation();

	public RescheduledVisit getRescheduledVisitById(int id);

	public void save(RescheduledVisit rescheduledVisit);

	public void delete(RescheduledVisit rescheduledVisit);

	public void update(RescheduledVisit rescheduledVisit);
}
