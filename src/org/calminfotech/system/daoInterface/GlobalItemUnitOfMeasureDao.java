package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.billing.models.BillUnitOfMeasure;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitOfMeasure;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface GlobalItemUnitOfMeasureDao {
	
public List<GlobalItemUnitOfMeasure> fetchAll();
	
	public GlobalItemUnitOfMeasure getGlobalItemMeasureById(int id);
	
	public void save(GlobalItemUnitOfMeasure measure);
	
	public void delete(GlobalItemUnitOfMeasure measure);
	
	public void update(GlobalItemUnitOfMeasure measure);
	
	//public GlobalItemUnitOfMeasure measure getByPointAndItem(Integer itemId, VisitWorkflowPoint point); 
	
	//public List<GlobalItemPoint> fetchByPoint(VisitWorkflowPoint point); 
	
	//public GlobalItemPoint fetchGobalViaGLobalItemPoint();
	public GlobalItem fetchUnitOfMesureViaGlobalItem(int id);
	
	public List<BillUnitOfMeasure> listGlobalItemViaMeasure(Integer id);
	
	public BillUnitOfMeasure fetchGlobalItemViaMeasure(Integer id);

}
