package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.BCategoryListBo;
import org.calminfotech.system.daoInterface.BCategoryListDao;
import org.calminfotech.system.models.BCategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BCategoryListBoImpl implements BCategoryListBo {

	@Autowired
	private BCategoryListDao categoryListDao;

	public List<BCategoryList> fetchAll() {
		// TODO Auto-generated method stub
		return categoryListDao.fetchAll();
	}

	@Override
	public BCategoryList getCategoryById(int id) {
		// TODO Auto-generated method stub
		return categoryListDao.getCategoryById(id);
	}

}
