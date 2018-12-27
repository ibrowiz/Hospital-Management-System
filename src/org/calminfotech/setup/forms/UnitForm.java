package org.calminfotech.setup.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class UnitForm {
	
private Integer UnitId;

private Integer unitCategoryId;
	
	private boolean attendQ;
	
	private Integer billingScheme;
	
	private Integer pointId;

	public Integer getUnitId() {
		return UnitId;
	}

	public void setUnitId(Integer unitId) {
		UnitId = unitId;
	}



	public boolean isAttendQ() {
		return attendQ;
	}

	public void setAttendQ(boolean attendQ) {
		this.attendQ = attendQ;
	}

	public Integer getBillingScheme() {
		return billingScheme;
	}

	public void setBillingScheme(Integer billingScheme) {
		this.billingScheme = billingScheme;
	}

	

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	public Integer getUnitCategoryId() {
		return unitCategoryId;
	}

	public void setUnitCategoryId(Integer unitCategoryId) {
		this.unitCategoryId = unitCategoryId;
	}

	

	

}
