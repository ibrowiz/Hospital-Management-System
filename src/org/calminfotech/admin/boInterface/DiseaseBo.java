package org.calminfotech.admin.boInterface;

import java.util.List;

import org.calminfotech.admin.forms.DiseaseForm;
import org.calminfotech.system.models.Disease;

public interface DiseaseBo {

	public List<Disease> fetchAll();

	public Disease getDiseaseById(int id);

	public Disease save(DiseaseForm diseaseForm);

	public void delete(Disease disease);

	public void update(DiseaseForm diseaseForm);
}
