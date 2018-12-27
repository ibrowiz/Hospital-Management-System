package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.system.models.BillingSchemeItem;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class PaymentSchemeItemList extends CustomHibernateDaoSupport{

	
	public List<BillingSchemeItem> fetchAll() {
		List list = getHibernateTemplate().find("from PaymentSchemeItem");
		return (List<BillingSchemeItem>) list;
	}
	
	
	public BillingSchemeItem getPaymentSchemeItemById(int id) {
		List list = getHibernateTemplate().find("from PaymentSchemeItem where id = ?",
				id);
		if (list.size() > 0)
			return (BillingSchemeItem) list.get(0);
		return null;
	}
	
}
