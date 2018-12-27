package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.GlobalItemForm;

import org.calminfotech.system.models.GlobalItem;

public interface GlobalItemBo {
	
	public List<GlobalItem> fetchAllByOrganisation();

	public GlobalItem getGlobalItemById(int id);

	public void save(GlobalItemForm iForm);

	public void delete(GlobalItem gblItem);

	public void update(GlobalItemForm iForm);
		
	public List<GlobalItem> fetchAll();
	
}
