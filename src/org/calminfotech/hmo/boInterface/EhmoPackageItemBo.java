package org.calminfotech.hmo.boInterface;

import java.util.List;

import org.calminfotech.hmo.forms.EhmoPackageItemForm;

import org.calminfotech.hmo.models.EhmoPackageItem;


public interface EhmoPackageItemBo {

	public List<EhmoPackageItem> fetchAll();

	public EhmoPackageItem getEhmoItemById(int id);
	
	public void save(EhmoPackageItem ehmoItem);
	
	public void delete(EhmoPackageItem ehmoItem);

	public void update(EhmoPackageItem ehmoItem);
	
}