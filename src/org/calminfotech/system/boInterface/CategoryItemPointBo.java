package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.CategoryItemPoint;

public interface CategoryItemPointBo {
	
	public List<CategoryItemPoint> fetchAll();	

	public CategoryItemPoint getCategoryItemPointById(int id);
	
	public void save(CategoryItemPoint itemPoint);
	
	public void update(CategoryItemPoint itemPoint);
	
	public void delete(CategoryItemPoint itemPoint);
}
