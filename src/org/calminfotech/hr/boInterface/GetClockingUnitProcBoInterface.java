package org.calminfotech.hr.boInterface;

import java.util.List;

import org.calminfotech.hr.models.GetClockingUnitProc;

public interface GetClockingUnitProcBoInterface {
	List<GetClockingUnitProc> fetchClockinUnit(Integer userId);

}
