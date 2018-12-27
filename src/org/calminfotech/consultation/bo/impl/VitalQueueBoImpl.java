package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.VitalQueueBo;
import org.calminfotech.consultation.dao.VitalQueueDao;
import org.calminfotech.consultation.models.VitalQueue;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class VitalQueueBoImpl implements VitalQueueBo{

	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private VitalQueueDao vitalQueueDao;
	
	
	@Override
	public List<VitalQueue> fetchAll() {
		// TODO Auto-generated method stub
		
		return this.vitalQueueDao.fetchAll();
	}


	@Override
	public List<VitalQueue> fetchAllByUser() {
		// TODO Auto-generated method stub
		return this.vitalQueueDao.fetchAllByUser(this.userIdentity.getUserId());
	}

	
}
