package org.calminfotech.inventory.daoInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.models.Vendor;

public interface VendorDao {

	public void save(Vendor vendor);

	public void update(Vendor vendor);

	public List<Vendor> getVendorsList() throws RecordNotFoundException;

	public Vendor getVendorDetailsById(int id) throws RecordNotFoundException;
	
	public void delete(Vendor vendor);

	public String getLastVendorCodeGenWithShortName(String vendorShortNameCode) ;
}
