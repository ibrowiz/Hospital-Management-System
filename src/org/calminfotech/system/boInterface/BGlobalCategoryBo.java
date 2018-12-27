package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.BGlobalCategory;
import org.calminfotech.system.models.OuterRecursive;

public interface BGlobalCategoryBo {

	public List<BGlobalCategory> fetchAll();

	public BGlobalCategory getCategoryById(int categoryId);

	public void save(BGlobalCategory category);

	public void update(BGlobalCategory category);

	public void delete(BGlobalCategory category);

	public List<OuterRecursive> fetchAllTypes();
}
