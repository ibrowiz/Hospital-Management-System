package org.calminfotech.consultation.forms;





public class ExaminationCategoryForm {
	
	private Integer ExamCategoryId;
	
	private Integer ParentId;
	
	private String name;
	
	/*private String minimumValue;
		
	private String maximumValue;*/
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return ParentId;
	}
	public void setParentId(Integer parentId) {
		ParentId = parentId;
	}
	
	
	/*public String getMinimumValue() {
		return minimumValue;
	}
	public void setMinimumValue(String minimumValue) {
		this.minimumValue = minimumValue;
	}
	public String getMaximumValue() {
		return maximumValue;
	}
	public void setMaximumValue(String maximumValue) {
		this.maximumValue = maximumValue;
	}*/
	public Integer getExamCategoryId() {
		return ExamCategoryId;
	}
	public void setExamCategoryId(Integer examCategoryId) {
		ExamCategoryId = examCategoryId;
	}
	
}
