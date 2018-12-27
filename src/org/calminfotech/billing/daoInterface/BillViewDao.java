package org.calminfotech.billing.daoInterface;

import org.calminfotech.billing.models.BillView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface BillViewDao {
	
	public BillView getBillViewByGlobalItemUofMeasureSchemeId(Integer GlobalItemId, Integer UnitOfMeasureId, Integer schemeId);

}
