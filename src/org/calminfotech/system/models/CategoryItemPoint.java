package org.calminfotech.system.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category_item_point")
public class CategoryItemPoint {
	
	@Id
	@GeneratedValue
	@Column(name = "itemPointId")
	private Integer itemPointId;
	
	@ManyToOne
	@JoinColumn(name = "flowPointId")
	private VisitWorkflowPoint flowPoint;
	
	/*@ManyToOne
	@JoinColumn(name = "categoryItemId")
	private CategoryItem categoryItem;*/

	//Getters and Setters
	public Integer getItemPointId() {
		return itemPointId;
	}

	public void setItemPointId(Integer itemPointId) {
		this.itemPointId = itemPointId;
	}

	public VisitWorkflowPoint getFlowPoint() {
		return flowPoint;
	}

	public void setFlowPoint(VisitWorkflowPoint flowPoint) {
		this.flowPoint = flowPoint;
	}

	/*public CategoryItem getCategoryItem() {
		return categoryItem;
	}

	public void setCategoryItem(CategoryItem categoryItem) {
		this.categoryItem = categoryItem;
	}*/
}
