package org.calminfotech.hr.bo.impl;

import java.util.List;

import org.calminfotech.hr.boInterface.ClockinBoInterface;
import org.calminfotech.hr.daoInterface.ClockinDao;
import org.calminfotech.hr.models.Clockin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClockinBoImpl implements ClockinBoInterface {
	
	@Autowired
	private ClockinDao clockinDao;

	@Override
	public Clockin getClockinAssgnmentById(int id) {
		return this.clockinDao.getClockinAssgnmentById(id);
	}

	@Override
	public void save(Clockin clockin) {
	this.clockinDao.save(clockin);
		
	}

	@Override
	public List<Clockin> deleteAllCheckedValues(Integer userId) {
		return this.clockinDao.deleteAllCheckedValues(userId);
	}

	@Override
	public List<Clockin> fetchAllByUnitId(int unitId) {
		return this.clockinDao.fetchAllByUnitId(unitId);
	}
	

	/*@Override
	public Clockin getClockinById(int id) {
		return this.clockinDao.getClockinById(id);
	}

	@Override
	public List<Clockin> fetchAllByOrganisation(int organisationId) {
		return this.clockinDao.fetchAllByOrganisation(organisationId);
	}

	@Override
	public List<Clockin> fetchAllByUnitId(int unitId) {
		return this.clockinDao.fetchAllByUnitId(unitId);
	}
	return this.clockinDao.getClockinByUnitId(unitId);
*/
	

}
