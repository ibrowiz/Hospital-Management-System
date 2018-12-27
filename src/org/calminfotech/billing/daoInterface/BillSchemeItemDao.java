package org.calminfotech.billing.daoInterface;

import java.util.List;

import org.calminfotech.billing.models.BillSchemeItem;
import org.calminfotech.hmo.forms.EhmoPackageItemForm;

import org.calminfotech.hmo.models.EhmoPackageItem;


public interface BillSchemeItemDao {

	public List<BillSchemeItem> fetchAll();

	public BillSchemeItem getBillSchemeItemById(int id);
	
	public void save(BillSchemeItem billSchemeItem);
	
	public void delete(BillSchemeItem billSchemeItem);

	public void update(BillSchemeItem billSchemeItem);
	
}