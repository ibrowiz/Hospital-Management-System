package org.calminfotech.admin.boInterface;

import java.util.List;

import org.calminfotech.admin.forms.DrugForm;
import org.calminfotech.system.models.Drug;

public interface DrugBo {

	public List<Drug> fetchAll();

	public Drug getDrugById(int id);

	public Drug save(DrugForm drugForm);

	public void delete(Drug drug);

	public void update(DrugForm drugForm);

	public List<Drug> fetchLikeName(String q);
}
