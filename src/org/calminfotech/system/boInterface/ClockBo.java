package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.Clock;
import org.calminfotech.utils.models.Organisation;

public interface ClockBo {
	
	public List<Clock> fetchAllClock();	

	public List<Clock> fetchAllByOrganisation(Organisation organisation);
	
	public Clock getClockById(Integer id);
	
	public void save(Clock clock);
	
	public void update(Clock clock);
	
	public void delete(Clock clock);

}
