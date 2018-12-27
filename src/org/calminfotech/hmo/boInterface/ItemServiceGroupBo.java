package org.calminfotech.hmo.boInterface;
import java.util.List;

import org.calminfotech.hmo.models.ItemServiceGroup;
//import org.calminfotech.hmo.forms.EhmoPackageForm;
public interface ItemServiceGroupBo {
	
	public List<ItemServiceGroup> fetchAll();
	public ItemServiceGroup getItemServiceGroupById(int id);
	public void save(ItemServiceGroup itemServiceGroup);
	public void delete(ItemServiceGroup itemServiceGroup);
	public void update(ItemServiceGroup itemServiceGroup);
	
}
