package org.calminfotech.setup.daoInterface;

import java.util.List;

import org.calminfotech.setup.models.HrUnitView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface HrUnitViewDao {

	public List<HrUnitView> fetchAll();

	public HrUnitView getUnitViewById(int id);
	
	public List<HrUnitView> fetchAllByOrganisation(Integer organisationid);
	
}
