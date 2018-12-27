package org.calminfotech.admin.daoInterface;

import java.util.List;

import org.calminfotech.system.models.AilmentClassification;

public interface AilmentClassificationDao {

	public List<AilmentClassification> fetchAll();

	public AilmentClassification getAilmentClassificationById(int id);

	public void save(AilmentClassification ailmentClassification); 

	public void delete(AilmentClassification ailmentClassification);

	public void update(AilmentClassification ailmentClassification);

}
