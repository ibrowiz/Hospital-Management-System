package org.calminfotech.views.boInterface;

import java.util.List;

import org.calminfotech.views.models.VwItem;

public interface VwItemBo {

	List<VwItem> fetchAllByPoint(Integer pointId);
	
	VwItem getVwItemById(Integer id);
}
