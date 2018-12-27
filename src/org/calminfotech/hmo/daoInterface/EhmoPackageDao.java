package org.calminfotech.hmo.daoInterface;

import java.util.List;

import org.calminfotech.hmo.models.EhmoPackage;

public interface EhmoPackageDao {

	public List<EhmoPackage> fetchAll();
	
	public EhmoPackage getEhmoPackageById(int id);
	
	public void save(EhmoPackage ehmoPackage);

	public void delete(EhmoPackage ehmoPackage);

	public void update(EhmoPackage ehmoPackage);

}
