package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.system.boInterface.HmoBalanceBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.daoInterface.HmoBalanceDao;
import org.calminfotech.system.models.HmoBalance;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HmoBalanceBoImpl implements HmoBalanceBo {

	@Autowired
	private HmoBalanceDao hmoBalanceDao;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private PatientBo patientBo;
	
	@Autowired
	private EHmoPackagesBo hmoPackageBo;

	@Override
	public List<HmoBalance> fetchAll() {
		// TODO Auto-generated method stub
		return hmoBalanceDao.fetchAll();
	}

	@Override
	public List<HmoBalance> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return hmoBalanceDao.fetchAllByOrganisation(userIdentity.getOrganisation());
	}

	@Override
	public void save(HmoBalance hmoBalance) {
		// TODO Auto-generated method stub
		hmoBalanceDao.save(hmoBalance);
	}

	@Override
	public void delete(HmoBalance hmoBalance) {
		// TODO Auto-generated method stub
		hmoBalanceDao.delete(hmoBalance);
	}

	@Override
	public void update(HmoBalance hmoBalance) {
		// TODO Auto-generated method stub
		hmoBalanceDao.update(hmoBalance);
	}

	@Override
	public HmoBalance getHmoBalanceById(int id) {
		// TODO Auto-generated method stub
		return hmoBalanceDao.getHmoBalanceById(id);
	}

	@Override
	public HmoBalance getHmoStatus(int patientId,
			int packageId, int subservice) {
		// TODO Auto-generated method stub
		Patient patient = patientBo.getPatientById(patientId);
		EhmoPackages hmoPackage = hmoPackageBo.getPackageById(packageId);
		return hmoBalanceDao.getHmoStatus(patient, hmoPackage, subservice, userIdentity.getOrganisation());
	}

}
