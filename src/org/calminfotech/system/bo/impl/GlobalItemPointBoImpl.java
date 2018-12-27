package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.GlobalItemPointBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.daoInterface.GlobalItemPointDao;
import org.calminfotech.system.models.GlobalItemPoint;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalItemPointBoImpl implements GlobalItemPointBo {
	
	@Autowired
	private GlobalItemPointDao globalItemPointDao;
	
	@Autowired
	private VisitWorkflowPointBo vWorkflowPointBo;
	
	@Autowired
	private VisitWorkflowPointBo pointBo;

	@Override
	public List<GlobalItemPoint> fetchAll() {
		// TODO Auto-generated method stub
		return this.globalItemPointDao.fetchAll();
	}

	@Override
	public GlobalItemPoint getGlobalItemPointById(int id) {
		// TODO Auto-generated method stub
		return this.globalItemPointDao.getGlobalItemPointById(id);
	}

	@Override
	public void save(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.globalItemPointDao.save(point);
	}

	@Override
	public void delete(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.globalItemPointDao.delete(point);
	}

	@Override
	public void update(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.globalItemPointDao.update(point);
	}

	@Override
	public GlobalItemPoint getByPointAndItem(Integer itemId, Integer point) {
		// TODO Auto-generated method stub
		return globalItemPointDao.getByPointAndItem(itemId, pointBo.getWorkflowPointById(point));
	}

	@Override
	public VisitWorkflowPoint fetchGlobalItemViaPoint() {
		// TODO Auto-generated method stub
		return this.globalItemPointDao.fetchGlobalItemViaPoint();
	}

	

//	@Override
//	public List<GlobalItemPoint> fetchByPoint(Integer point) {
//		VisitWorkflowPoint vWorkflowPoint = this.vWorkflowPointBo.getWorkflowPointById(point);
//		return globalItemPointDao.fetchByPoint(vWorkflowPoint);
//	}

	/*@Override
	public GlobalItemPoint fetchByFrontDesk() {
		// TODO Auto-generated method stub
		return globalItemPointDao.fetchByFrontDesk();
	}*/
	
	

}
