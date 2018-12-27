package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.VitalQueue;


public interface VitalQueueBo {
	
	
	public List<VitalQueue> fetchAll();

	public List<VitalQueue> fetchAllByUser();

}
