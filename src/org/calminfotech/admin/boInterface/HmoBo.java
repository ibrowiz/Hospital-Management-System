package org.calminfotech.admin.boInterface;

import java.util.List;

import org.calminfotech.admin.forms.DataTableForm;
import org.calminfotech.system.forms.HmoForm;
import org.calminfotech.system.models.Hmo;

public interface HmoBo {

	public List<Hmo> fetchAll();

	public Hmo getHmoById(int id);

	public Hmo save(HmoForm hmoForm);

	public void delete(Hmo hmo);

	public void update(HmoForm hmoForm);

	public String dataTableQuery(DataTableForm dataTableForm);
}
