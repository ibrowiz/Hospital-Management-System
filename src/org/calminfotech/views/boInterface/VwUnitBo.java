package org.calminfotech.views.boInterface;

import java.util.List;

import org.calminfotech.views.models.VwUnit;

public interface VwUnitBo {
	
	List<VwUnit> fetchAllByItemId(Integer itemId);
	
	VwUnit getVwUnitById(Integer id);
}
