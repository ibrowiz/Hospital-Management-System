package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.CategoryTypeXX;

public interface CategoryTypeBo {
	
	public List<CategoryTypeXX> fetchAll();	

	public CategoryTypeXX getCategoryTypeById(int id);
	
	public void save(CategoryTypeXX categoryType);
	
	public void update(CategoryTypeXX categoryType);
	
	public void delete(CategoryTypeXX categoryType);

}
