package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.HmoSubserviceItem;

public interface HmoSubserviceItemBo {

	HmoSubserviceItem getHmoSubserviceItemById(int id);
	
	HmoSubserviceItem checksubservice(int itemid, int packid);
	
	HmoSubserviceItem getHmoSubserviceItemByCategoryItem(int itemId);
	
	void save(HmoSubserviceItem hmoSubserviceItem);
	
	List<HmoSubserviceItem> deleteAll(HmoSubserviceItem hmoSubserviceItem);
}
