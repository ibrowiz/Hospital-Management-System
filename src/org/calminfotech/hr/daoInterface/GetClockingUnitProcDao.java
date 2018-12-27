package org.calminfotech.hr.daoInterface;

import java.util.List;

import org.calminfotech.hr.models.GetClockingUnitProc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface GetClockingUnitProcDao {
	List<GetClockingUnitProc> fetchClockinUnit(Integer userId);

}
