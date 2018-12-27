package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.BGlobalCategoryBo;
import org.calminfotech.system.daoInterface.BGlobalCategoryDao;
import org.calminfotech.system.models.BGlobalCategory;
import org.calminfotech.system.models.OuterRecursive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BGlobalCategoryBoImpl implements BGlobalCategoryBo {

	@Autowired
	private BGlobalCategoryDao bCategoryDao;

	@Override
	public List<BGlobalCategory> fetchAll() {
		// TODO Auto-generated method stub

		return bCategoryDao.fetchAll();
	}

	@Override
	public BGlobalCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return bCategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(BGlobalCategory category) {
		// TODO Auto-generated method stub

		bCategoryDao.save(category);
	}

	@Override
	public void update(BGlobalCategory category) {
		// TODO Auto-generated method stub

		bCategoryDao.update(category);
	}

	@Override
	public void delete(BGlobalCategory category) {
		// TODO Auto-generated method stub

		bCategoryDao.delete(category);

	}

	@Override
	public List<OuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return bCategoryDao.fetchAllTypes();
	}

}
