package org.calminfotech.consultation.forms;


public class ExaminationForm {
	
private Integer examId;
	
private Integer examCategoryId;

private String name;

private String minimumValue;

private String maximumValue;
	
private String description;

public Integer getExamId() {
	return examId;
}

public void setExamId(Integer examId) {
	this.examId = examId;
}

public Integer getExamCategoryId() {
	return examCategoryId;
}

public void setExamCategoryId(Integer examCategoryId) {
	this.examCategoryId = examCategoryId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getMinimumValue() {
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
}

}
