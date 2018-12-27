package org.calminfotech.setup.bo.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.setup.boInterface.UnitCategoryBo;
import org.calminfotech.setup.daoInterface.AllergyCategoryDao;
import org.calminfotech.setup.daoInterface.UnitCategoryDao;
import org.calminfotech.setup.forms.UnitCategoryForm;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UnitCategoryBoImpl implements UnitCategoryBo {
	
	/*@Autowired
	private UnitCategoryBo unitCategoryBo;*/
	
	@Autowired
	private VisitWorkflowPointBo wfPointBo;
	
	@Autowired
	private UnitCategoryDao unitCategoryDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<HrUnitCategory> fetchAll() {
		return unitCategoryDao.fetchAll();
	}

	@Override
	public HrUnitCategory getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		return unitCategoryDao.getCategoryById(categoryId);
	}

	@Override
	public HrUnitCategory save(UnitCategoryForm unitCategoryForm) {
		// TODO Auto-generated method stub
		HrUnitCategory unitCategory = new HrUnitCategory();
		unitCategory.setParentId(unitCategoryForm.getParentId());
		unitCategory.setName(unitCategoryForm.getName());
		unitCategory.setCreatedBy(userIdentity.getUserId());
		unitCategory.setOrganisationId(userIdentity.getOrganisation().getId());
		unitCategory.setStatus("active");
		unitCategory.setDeleted(false);
		unitCategory.setCreateDate(new GregorianCalendar().getTime());
		unitCategoryDao.save(unitCategory);
		return unitCategory;
	
	}

	@Override
	public void update(UnitCategoryForm unitCategoryForm) {
		// TODO Auto-generated method stub
		HrUnitCategory unitCategory = this.unitCategoryDao.getCategoryById(unitCategoryForm.getUnitCategoryId());
		unitCategory.setParentId(unitCategoryForm.getParentId());
		unitCategory.setName(unitCategoryForm.getName());
		unitCategory.setModifiedBy(userIdentity.getUserId());
		unitCategory.setModifiedDate(new Date(System.currentTimeMillis()));
		
		unitCategoryDao.update(unitCategory);
	}

	@Override
	public void delete(HrUnitCategory unitCategory) {
		// TODO Auto-generated method stub
		unitCategoryDao.delete(unitCategory);
	}

	@Override
	public List<HrUnitCategory> fetchAllByOrganisation(int organisationId) {
		// TODO Auto-generated method stub
		return this.unitCategoryDao.fetchAllByOrganisation(organisationId);
	}

}
