package org.calminfotech.inventory.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.utils.CommonVariables;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_vendors")
@SQLDelete(sql = "UPDATE inventory_vendors SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class Vendor extends CommonVariables implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "vendor_code", unique = true)
	private String vendorId;

	@Column(name = "vendor_name")
	private String vendorName;

	@Column(name = "email")
	private String email;

	@Column(name = "telephone_no")
	private String telephoneNumber;

	@Column(name = "contact_address")
	private String contactAddress;

	@Column(name = "url")
	private String url;

	@Column(name = "fax")
	private String fax;



	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendor", cascade = CascadeType.ALL)
	private Set<StockIn> batchSupplies;

	public String getVendorId() {
		return vendorId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}


	public Set<StockIn> getBatchSupplies() {
		return batchSupplies;
	}

	public void setBatchSupplies(Set<StockIn> batchSupplies) {
		this.batchSupplies = batchSupplies;
	}
}
