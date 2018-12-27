package org.calminfotech.system.bo.impl;

import org.calminfotech.system.boInterface.BillingItemPriceDetailBo;
import org.calminfotech.system.daoInterface.BillingItemPriceDetailDao;
import org.calminfotech.system.models.BillingItemPriceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillingItemPriceDetailBoImpl implements BillingItemPriceDetailBo{

	@Autowired
	private BillingItemPriceDetailDao billingItemPriceDetailDao;
	
	@Override
	public void save(BillingItemPriceDetail billingItemPriceDetail) {
		// TODO Auto-generated method stub
		this.billingItemPriceDetailDao.save(billingItemPriceDetail);
	}

	@Override
	public BillingItemPriceDetail getBillingItemPriceDetailById(int id) {
		// TODO Auto-generated method stub
		return this.billingItemPriceDetailDao.getBillingItemPriceDetailById(id);
	}

	@Override
	public void delete(BillingItemPriceDetail billingItemPriceDetail) {
		// TODO Auto-generated method stub
		this.billingItemPriceDetailDao.delete(billingItemPriceDetail);	
	}
}
