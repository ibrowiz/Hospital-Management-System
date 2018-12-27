package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemCategory;

public interface CategoryBo {
	
	public List<GlobalItemCategory> fetchAll();	

	public GlobalItemCategory getCategoryById(int id);
	
	public void save(GlobalItemCategory category);
	
	public void update(GlobalItemCategory category);
	
	public void delete(GlobalItemCategory category);

}
