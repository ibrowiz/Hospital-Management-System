package org.calminfotech.hmo.bo.impl;

import java.util.List;

import org.calminfotech.hmo.boInterface.EhmoPackageServicesViewBo;
import org.calminfotech.hmo.daoInterface.EhmoPackageServicesViewDao;
import org.calminfotech.hmo.models.EhmoPackageServicesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EhmoPackageServicesViewBoImpl implements EhmoPackageServicesViewBo  {
	
@Autowired
private EhmoPackageServicesViewDao ehmoPackageServicesViewDao;

	@Override
	public EhmoPackageServicesView getPackageServicesViewById(Integer id) {
		return this.ehmoPackageServicesViewDao.getPackageServicesViewById(id) ;
	}

	@Override
	public List<EhmoPackageServicesView> fetchAllByOrganisation(
			Integer organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

}
