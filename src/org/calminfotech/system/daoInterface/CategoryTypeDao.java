package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.CategoryTypeXX;

public interface CategoryTypeDao {

	public List<CategoryTypeXX> fetchAll();	

	public CategoryTypeXX getCategoryTypeById(int catTypeId);
	
	public void save(CategoryTypeXX categoryType);
	
	public void update(CategoryTypeXX categoryType);
	
	public void delete(CategoryTypeXX categoryType);
}
