package org.calminfotech.hmo.daoInterface;
import java.util.List;

import org.calminfotech.hmo.models.ItemServiceGroup;
//import org.calminfotech.hmo.forms.EhmoPackageForm;
public interface ItemServiceGroupDao {
	
	public List<ItemServiceGroup> fetchAll();
	public ItemServiceGroup getItemServiceGroupById(int id);
	public void save(ItemServiceGroup itemServiceGroup);
	public void delete(ItemServiceGroup itemServiceGroup);
	public void update(ItemServiceGroup itemServiceGroup);
	
}
