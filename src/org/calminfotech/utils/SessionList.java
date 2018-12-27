package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.system.models.LoginSection;

import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class SessionList extends CustomHibernateDaoSupport{
	
	
	public List<LoginSection> fetchAll() {
		List<LoginSection> list = getHibernateTemplate().find("from LoginSection");
		return list;
	}
	
	public LoginSection getLoginSectionById(int id) {
		List list = getHibernateTemplate().find(
				"from LoginSection where id = ?", id);
		if (list.size() > 0)
			return (LoginSection) list.get(0);
		return null;
	}
	
	

}
