package org.calminfotech.hr.boInterface;

import java.util.List;

import org.calminfotech.hr.models.Clockin;

public interface ClockinBoInterface {
	
public Clockin getClockinAssgnmentById(int id);
	
	public void save(Clockin clockin);
	
	List<Clockin> deleteAllCheckedValues(Integer userId);
	public List<Clockin> fetchAllByUnitId(int unitId);

}
