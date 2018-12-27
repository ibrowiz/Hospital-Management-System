package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;

public class SettingForm {

	@NotBlank(message = "Field cannot be left empty!")
	private String hospitalName;

	@NotBlank(message = "Field cannot be left empty!")
	private String hospitalAddress;

	@NotBlank(message = "Field cannot be left empty!")
	private String hospitalEmail;

	@NotBlank(message = "Field cannot be left empty!")
	private String systemEmail;

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getHospitalEmail() {
		return hospitalEmail;
	}

	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

}
