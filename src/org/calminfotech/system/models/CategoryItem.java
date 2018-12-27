package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category_item")
public class CategoryItem {
	
	@Id
	@GeneratedValue
	@Column(name = "categoryItemId")
	private Integer itemId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "description")
	private String itemDesc;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private GlobalItemCategory category;
	
	@ManyToOne
	@JoinColumn(name = "catTypeId")
	private CategoryTypeXX categoryType;
	
	@OneToMany
	@JoinColumn(name = "categoryItemId")
	private Set<CategoryItemUnit> categoryItemUnit = new HashSet<CategoryItemUnit>();
	
	@OneToMany
	@JoinColumn(name = "categoryItemId")
	private Set<CategoryItemPoint> categoryItemPoint = new HashSet<CategoryItemPoint>();
	
	@ManyToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private Set<HmoPckSubService> pckSubService = new HashSet<HmoPckSubService>();
	
	//Getters and Setters
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
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

	public GlobalItemCategory getCategory() {
		return category;
	}

	public void setCategory(GlobalItemCategory category) {
		this.category = category;
	}

	public CategoryTypeXX getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryTypeXX categoryType) {
		this.categoryType = categoryType;
	}

	public Set<CategoryItemUnit> getCategoryItemUnit() {
		return categoryItemUnit;
	}

	public void setCategoryItemUnit(Set<CategoryItemUnit> categoryItemUnit) {
		this.categoryItemUnit = categoryItemUnit;
	}

	public Set<CategoryItemPoint> getCategoryItemPoint() {
		return categoryItemPoint;
	}

	public void setCategoryItemPoint(Set<CategoryItemPoint> categoryItemPoint) {
		this.categoryItemPoint = categoryItemPoint;
	}

	public Set<HmoPckSubService> getPckSubService() {
		return pckSubService;
	}

	public void setPckSubService(Set<HmoPckSubService> pckSubService) {
		this.pckSubService = pckSubService;
	}
}
