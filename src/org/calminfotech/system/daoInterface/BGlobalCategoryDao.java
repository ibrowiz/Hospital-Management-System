package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.BGlobalCategory;
import org.calminfotech.system.models.OuterRecursive;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface BGlobalCategoryDao {

	public List<BGlobalCategory> fetchAll();

	public BGlobalCategory getCategoryById(int categoryId);

	public List<OuterRecursive> fetchAllTypes();

	public void save(BGlobalCategory category);

	public void update(BGlobalCategory category);

	public void delete(BGlobalCategory category);

}
