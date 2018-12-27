package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.BGlobalItem;
import org.calminfotech.system.models.OuterRecursive;

public interface BGlobalItemBo {

	public List<BGlobalItem> fetchAll();

	public BGlobalItem getCategoryItemById(int itemId);

	public List<OuterRecursive> fetchAllTypes();

	public void save(BGlobalItem category);

	public void update(BGlobalItem category);

	public void delete(BGlobalItem category);

	public List<OuterRecursive> fetchAllTypesNew();
	
	
	public List<BGlobalItem> fetchAllByOgranisation(int organisationId);
}
