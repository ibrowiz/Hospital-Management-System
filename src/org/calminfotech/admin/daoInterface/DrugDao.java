package org.calminfotech.admin.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Drug;

public interface DrugDao {

	public List<Drug> fetchAll();

	public Drug getDrugById(int id);

	public void save(Drug drug);

	public void delete(Drug drug);

	public void update(Drug drug);

	public List<Drug> fetchLikeName(String q);
}
