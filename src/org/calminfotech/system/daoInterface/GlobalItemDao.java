package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.utils.models.Organisation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface GlobalItemDao {
	
	public GlobalItem getglobalItemById(int id);

	public void save(GlobalItem globalItem);

	public void delete(GlobalItem globalItem);

	public void update(GlobalItem globalItem);
		
	public List<GlobalItem> fetchAll();

	public List<GlobalItem> fetchAllByOrganisation(Organisation organisation);

}
