package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.daoInterface.GlobalUnitofMeasureDao;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalUnitofMeasureBoImpl implements GlobalUnitofMeasureBo {
	
	@Autowired
	private GlobalUnitofMeasureDao unitofMeasureDao;

	@Override
	public List<GlobalUnitofMeasure> fetchAll() {
		// TODO Auto-generated method stub
		return unitofMeasureDao.fetchAll();
	}

	@Override
	public GlobalUnitofMeasure getUnitofMeasureById(int id) {
		// TODO Auto-generated method stub
		return unitofMeasureDao.getGlobalUnitofMeasureById(id);
	}

	@Override
	public void save(GlobalUnitofMeasure unitofMeasure) {
		// TODO Auto-generated method stub
		unitofMeasureDao.save(unitofMeasure);
	}

	@Override
	public void delete(GlobalUnitofMeasure unitofMeasure) {
		// TODO Auto-generated method stub
		unitofMeasureDao.delete(unitofMeasure);
	}

	@Override
	public void update(GlobalUnitofMeasure unitofMeasure) {
		// TODO Auto-generated method stub
		unitofMeasureDao.update(unitofMeasure);
	}
}
