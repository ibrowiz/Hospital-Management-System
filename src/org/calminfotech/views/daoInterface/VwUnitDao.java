package org.calminfotech.views.daoInterface;

import java.util.List;

import org.calminfotech.views.models.VwUnit;

public interface VwUnitDao {

	List<VwUnit> fetchAllByItemId(Integer itemId);
	
	VwUnit getVwUnitById(Integer id);
}
