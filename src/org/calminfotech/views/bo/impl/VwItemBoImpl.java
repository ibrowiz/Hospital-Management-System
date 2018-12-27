package org.calminfotech.views.bo.impl;

import java.util.List;

import org.calminfotech.views.boInterface.VwItemBo;
import org.calminfotech.views.daoInterface.VwItemDao;
import org.calminfotech.views.models.VwItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VwItemBoImpl implements VwItemBo {
	
	@Autowired
	private VwItemDao wvItemDao;

	@Override
	public List<VwItem> fetchAllByPoint(Integer pointId) {
		// TODO Auto-generated method stub
		return this.wvItemDao.fetchAllByPoint(pointId);
	}

	@Override
	public VwItem getVwItemById(Integer id) {
		// TODO Auto-generated method stub
		return this.wvItemDao.getVwItemById(id);
	}

}
