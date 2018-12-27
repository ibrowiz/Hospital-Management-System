package org.calminfotech.setup.daoImpl;

import java.util.List;

import org.calminfotech.setup.daoInterface.UnitListDao;
import org.calminfotech.setup.models.AllergyCategoryList;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.setup.models.UnitList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UnitListDaoImpl implements UnitListDao{
	

	@Autowired
	private SessionFactory sessionFactory;
	

@Override
	public List<UnitList> fetchAllByOrganisationId(Integer organisationId) {
		// TODO Auto-generated method stub
		List<UnitList> list = sessionFactory.getCurrentSession()
				   .createQuery("from UnitList where organisationId = ?").setParameter(0,organisationId).list();
		return list;
	}


	@Override
	public UnitList getUnitListById(int id) {
		List<UnitList> list = sessionFactory.getCurrentSession()
                .createQuery("from UnitList where rowId = ? ")
                .setParameter(0, id).list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	}

}
