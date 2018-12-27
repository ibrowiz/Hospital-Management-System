package org.calminfotech.billing.boInterface;

	import java.util.List;

import org.calminfotech.billing.models.BillItemPrice;


	public interface BillItemPriceBo {

		public List<BillItemPrice> fetchAll();

		public BillItemPrice getBillItemPriceById(int id);
		
		public void save(BillItemPrice billItemPrice);
		
		public void delete(BillItemPrice billItemPrice);

		public void update(BillItemPrice billItemPrice);
		
}
