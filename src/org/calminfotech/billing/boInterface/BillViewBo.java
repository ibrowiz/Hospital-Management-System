package org.calminfotech.billing.boInterface;

import org.calminfotech.billing.models.BillView;

public interface BillViewBo {
	
	public BillView getBillViewByGlobalItemUofMeasureSchemeId(Integer GlobalItemId, Integer UnitOfMeasureId, Integer schemeId);

}
