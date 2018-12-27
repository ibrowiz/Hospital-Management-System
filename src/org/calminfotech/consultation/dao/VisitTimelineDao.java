package org.calminfotech.consultation.dao;
import java.util.List;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitTimeline;

public interface VisitTimelineDao {
	
	public void save (VisitTimeline visitTimeline);
	
	public List<VisitTimeline> getVTimelineByVisitId(
			Visit visit); 
	
	public VisitTimeline getVisitTlineByVisitId(Visit visit);
	
}
