package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.BGlobalItemBo;
import org.calminfotech.system.daoInterface.BGlobalItemDao;
import org.calminfotech.system.models.BGlobalItem;
import org.calminfotech.system.models.OuterRecursive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BGlobalItemBoImpl implements BGlobalItemBo {

	@Autowired
	private BGlobalItemDao categoryItemDao;

	@Override
	public List<BGlobalItem> fetchAll() {
		// TODO Auto-generated method stub
		return categoryItemDao.fetchAll();
	}

	@Override
	public BGlobalItem getCategoryItemById(int itemId) {
		// TODO Auto-generated method stub
		return categoryItemDao.getCategoryItemById(itemId);
	}

	@Override
	public void save(BGlobalItem category) {
		// TODO Auto-generated method stub

		categoryItemDao.save(category);
	}

	@Override
	public void update(BGlobalItem category) {
		// TODO Auto-generated method stub

		categoryItemDao.update(category);
	}

	@Override
	public void delete(BGlobalItem category) {
		// TODO Auto-generated method stub

		categoryItemDao.update(category);

	}

	@Override
	public List<OuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return categoryItemDao.fetchAllTypesNew();

	}

	@Override
	public List<BGlobalItem> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return categoryItemDao.fetchAllByOgranisation(organisationId);
	}

}
