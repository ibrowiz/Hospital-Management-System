package org.calminfotech.system.daoInterface;

import org.calminfotech.system.models.Setting;

public interface SettingDao {

	public Setting getSetting();

	public void update(Setting setting);

}
