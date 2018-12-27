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
	name = "spGetDeleteResult",
	query = "{CALL sp_delete_labtest_result(:testid)}",
	callable = true,
	resultClass = LabDeleteResult.class
)
@Table(name = "laboratory_result")
@org.hibernate.annotations.Entity(dynamicInsert = true) 
public class LabDeleteResult implements java.io.Serializable {

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

}
