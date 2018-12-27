package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HmoPackageServiceBo;
import org.calminfotech.system.daoInterface.HmoPackageServiceDao;
import org.calminfotech.system.models.HmoPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HmoPackageServiceBoImpl implements HmoPackageServiceBo {

	@Autowired
	private HmoPackageServiceDao hmoPackageServiceDao;

	@Override
	public List<HmoPackageService> fetchAll() {
		// TODO Auto-generated method stub
		return this.hmoPackageServiceDao.fetchAll();
	}

	@Override
	public List<HmoPackageService> fetchAllByPackageId(int id) {
		// TODO Auto-generated method stub
		return this.hmoPackageServiceDao.fetchAllByPackageId(id);
	}

	@Override
	public HmoPackageService getServiceById(int id) {
		// TODO Auto-generated method stub
		return this.hmoPackageServiceDao.getServiceById(id);
	}

	@Override
	public void save(HmoPackageService service) {
		// TODO Auto-generated method stub
		this.hmoPackageServiceDao.save(service);
	}

	@Override
	public void delete(HmoPackageService service) {
		// TODO Auto-generated method stub
		this.hmoPackageServiceDao.delete(service);
	}

	@Override
	public void update(HmoPackageService service) {
		// TODO Auto-generated method stub
		this.hmoPackageServiceDao.update(service);
	}

}
