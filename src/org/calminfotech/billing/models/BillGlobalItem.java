package org.calminfotech.billing.models;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "global_item")
@SQLDelete(sql = "UPDATE global_item SET is_deleted = 1 WHERE global_item_id = ?")
@Where(clause = "is_deleted <> 1")
public class BillGlobalItem {
	
	@Id
	@GeneratedValue
	@Column(name = "global_item_id")
	private Integer globalItemId;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@OneToMany
	@JoinColumn(name = "global_item_id")
	private Set<BillSchemeItem> billSchemeItem;
	
	@OneToMany
	@JoinColumn(name = "global_item_id")
	private Set<BillItemPrice> billItemPrice;

	@Column(name = "name")
	private String globalName;

	@Column(name = "description")
	private String globalDescription;

	@Column(name = "item_type")
	private Integer itemType;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "status")
	private String status;

	public Integer getGlobalItemId() {
		return globalItemId;
	}

	public void setGlobalItemId(Integer globalItemId) {
		this.globalItemId = globalItemId;
	}
	
	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Set<BillSchemeItem> getBillSchemeItem() {
		return billSchemeItem;
	}

	public void setBillSchemeItem(Set<BillSchemeItem> billSchemeItem) {
		this.billSchemeItem = billSchemeItem;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getGlobalName() {
		return globalName;
	}

	public void setGlobalName(String globalName) {
		this.globalName = globalName;
	}

	public String getGlobalDescription() {
		return globalDescription;
	}

	public void setGlobalDescription(String globalDescription) {
		this.globalDescription = globalDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<BillItemPrice> getBillItemPrice() {
		return billItemPrice;
	}

	public void setBillItemPrice(Set<BillItemPrice> billItemPrice) {
		this.billItemPrice = billItemPrice;
	}
	
	
	
}
