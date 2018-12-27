package org.calminfotech.consultation.dao;

import java.util.List;


import org.calminfotech.consultation.models.VitalQueue;
import org.calminfotech.user.models.User;


public interface VitalQueueDao {
	
	public List<VitalQueue> fetchAll();

	public List<VitalQueue> fetchAllByUser(int i);

	List<VitalQueue> fetchAllByUser(User user);

	

}
