package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.system.models.LoginSectionPoint;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class LoginSectionPointList extends CustomHibernateDaoSupport{
	
	
	public List<LoginSectionPoint> fetchAll() {
		List<LoginSectionPoint> list = getHibernateTemplate().find("from LoginSectionPoint");
		return list;
	}
	
	
	public LoginSectionPoint getLoginSectionPointById(int id) {
		List list = getHibernateTemplate().find(
				"from LoginSectionPoint where id = ?", id);
		if (list.size() > 0)
			return (LoginSectionPoint) list.get(0);
		return null;
	}


}
