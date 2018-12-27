package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.CategoryItemPointBo;
import org.calminfotech.system.daoInterface.CategoryItemPointDao;
import org.calminfotech.system.models.CategoryItemPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryItemPointBoImpl implements CategoryItemPointBo {

	@Autowired
	private CategoryItemPointDao itemPointDao;
	
	@Override
	public List<CategoryItemPoint> fetchAll() {
		return itemPointDao.fetchAll();
	}

	@Override
	public CategoryItemPoint getCategoryItemPointById(int PointId) {
		return itemPointDao.getCategoryItemPointById(PointId);
	}

	@Override
	public void save(CategoryItemPoint itemPoint) {
		itemPointDao.save(itemPoint);
	}

	@Override
	public void update(CategoryItemPoint itemPoint) {
		itemPointDao.update(itemPoint);
	}

	@Override
	public void delete(CategoryItemPoint itemPoint) {
		itemPointDao.delete(itemPoint);
	}
}
