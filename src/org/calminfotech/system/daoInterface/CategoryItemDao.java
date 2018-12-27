package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.CategoryItem;

public interface CategoryItemDao {

	public List<CategoryItem> fetchAll();	

	public CategoryItem getCategoryItemById(int itemId);
	
	public void save(CategoryItem item);
	
	public void update(CategoryItem item);
	
	public void delete(CategoryItem item);

	

}
