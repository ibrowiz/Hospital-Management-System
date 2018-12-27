package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.CategoryItemUnit;

public interface CategoryItemUnitDao {

	public List<CategoryItemUnit> fetchAll();	

	public CategoryItemUnit getCategoryItemUnitById(int id);
	
	public void save(CategoryItemUnit itemUnit);
	
	public void update(CategoryItemUnit itemUnit);
	
	public void delete(CategoryItemUnit itemUnit);

	

}
