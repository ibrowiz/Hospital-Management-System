package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.utils.models.Organisation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface GlobalItemCategoryDao {

	public GlobalItemCategory getGlobalCategoryItemById(int id);
	
	public List<GlobalItemCategory> fetchGlobalItemCategoryByItemTypeId(GlobalItemType globalItemType);

	public void save(GlobalItemCategory globalCategoryItem);

	public void delete(GlobalItemCategory globalCategoryItem);

	public void update(GlobalItemCategory globalCategoryItem);
		
	public List<GlobalItemCategory> fetchAll();

	public List<GlobalItemCategory> fetchAllByOrganisation(Organisation organisation);
}
