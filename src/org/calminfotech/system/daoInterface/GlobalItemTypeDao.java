package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.utils.models.Organisation;

public interface GlobalItemTypeDao {
	public GlobalItemType getGlobalItemTypeById(int id);

	public void save(GlobalItemType globalItemType);

	public void delete(GlobalItemType globalItemType);

	public void update(GlobalItemType globalItemType);
		
	public List<GlobalItemType> fetchAll();

	public List<GlobalItemType> fetchAllByOrganisation(Organisation organisation);
}
