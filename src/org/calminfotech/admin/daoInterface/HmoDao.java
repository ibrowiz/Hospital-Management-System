package org.calminfotech.admin.daoInterface;

import java.util.List;

import org.calminfotech.admin.forms.DataTableForm;
import org.calminfotech.system.models.Hmo;

public interface HmoDao {

	public List<Hmo> fetchAll();

	public Hmo getHmoById(int id);

	public void save(Hmo hmo);

	public void delete(Hmo hmo);

	public void update(Hmo hmo);

	public String dataTableQuery(DataTableForm dataTableForm);

}
