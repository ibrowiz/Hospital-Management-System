package org.calminfotech.setup.bo.impl;

import java.util.List;

import org.calminfotech.setup.boInterface.CategoryListBo;
import org.calminfotech.setup.daoInterface.CategoryListDao;
import org.calminfotech.setup.models.AllergyCategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryListBoImpl implements CategoryListBo {
	
	@Autowired
	private CategoryListDao categoryListDao;

	public List<AllergyCategoryList> fetchAll() {
		// TODO Auto-generated method stub
		return categoryListDao.fetchAll();
	}

	@Override
	public List<AllergyCategoryList> fetchAllByOrganisation(
			Integer organisationId) {
		// TODO Auto-generated method stub
		return categoryListDao.fetchAllByOrganisation(organisationId);
	}

}
