package org.calminfotech.hmo.boInterface;
import java.util.List;

import org.calminfotech.hmo.models.EhmoCategoryList;
//import org.calminfotech.hmo.forms.EhmoPackageForm;
public interface EhmoCategoryListBo{
	
	public List<EhmoCategoryList> fetchAll();
	public EhmoCategoryList getEhmoCategoryListById(int id);
	public void save(EhmoCategoryList ehmoCategoryList);
	public void delete(EhmoCategoryList ehmoCategoryList);
	public void update(EhmoCategoryList ehmoCategoryList);
	
}
