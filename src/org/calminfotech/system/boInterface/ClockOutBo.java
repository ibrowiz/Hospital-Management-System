package org.calminfotech.system.boInterface;

import org.calminfotech.system.models.ClockOut;


public interface ClockOutBo {
	
	
	public void save(ClockOut clockOut);

	public void delete(ClockOut clockOut);
	
	public void update(ClockOut clockOut);

	public ClockOut getClockOutById(int id);
	
	

}
