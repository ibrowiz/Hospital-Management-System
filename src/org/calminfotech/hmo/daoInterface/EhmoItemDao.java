package org.calminfotech.hmo.daoInterface;

import java.util.List;

import org.calminfotech.hmo.models.EhmoItem;

public interface EhmoItemDao {

	public List<EhmoItem> fetchAll();

	public EhmoItem getEhmoItemById(int id);

	public void save(EhmoItem ehmoItem);

	public void delete(EhmoItem ehmoItem);

	public void update(EhmoItem ehmoItem);

}
