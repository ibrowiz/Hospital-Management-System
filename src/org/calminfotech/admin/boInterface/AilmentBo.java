package org.calminfotech.admin.boInterface;

import java.util.List;

import org.calminfotech.admin.forms.AilmentForm;
import org.calminfotech.system.models.Ailment;

public interface AilmentBo {

	public List<Ailment> fetchAll();

	public Ailment getAilmentById(int id);

	public Ailment save(AilmentForm ailmentForm);

	public void delete(Ailment ailment);

	public void update(AilmentForm ailmentForm);

	public List<Ailment> fetchLikeName(String q);
}
