package org.calminfotech.consultation.forms;

import org.calminfotech.utils.annotations.FormFieldName;
import org.hibernate.validator.constraints.NotEmpty;

public class VisitVitalsForm {

	@FormFieldName(name = "Visit")
	private Integer visitId;

	@FormFieldName(name = "Temperature")
	@NotEmpty(message = "Field  cannot be empty")
	private String temperature;

	@FormFieldName(name = "Blood Pressure")
	@NotEmpty(message = "Field  cannot be empty")
	private String bloodPressure;

	@FormFieldName(name = "Heart Rate")
	@NotEmpty(message = "Field  cannot be empty")
	private String heartRate;

	@FormFieldName(name = "Respiration")
	@NotEmpty(message = "Field  cannot be empty")
	private String respiration;

	@FormFieldName(name = "Others")
	private String others;

	/**
	 * @return the visitId
	 */
	public Integer getVisitId() {
		return visitId;
	}

	/**
	 * @param visitId
	 *            the visitId to set
	 */
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	/**
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the bloodPressure
	 */
	public String getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * @param bloodPressure
	 *            the bloodPressure to set
	 */
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	/**
	 * @return the heartRate
	 */
	public String getHeartRate() {
		return heartRate;
	}

	/**
	 * @param heartRate
	 *            the heartRate to set
	 */
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}

	/**
	 * @return the respiration
	 */
	public String getRespiration() {
		return respiration;
	}

	/**
	 * @param respiration
	 *            the respiration to set
	 */
	public void setRespiration(String respiration) {
		this.respiration = respiration;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

}
