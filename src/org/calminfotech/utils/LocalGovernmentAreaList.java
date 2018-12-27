package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.springframework.stereotype.Service;

@Service
public class LocalGovernmentAreaList extends CustomHibernateDaoSupport {

	public List<LocalGovernmentArea> fetchAll() {
		List list = getHibernateTemplate().find("from LocalGovernmentArea");
		return (List<LocalGovernmentArea>) list;
	}

	public LocalGovernmentArea getLgaById(int id) {

		List list = getHibernateTemplate().find(
				"from LocalGovernmentArea where localGovernmentAreaId = ?", id);
		if (list.size() > 0)
			return (LocalGovernmentArea) list.get(0);
		return null;
	}
}
