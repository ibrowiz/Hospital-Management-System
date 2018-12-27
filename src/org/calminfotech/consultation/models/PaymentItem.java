package org.calminfotech.consultation.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.utils.models.Organisation;

@Entity
public class PaymentItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "item_description")
	private String itemDescription;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "hmo_package_item_type")
	private String hmoPackageItemType;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "hmo_package_id")
	private EhmoPackages hmoPackage;

	@ManyToOne
	@JoinColumn(name = "visit_id")
	private Visit visit;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	// the item you are paying for
	@JoinColumn(name = "hmo_package_item_id")
	private Integer hmoPackageItemId;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription
	 *            the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the hmoPackageItemType
	 */
	public String getHmoPackageItemType() {
		return hmoPackageItemType;
	}

	/**
	 * @param hmoPackageItemType
	 *            the hmoPackageItemType to set
	 */
	public void setHmoPackageItemType(String hmoPackageItemType) {
		this.hmoPackageItemType = hmoPackageItemType;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the hmoPackage
	 */
	public EhmoPackages getHmoPackage() {
		return hmoPackage;
	}

	/**
	 * @param hmoPackage
	 *            the hmoPackage to set
	 */
	public void setHmoPackage(EhmoPackages hmoPackage) {
		this.hmoPackage = hmoPackage;
	}

	/**
	 * @return the hmoPackageItemId
	 */
	public Integer getHmoPackageItemId() {
		return hmoPackageItemId;
	}

	/**
	 * @param hmoPackageItemId
	 *            the hmoPackageItemId to set
	 */
	public void setHmoPackageItemId(Integer hmoPackageItemId) {
		this.hmoPackageItemId = hmoPackageItemId;
	}

	/**
	 * @return the visit
	 */
	public Visit getVisit() {
		return visit;
	}

	/**
	 * @param visit
	 *            the visit to set
	 */
	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	/**
	 * @return the organisation
	 */
	public Organisation getOrganisation() {
		return organisation;
	}

	/**
	 * @param organisation
	 *            the organisation to set
	 */
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

}
