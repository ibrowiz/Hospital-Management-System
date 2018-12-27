package org.calminfotech.hmo.boInterface;

import java.util.List;

import org.calminfotech.hmo.forms.EhmoItemForm;

import org.calminfotech.hmo.models.EhmoItem;


public interface EhmoItemBo {

	public List<EhmoItem> fetchAll();

	public EhmoItem getEhmoItemById(int id);
	
	public void save(EhmoItem ehmoItem);
	
	public void delete(EhmoItem ehmoItem);

	public void update(EhmoItem ehmoItem);
	
}