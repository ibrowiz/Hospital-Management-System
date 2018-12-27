package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitTimeline;

public interface VisitTimelineBo {
	
	public VisitTimeline save (VisitTimeline visitTimeline);
	
	public List<VisitTimeline> getVTimelineByVisitId(
			int visitId);
	public VisitTimeline getVisitTlineByVisitId(int visitId);
}
