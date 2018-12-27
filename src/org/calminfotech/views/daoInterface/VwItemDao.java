package org.calminfotech.views.daoInterface;

import java.util.List;

import org.calminfotech.views.models.VwItem;

public interface VwItemDao {

	List<VwItem> fetchAllByPoint(Integer pointId);
	
	VwItem getVwItemById(Integer id);
}
