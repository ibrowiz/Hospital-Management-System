package org.calminfotech.hmo.bo.impl;
//import java.util.Date;
import java.util.List;

import org.calminfotech.hmo.boInterface.EhmoPackageBo;
import org.calminfotech.hmo.daoInterface.EhmoPackageDao;

import org.calminfotech.hmo.models.EhmoPackage;



//import org.calminfotech.admin.forms.DataTableForm
//import org.calminfotech.system.models.AllergyCategory;
//import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EhmoPackageBoImpl implements EhmoPackageBo {

	@Autowired
	private EhmoPackageDao ehmoPackageDao;

	@Override
	public List<EhmoPackage> fetchAll() {
		// TODO Auto-generated method stub
		return ehmoPackageDao.fetchAll();
	}

	@Override
	public EhmoPackage getEhmoPackageById(int id) {
		// TODO Auto-generated method stub
		return ehmoPackageDao.getEhmoPackageById(id);
	}

	
	@Override
	public void delete(EhmoPackage ehmoPackage) {
		// TODO Auto-generated method stub
		ehmoPackageDao.delete(ehmoPackage);
	}


	@Override
	public void save(EhmoPackage ehmoPackage) {
		// TODO Auto-generated method stub
		
		
		ehmoPackageDao.save(ehmoPackage);
		
	}


	@Override
	public void update(EhmoPackage ehmoPackageT) {
        ehmoPackageDao.update(ehmoPackageT);
	}
}
