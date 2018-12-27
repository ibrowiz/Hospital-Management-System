package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.GlobalUnitofMeasureXXX;

import org.springframework.stereotype.Service;


@Service
public class UnitbyChoiceList extends CustomHibernateDaoSupport{
	
	
	public List<GlobalUnitofMeasureXXX> fetchAll() {
		List list = getHibernateTemplate().find("from GlobalUnitofMeasure");
		return (List<GlobalUnitofMeasureXXX>) list;
	}
	
	public GlobalUnitofMeasureXXX getGlobalUnitofMeasurebyId(int id) {
		List list = getHibernateTemplate().find("from GlobalUnitofMeasure where id = ?",
				id);
		if (list.size() > 0)
			return (GlobalUnitofMeasureXXX) list.get(0);
		return null;
	}
	

}
