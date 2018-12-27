package org.calminfotech.setup.bo.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.setup.boInterface.UnitBo;
import org.calminfotech.setup.boInterface.UnitBo;
import org.calminfotech.setup.boInterface.UnitCategoryBo;
import org.calminfotech.setup.boInterface.UnitCategoryViewBo;
import org.calminfotech.setup.daoInterface.AllergyDao;
import org.calminfotech.setup.daoInterface.UnitDao;
import org.calminfotech.setup.forms.UnitCategoryForm;
import org.calminfotech.setup.forms.UnitForm;
import org.calminfotech.setup.models.HrUnit;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.setup.models.UnitCategoryView;
import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UnitBoImpl implements UnitBo {
	
	@Autowired
	private UnitCategoryBo unitCategoryBo;
	
	@Autowired
	private UnitCategoryViewBo unitCategoryViewBo;
	
	@Autowired
	private VisitWorkflowPointBo wfPointBo;
	
	@Autowired
	private UnitDao unitDao;
	
	@Autowired
	private BillingSchemeBo billingSchemeBo;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<HrUnit> fetchAll() {
		return unitDao.fetchAll();
	}

	@Override
	public HrUnit getUnitById(int Id) {
		// TODO Auto-generated method stub
		return unitDao.getUnitById(Id);
	}

	@Override
	public HrUnit save(UnitForm unitForm) {
		// TODO Auto-generated method stub
		HrUnit hrUnit = new HrUnit();
		HrUnitCategory hrUnitCategory = this.unitCategoryBo.getCategoryById(unitForm.getUnitCategoryId());
		
		hrUnit.setUnitId(hrUnitCategory.getUnitCategoryId());
		hrUnit.setAttendQ(unitForm.isAttendQ());
		BillingScheme billScheme = this.billingSchemeBo.getBillingSchemeById(unitForm.getBillingScheme());
		hrUnit.setBillingSchemeId(billScheme.getId());
		VisitWorkflowPoint visitPoints = this.wfPointBo.getWorkflowPointById(unitForm.getPointId());
		hrUnit.setPoint(visitPoints);
		hrUnit.setCreatedBy(userIdentity.getUserId());
		hrUnit.setOrganisationId(userIdentity.getOrganisation().getId());
		hrUnit.setStatus("active");
		hrUnit.setDeleted(false);
		hrUnit.setCreateDate(new GregorianCalendar().getTime());
		unitDao.save(hrUnit);
		
		return hrUnit;
	
	}

	@Override
	public void update(UnitForm unitForm) {
		// TODO Auto-generated method stub
		HrUnit hrUnit = this.unitDao.getUnitById(unitForm.getUnitId());
		hrUnit.setAttendQ(unitForm.isAttendQ());
		hrUnit.setBillingSchemeId(unitForm.getBillingScheme());
		VisitWorkflowPoint visitPoints = this.wfPointBo.getWorkflowPointById(unitForm.getPointId());
		hrUnit.setPoint(visitPoints);
		hrUnit.setModifiedBy(userIdentity.getUserId());
		hrUnit.setModifiedDate(new Date(System.currentTimeMillis()));
		
		unitDao.update(hrUnit);
	}

	@Override
	public void delete(HrUnit hrUnit) {
		// TODO Auto-generated method stub
		unitDao.delete(hrUnit);
	}

	@Override
	public List<HrUnit> fetchAllByOrganisation(int organisationId) {
		// TODO Auto-generated method stub
		return this.unitDao.fetchAllByOrganisation(organisationId);
	}

	

	

	
	

}
