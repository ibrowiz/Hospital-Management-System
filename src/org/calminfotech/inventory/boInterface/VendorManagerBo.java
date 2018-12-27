package org.calminfotech.inventory.boInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.error.custom.exception.UnableToGenerateVendorCodeException;
import org.calminfotech.inventory.forms.VendorForm;
import org.calminfotech.inventory.models.Vendor;

public interface VendorManagerBo {

	public Vendor save(VendorForm vendorForm) throws UnableToGenerateVendorCodeException;

	public List<Vendor> getVendorsList() throws RecordNotFoundException;

	public Vendor getVendorDetailsById(int id) throws RecordNotFoundException;
	
	public Vendor update(VendorForm vendorForm);

	public void delete(Vendor vendor);
}
