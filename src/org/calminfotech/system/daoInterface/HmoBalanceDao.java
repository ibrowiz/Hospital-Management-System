package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.HmoBalance;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface HmoBalanceDao {

	List<HmoBalance> fetchAll();
	List<HmoBalance> fetchAllByOrganisation(Organisation organisation);
	void save(HmoBalance hmoBalance);
	void delete(HmoBalance hmoBalance);
	void update(HmoBalance hmoBalance);
	public HmoBalance getHmoBalanceById(int id);
	public HmoBalance getHmoStatus(Patient patientId, EhmoPackages packageId, int subservice, Organisation organisation );
}
