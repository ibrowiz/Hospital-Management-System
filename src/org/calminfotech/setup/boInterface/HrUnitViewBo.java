package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.setup.models.HrUnitView;

public interface HrUnitViewBo {
	
	public List<HrUnitView> fetchAll();

	public HrUnitView getUnitViewById(int id);
																																																																							
	public List<HrUnitView> fetchAllByOrganisation(Integer organisationid);

}
