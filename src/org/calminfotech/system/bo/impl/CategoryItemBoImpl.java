package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.CategoryItemBo;
import org.calminfotech.system.daoInterface.CategoryItemDao;
import org.calminfotech.system.models.CategoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryItemBoImpl implements CategoryItemBo {

	@Autowired
	private CategoryItemDao itemDao;
	
	@Override
	public List<CategoryItem> fetchAll() {
		return itemDao.fetchAll();
	}

	@Override
	public CategoryItem getCategoryItemById(int itemId) {
		return itemDao.getCategoryItemById(itemId);
	}

	@Override
	public void save(CategoryItem item) {
		itemDao.save(item);
	}

	@Override
	public void update(CategoryItem item) {
		itemDao.update(item);
	}

	@Override
	public void delete(CategoryItem item) {
		itemDao.delete(item);
	}

}
