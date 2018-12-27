package org.calminfotech.utils;

import org.calminfotech.system.models.Setting;
import org.calminfotech.utils.models.Organisation;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SystemSetting {

	private Setting setting;
	private Organisation organisation;

	public SystemSetting() {
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	
	//Get the user's organisation and make it available through out the system

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
}
