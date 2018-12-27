package org.calminfotech.billing.dao.impl;

import java.util.List;

import org.calminfotech.billing.daoInterface.BillViewDao;
import org.calminfotech.billing.models.BillView;
import org.calminfotech.consultation.models.Examination;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class BillViewDaoImpl implements BillViewDao  {
	

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BillView getBillViewByGlobalItemUofMeasureSchemeId(Integer GlobalItemId,
			Integer UnitOfMeasureId, Integer schemeId) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM BillView WHERE  globalItemId = ? and unitOfMeasureId =? and billId = ?")
				.setParameter(0, GlobalItemId).setParameter(1, UnitOfMeasureId).setParameter(2, schemeId).list();

		if (list.size() > 0)
			return (BillView) list.get(0);

		return null;
	}

}
