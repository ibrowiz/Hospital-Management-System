package org.calminfotech.admin.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Disease;

public interface DiseaseDao {

	public List<Disease> fetchAll();

	public Disease getDiseaseById(int id);

	public void save(Disease disease);

	public void delete(Disease disease);

	public void update(Disease disease);

}
