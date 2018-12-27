package org.calminfotech.admin.boInterface;

import java.util.List;

import org.calminfotech.admin.forms.AilmentClassificationForm;
import org.calminfotech.system.models.AilmentClassification;

public interface AilmentClassificationBo {

	public List<AilmentClassification> fetchAll();

	public AilmentClassification getAilmentClassificationById(int id);

	public AilmentClassification save(AilmentClassificationForm ailmentClassificationForm);

	public void delete(AilmentClassification ailmentClassification);

	public void update(AilmentClassificationForm ailmentClassificationForm);
}
