package org.calminfotech.utils;

import java.util.List;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class PaymentSchemeList extends CustomHibernateDaoSupport{
	
	
	public List<BillingScheme> fetchAll() {
		List list = getHibernateTemplate().find("from PaymentScheme");
		return (List<BillingScheme>) list;
	}
	
	
	public BillingScheme getPaymentSchemeById(int id) {
		List list = getHibernateTemplate().find("from PaymentScheme where id = ?",
				id);
		if (list.size() > 0)
			return (BillingScheme) list.get(0);
		return null;
	}

}
