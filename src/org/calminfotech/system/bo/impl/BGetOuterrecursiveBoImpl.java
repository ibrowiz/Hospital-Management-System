package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.BGetOuterrecursiveBo;
import org.calminfotech.system.daoInterface.BGetOuterrecursiveDao;
import org.calminfotech.system.models.BOuterRecursive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BGetOuterrecursiveBoImpl implements BGetOuterrecursiveBo {

	@Autowired
	private BGetOuterrecursiveDao getOuterrecursiveDao;

	@Override
	public List<BOuterRecursive> fetchAllCategories() {
		// TODO Auto-generated method stub
		return getOuterrecursiveDao.fetchAllCategories();
	}

}
