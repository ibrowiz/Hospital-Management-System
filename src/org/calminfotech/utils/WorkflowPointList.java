package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;

import org.springframework.stereotype.Service;


@Service
public class WorkflowPointList extends CustomHibernateDaoSupport{
	
	public List<VisitWorkflowPoint> fetchAll() {
		List<VisitWorkflowPoint> list = getHibernateTemplate().find("from VisitWorkflowPoint");
		return list;
	}
	
	public VisitWorkflowPoint getVisitWorkflowPointById(int id) {
		List list = getHibernateTemplate().find(
				"from VisitWorkflowPoint where id = ?", id);
		if (list.size() > 0)
			return (VisitWorkflowPoint) list.get(0);
		return null;
	}

}
