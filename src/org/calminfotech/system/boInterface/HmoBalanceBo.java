package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.HmoBalance;

public interface HmoBalanceBo {

	List<HmoBalance> fetchAll();
	List<HmoBalance> fetchAllByOrganisation();
	void save(HmoBalance hmoBalance);
	void delete(HmoBalance hmoBalance);
	void update(HmoBalance hmoBalance);
	public HmoBalance getHmoBalanceById(int id);
	public HmoBalance getHmoStatus(int patientId, int packageId, int subservice);
}
