package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemCategory;

public interface CategoryDao {

	public List<GlobalItemCategory> fetchAll();	

	public GlobalItemCategory getCategoryById(int catTypeId);
	
	public void save(GlobalItemCategory category);
	
	public void update(GlobalItemCategory category);
	
	public void delete(GlobalItemCategory category);

	

}
