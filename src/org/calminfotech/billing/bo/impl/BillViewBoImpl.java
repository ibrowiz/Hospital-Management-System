package org.calminfotech.billing.bo.impl;

import org.calminfotech.billing.boInterface.BillViewBo;
import org.calminfotech.billing.daoInterface.BillViewDao;
import org.calminfotech.billing.models.BillView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillViewBoImpl implements BillViewBo {
	
	@Autowired
	private BillViewDao billViewDao;

	@Override
	public BillView getBillViewByGlobalItemUofMeasureSchemeId(Integer GlobalItemId,
			Integer UnitOfMeasureId,Integer schemeId) {
		return this.billViewDao.getBillViewByGlobalItemUofMeasureSchemeId(GlobalItemId, UnitOfMeasureId,schemeId);
	}

}
