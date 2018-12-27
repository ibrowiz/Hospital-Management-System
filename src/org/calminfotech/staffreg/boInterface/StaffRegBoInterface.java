package org.calminfotech.staffreg.boInterface;

import java.util.List;

import org.calminfotech.setup.models.Allergy;
import org.calminfotech.staffreg.forms.StaffRegForm;
import org.calminfotech.staffreg.models.StaffRegistration;

public interface StaffRegBoInterface {
	
	public List<StaffRegistration> fetchAllByOrganisation(int organisationId);
	
	public StaffRegistration getStaffById(int id);

	public StaffRegistration save(StaffRegForm staffRegForm);

	public void delete(StaffRegistration staffRegistration);
	
	public List<StaffRegistration> fetchStaffByUnitId(int unitId);

	public void update(StaffRegForm staffRegForm);

}
