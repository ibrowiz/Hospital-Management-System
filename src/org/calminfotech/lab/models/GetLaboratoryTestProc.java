package org.calminfotech.lab.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
	name = "splaboratorytest",
	query = "{CALL sp_laboratorytest(:testid)}",
	callable = true,
	resultClass = GetLaboratoryTestProc.class
)
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Laboratory_result_setup")
public class GetLaboratoryTestProc implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "result_setup_id", unique = true, nullable = false)
	private Integer setupId;

	@Column(name = "test_id", nullable = true)
	private String testId;
	
	
	@Column(name = "result_name", nullable = true)
	private String result_name;
	
	@Column(name = "actual_value", nullable = true)
	private String actual_value;
	
	@Column(name = "final_result", nullable = true)
	private String finalResult;
	
	@Column(name = "minimum_value", nullable = true)
	private String minimum_value;
	
	@Column(name = "maximum_value", nullable = true)
	private String maximum_value;

	public Integer getSetupId() {
		return setupId;
	}

	public void setSetupId(Integer setupId) {
		this.setupId = setupId;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getResult_name() {
		return result_name;
	}

	public void setResult_name(String result_name) {
		this.result_name = result_name;
	}

	public String getActual_value() {
		return actual_value;
	}

	public void setActual_value(String actual_value) {
		this.actual_value = actual_value;
	}

	public String getMinimum_value() {
		return minimum_value;
	}

	public void setMinimum_value(String minimum_value) {
		this.minimum_value = minimum_value;
	}

	public String getMaximum_value() {
		return maximum_value;
	}

	public void setMaximum_value(String maximum_value) {
		this.maximum_value = maximum_value;
	}

	public String getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

	

	

}
