package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.HmoSubserviceItem;

public interface HmoSubserviceItemDao {

	HmoSubserviceItem getHmoSubserviceItemById(int id);
	
	HmoSubserviceItem checksubservice(int itemid, int packid);
	
	HmoSubserviceItem getHmoSubserviceItemByCategoryItem(int itemId, Integer organisation);
	
	void save(HmoSubserviceItem hmoSubserviceItem);
	
	List<HmoSubserviceItem> deleteAll(HmoSubserviceItem hmoSubserviceItem, Integer organisation);
}
