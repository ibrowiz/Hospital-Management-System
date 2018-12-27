package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.GlobalItemCategoryForm;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemType;

public interface GlobalItemCategoryBo {

	public GlobalItemCategory getGlobalCategoryItemById(int id);
	
	public List<GlobalItemCategory> fetchGlobalItemCategoryByItemTypeId(GlobalItemType globalItemTypId);

	public void save(GlobalItemCategory globalCategoryItem);
	
	public void saveCategoryForm(GlobalItemCategoryForm cForm);

	public void delete(GlobalItemCategory globalCategoryItem);

	public void update(GlobalItemCategory globalCategoryItem);
	
	public void updateForm(GlobalItemCategoryForm cForm);
		
	public List<GlobalItemCategory> fetchAll();

	public List<GlobalItemCategory> fetchAllByOrganisation();
}
