package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.BillingSchemeForm;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.user.forms.LanguageForm;
import org.calminfotech.user.models.Language;
import org.calminfotech.utils.models.Organisation;


public interface BillingSchemeBo {
	
	
	public List<BillingScheme> fetchAllByOrganisation();
	
	public List<BillingScheme> fetchAllByType();

	public BillingScheme getBillingSchemeById(int id);

	public void save(BillingSchemeForm billingSchemeform);

	public void delete(BillingScheme billingSchemeform);

	public void update(BillingSchemeForm billingSchemeform);
		
	public List<BillingScheme> fetchAll();



	
	

}
