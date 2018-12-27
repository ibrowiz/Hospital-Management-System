package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.CategoryBo;
import org.calminfotech.system.daoInterface.CategoryDao;
import org.calminfotech.system.models.GlobalItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryBoImpl implements CategoryBo {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<GlobalItemCategory> fetchAll() {
		return categoryDao.fetchAll();
	}

	@Override
	public GlobalItemCategory getCategoryById(int categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(GlobalItemCategory category) {
		categoryDao.save(category);
	}

	@Override
	public void update(GlobalItemCategory category) {
		categoryDao.update(category);
	}

	@Override
	public void delete(GlobalItemCategory category) {
		categoryDao.delete(category);
	}

}
