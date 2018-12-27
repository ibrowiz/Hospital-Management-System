package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.consultation.bo.VisitTimelineBo;
import org.calminfotech.consultation.dao.VisitTimelineDao;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitTimeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class VisitTimelineBoImpl implements VisitTimelineBo {
	
	@Autowired
	private VisitTimelineDao visitTimelineDao;
	
	@Autowired
	private VisitBo visitBo;

	@Override
	public VisitTimeline save(VisitTimeline visitTimeline) {
		visitTimelineDao.save(visitTimeline);	
		
		return visitTimeline;
	}
	
	@Override
	public List<VisitTimeline> getVTimelineByVisitId(
			int visitId) {
		Visit visit = visitBo.getVisitationById(visitId);
		return visitTimelineDao.getVTimelineByVisitId(visit);
}

	@Override
	public VisitTimeline getVisitTlineByVisitId(int visitId) {
		Visit visit = visitBo.getVisitationById(visitId);
		return visitTimelineDao.getVisitTlineByVisitId(visit);
	}

}
