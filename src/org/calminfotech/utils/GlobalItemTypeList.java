package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class GlobalItemTypeList extends CustomHibernateDaoSupport{
	
	
	
	public List<GlobalItemType> fetchAll() {
		List<GlobalItemType> list = getHibernateTemplate().find("from GlobalItemType");
		return list;
	}

	public GlobalItemType getItemTypeById(int id) {
		List list = getHibernateTemplate().find(
				"from GlobalItemType where globalitemTypeId = ?", id);
		if (list.size() > 0)
			return (GlobalItemType) list.get(0);
		return null;
	}

}
