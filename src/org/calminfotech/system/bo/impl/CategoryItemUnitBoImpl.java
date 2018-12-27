package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.CategoryItemUnitBo;
import org.calminfotech.system.daoInterface.CategoryItemUnitDao;
import org.calminfotech.system.models.CategoryItemUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryItemUnitBoImpl implements CategoryItemUnitBo {

	@Autowired
	private CategoryItemUnitDao itemUnitDao;
	
	@Override
	public List<CategoryItemUnit> fetchAll() {
		return itemUnitDao.fetchAll();
	}

	@Override
	public CategoryItemUnit getCategoryItemUnitById(int unitId) {
		return itemUnitDao.getCategoryItemUnitById(unitId);
	}

	@Override
	public void save(CategoryItemUnit itemUnit) {
		itemUnitDao.save(itemUnit);
	}

	@Override
	public void update(CategoryItemUnit itemUnit) {
		itemUnitDao.update(itemUnit);
	}

	@Override
	public void delete(CategoryItemUnit itemUnit) {
		itemUnitDao.delete(itemUnit);
	}

}
