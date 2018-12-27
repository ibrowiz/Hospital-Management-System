package org.calminfotech.billing.daoInterface;

	import java.util.List;

import org.calminfotech.billing.models.BillItemPrice;


	public interface BillItemPriceDao {

		public List<BillItemPrice> fetchAll();

		public BillItemPrice getBillItemPriceById(int id);
		
		public void save(BillItemPrice billItemPrice);
		
		public void delete(BillItemPrice billItemPrice);

		public void update(BillItemPrice billItemPrice);
		
}
