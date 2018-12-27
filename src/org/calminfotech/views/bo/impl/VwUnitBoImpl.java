package org.calminfotech.views.bo.impl;

import java.util.List;

import org.calminfotech.views.boInterface.VwUnitBo;
import org.calminfotech.views.daoInterface.VwUnitDao;
import org.calminfotech.views.models.VwUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VwUnitBoImpl implements VwUnitBo {
	
	@Autowired
	private VwUnitDao vwUnitDao;

	@Override
	public List<VwUnit> fetchAllByItemId(Integer itemId) {
		// TODO Auto-generated method stub
		return this.vwUnitDao.fetchAllByItemId(itemId);
	}

	@Override
	public VwUnit getVwUnitById(Integer id) {
		// TODO Auto-generated method stub
		return this.vwUnitDao.getVwUnitById(id);
	}

}
