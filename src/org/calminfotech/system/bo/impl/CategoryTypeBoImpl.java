package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.CategoryTypeBo;
import org.calminfotech.system.daoInterface.CategoryTypeDao;
import org.calminfotech.system.models.CategoryTypeXX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryTypeBoImpl implements CategoryTypeBo {

	@Autowired
	private CategoryTypeDao catTypeDao;
	
	@Override
	public List<CategoryTypeXX> fetchAll() {
		return catTypeDao.fetchAll();
	}

	@Override
	public CategoryTypeXX getCategoryTypeById(int catTypeId) {
		return catTypeDao.getCategoryTypeById(catTypeId);
	}

	@Override
	public void save(CategoryTypeXX categoryType) {
		catTypeDao.save(categoryType);
	}

	@Override
	public void update(CategoryTypeXX categoryType) {
		catTypeDao.update(categoryType);
	}

	@Override
	public void delete(CategoryTypeXX categoryType) {
		catTypeDao.delete(categoryType);
	}

}
