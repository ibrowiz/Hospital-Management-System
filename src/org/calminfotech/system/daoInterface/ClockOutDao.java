package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.ClockOut;
import org.calminfotech.system.models.Clocking;


public interface ClockOutDao {
	
	
/*	public List<ClockOut> fetchAll();

	public List<ClockOut> fetchAllByOrgainsation(
			Organisation organisation);

	public List<Clocking> fetchAllByloginsectn(Clocking clocking);
	
	public List<ClockOut> fetchAllByUser(User user);*/

	public void save(ClockOut clockOut);

	public ClockOut getClockOutById(int id);

	public void delete(ClockOut clockOut);


}
