package org.calminfotech.billing.bo.impl;

import java.util.List;
import org.calminfotech.billing.boInterface.BillItemPriceBo;
import org.calminfotech.billing.daoInterface.BillItemPriceDao;
import org.calminfotech.billing.models.BillItemPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillItemPriceBoImpl implements BillItemPriceBo {

	@Autowired
	private BillItemPriceDao billItemPriceDao;

	/*@Autowired
	private UserIdentity userIdentity;*/

	@Override
	public List<BillItemPrice> fetchAll() {
		// TODO Auto-generated method stub
		return billItemPriceDao.fetchAll();
	}

	@Override
	public BillItemPrice getBillItemPriceById(int id) {
		// TODO Auto-generated method stub
		return billItemPriceDao.getBillItemPriceById(id);
	}

	@Override
	public void save(BillItemPrice billItemPrice) {
	  this.billItemPriceDao.save(billItemPrice);
	

	}
	@Override
	public void delete(BillItemPrice billItemPrice) {
		// TODO Auto-generated method stub
		this.billItemPriceDao.delete(billItemPrice);
	}

	@Override
	public void update(BillItemPrice billItemPrice) {
		this.billItemPriceDao.update(billItemPrice);
      
		}
}
