package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.SettingDao;
import org.calminfotech.system.models.Setting;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SettingDaoImpl implements SettingDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Setting getSetting() {
		// TODO Auto-generated method stub
		List<Setting> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Setting").list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	// Create the record or update it. There should be only one object for
	// setting
	@Override
	public void update(Setting setting) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(setting);
	}

}
