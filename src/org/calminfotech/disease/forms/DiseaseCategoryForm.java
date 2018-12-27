package org.calminfotech.disease.forms;

public class DiseaseCategoryForm {
	
private Integer diseaseCategoryId;
	
	private Integer parentId;
	
	private String name;

	public Integer getDiseaseCategoryId() {
		return diseaseCategoryId;
	}

	public void setDiseaseCategoryId(Integer diseaseCategoryId) {
		this.diseaseCategoryId = diseaseCategoryId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
