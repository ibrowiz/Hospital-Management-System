package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.billing.models.BillUnitOfMeasure;
import org.calminfotech.system.boInterface.GlobalItemUnitOfMeasureBo;
import org.calminfotech.system.daoInterface.GlobalItemUnitOfMeasureDao;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class GlobalItemUnitOfMeasureBoImpl implements GlobalItemUnitOfMeasureBo {
	
	@Autowired
	private GlobalItemUnitOfMeasureDao globalItemUnitOfMeasureDao;

	@Override
	public List<GlobalItemUnitOfMeasure> fetchAll() {
		return this.globalItemUnitOfMeasureDao.fetchAll();
	}

	@Override
	public GlobalItemUnitOfMeasure getGlobalItemMeasureById(int id) {
		return this.globalItemUnitOfMeasureDao.getGlobalItemMeasureById(id);
	}

	@Override
	public void save(GlobalItemUnitOfMeasure measure) {
		this.globalItemUnitOfMeasureDao.save(measure);
	}

	@Override
	public void delete(GlobalItemUnitOfMeasure measure) {
		this.globalItemUnitOfMeasureDao.delete(measure);
	}

	@Override
	public void update(GlobalItemUnitOfMeasure measure) {
		this.globalItemUnitOfMeasureDao.update(measure);
	}

	@Override
	public BillUnitOfMeasure fetchGlobalItemViaMeasure(Integer id) {
	return	this.globalItemUnitOfMeasureDao.fetchGlobalItemViaMeasure(id);
	}

	@Override
	public List<BillUnitOfMeasure> listGlobalItemViaMeasure(Integer id) {
		return	this.globalItemUnitOfMeasureDao.listGlobalItemViaMeasure(id);
	}

	@Override
	public GlobalItem fetchUnitOfMesureViaGlobalItem(int id) {
		return	this.globalItemUnitOfMeasureDao.fetchUnitOfMesureViaGlobalItem(id);
	}

}
