package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.UnitItem;

public interface UnitItemBo {

	public void save(UnitItem unitItem);
	
	public void deleteUnitItem(UnitItem unitItem);
	
	public void edit(UnitItem unitItem );
	
	public UnitItem getItemById(Integer id);
	
	public List<UnitItem> fetchItemByUnit(UnitItem unitItem);
	
	public UnitItem getByUnitIdAndItemId(Integer unitId, Integer itemId);
}
