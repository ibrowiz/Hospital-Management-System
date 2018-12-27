package org.calminfotech.hmo.daoInterface;

import java.util.List;

import org.calminfotech.hmo.models.EhmoPackageItem;

public interface EhmoPackageItemDao {

	public List<EhmoPackageItem> fetchAll();

	public EhmoPackageItem getEhmoItemById(int id);

	public void save(EhmoPackageItem ehmoItem);

	public void delete(EhmoPackageItem ehmoItem);

	public void update(EhmoPackageItem ehmoItem);

}
