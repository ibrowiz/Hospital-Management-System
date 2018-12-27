package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.UnitItemBo;
import org.calminfotech.system.daoInterface.UnitItemDao;
import org.calminfotech.system.models.UnitItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnitItemBoImpl implements UnitItemBo {
	
	@Autowired
	private UnitItemDao unitItemDao;

	@Override
	public void save(UnitItem unitItem) {
		// TODO Auto-generated method stub
		this.unitItemDao.save(unitItem);
	}

	@Override
	public void deleteUnitItem(UnitItem unitItem) {
		// TODO Auto-generated method stub
		unitItemDao.deleteUnitItem(unitItem);
	}

	@Override
	public void edit(UnitItem unitItem) {
		// TODO Auto-generated method stub
		unitItemDao.edit(unitItem);
	}

	@Override
	public UnitItem getItemById(Integer id) {
		// TODO Auto-generated method stub
		return unitItemDao.getItemById(id);
	}

	@Override
	public List<UnitItem> fetchItemByUnit(UnitItem unitItem) {
		// TODO Auto-generated method stub
		return unitItemDao.fetchItemByUnit(unitItem);
	}

	@Override
	public UnitItem getByUnitIdAndItemId(Integer unitId, Integer itemId) {
		// TODO Auto-generated method stub
		return unitItemDao.getByUnitIdAndItemId(unitId, itemId);
	}

}
