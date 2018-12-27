package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.billing.models.BillUnitOfMeasure;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitOfMeasure;

public interface GlobalItemUnitOfMeasureBo {
	
public List<GlobalItemUnitOfMeasure> fetchAll();
	
	public GlobalItemUnitOfMeasure getGlobalItemMeasureById(int id);
	
	public void save(GlobalItemUnitOfMeasure measure);
	
	public void delete(GlobalItemUnitOfMeasure measure);
	
	public void update(GlobalItemUnitOfMeasure measure);
		
public List<BillUnitOfMeasure> listGlobalItemViaMeasure(Integer id);
public GlobalItem fetchUnitOfMesureViaGlobalItem(int id);
	
	public BillUnitOfMeasure fetchGlobalItemViaMeasure(Integer id);

}
