package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.BGlobalItem;
import org.calminfotech.system.models.OuterRecursive;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface BGlobalItemDao {

	public List<BGlobalItem> fetchAll();
	

	public List<BGlobalItem> fetchAllByOgranisation(int organisationId);

	public BGlobalItem getCategoryItemById(int itemId);

	public List<OuterRecursive> fetchAllTypesNew();

	public void save(BGlobalItem category);

	public void update(BGlobalItem category);

	public void delete(BGlobalItem category);
}
