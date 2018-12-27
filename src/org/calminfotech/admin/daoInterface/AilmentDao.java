package org.calminfotech.admin.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Ailment;

public interface AilmentDao {

	public List<Ailment> fetchAll();

	public Ailment getAilmentById(int id);

	public void save(Ailment ailment);

	public void delete(Ailment ailment);

	public void update(Ailment ailment);

	public List<Ailment> fetchLikeName(String q);
}
