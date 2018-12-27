package org.calminfotech.setup.bo.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.setup.boInterface.AllergyBo;
import org.calminfotech.setup.daoInterface.AllergyCategoryDao;
import org.calminfotech.setup.daoInterface.AllergyCategoryDao;
import org.calminfotech.setup.daoInterface.AllergyDao;
import org.calminfotech.setup.forms.AllergyForm;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllergyBoImpl implements AllergyBo {

	@Autowired
	private AllergyDao allergyDao;

	@Autowired
	private AllergyCategoryDao allergyCategoryDao;
	
	@Autowired
	private AllergyCategoryDao allergyCategoryDao1;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Allergy> fetchAll() {
		// TODO Auto-generated method stub
		return allergyDao.fetchAll();
	}

	@Override
	public Allergy getAllergyById(int id) {
		// TODO Auto-generated method stub
		return allergyDao.getAllergyById(id);
	}

	@Override
	public Allergy save(AllergyForm allergyForm) {
		// TODO Auto-generated method stub
		Allergy allergy = new Allergy();
		//allergy.setCategoryId(allergyForm.getCategoryId());
		AllergyCategory category = this.allergyCategoryDao1.getCategoryById(allergyForm.getCategoryId());
		allergy.setCategory(category);
		allergy.setName(allergyForm.getName());
		allergy.setDescription(allergyForm.getDescription());
		allergy.setCreatedBy(userIdentity.getUsername());
		allergy.setCreateDate(new Date(System.currentTimeMillis()));
		allergy.setOrganisationId(userIdentity.getOrganisation().getId());
		allergy.setStatus("active");
		
		allergyDao.save(allergy);
		return allergy;
	}

	@Override
	public void delete(Allergy allergy) {
		// TODO Auto-generated method stub
		allergyDao.delete(allergy);
	}

	@Override
	public void update(AllergyForm allergyForm) {
		// TODO Auto-generated method stub
		Allergy allergy = allergyDao.getAllergyById(allergyForm.getId());

		//AllergyCategory1 category = this.allergyCategoryDao1.getCategoryById(allergyForm.getCategoryId());
		
		AllergyCategory category = this.allergyCategoryDao1.getCategoryById(allergyForm.getCategoryId());
		allergy.setCategory(category);
		allergy.setName(allergyForm.getName());
		allergy.setDescription(allergyForm.getDescription());
		allergy.setModifiedBy(userIdentity.getUsername());
		allergy.setModifyDate(new Date(System.currentTimeMillis()));
		//allergy.setOrganisationId(userIdentity.getOrganisation().getId());
		allergyDao.update(allergy);
	}

	/*@Override
	public List<Allergy> fetchAllByOrganisation() {
		return this.allergyDao.fetchAllByOrganisation(this.userIdentity
				.getOrganisation());
	}*/
	
	@Override
	public List<Allergy> fetchAllByOrganisation(int organisationId) {
		return this.allergyDao.fetchAllByOrganisation(organisationId);
	}

	@Override
	public List<Allergy> fetchAllergyById(Integer id) {
		// TODO Auto-generated method stub
		return this.allergyDao.fetchAllergyById(id);
	}

}
