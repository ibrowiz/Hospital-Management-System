package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.admin.daoInterface.DrugDao;
import org.calminfotech.admin.daoInterface.OrganisationDao;
import org.calminfotech.system.boInterface.OrganisationDrugBo;
import org.calminfotech.system.dao.impl.OrganisationDrugDaoImpl;
import org.calminfotech.system.daoInterface.OrganisationDrugDao;
import org.calminfotech.system.forms.OrganisationDrugForm;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.OrganisationDrug;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganisationDrugBoImpl implements OrganisationDrugBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private OrganisationDrugDao organisationDrugDao;

	@Autowired
	private DrugDao drugDao;

	@Autowired
	private OrganisationDao organisationDao;

	@Override
	public List<OrganisationDrug> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.organisationDrugDao
				.fetchAllByOrganisation(this.userIdentity.getOrganisation());
	}

	@Override
	public List<OrganisationDrug> fetchAllByOrganisation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.organisationDrugDao.fetchAllByOrganisation(organisation);
	}

	@Override
	public List<OrganisationDrug> fetchAllByDrug(Drug drug) {
		// TODO Auto-generated method stub
		return this.organisationDrugDao.fetchAllByDrug(drug);
	}

	@Override
	public OrganisationDrug save(OrganisationDrugForm organisationDrugForm) {
		// TODO Auto-generated method stub
		OrganisationDrug organisationDrug = new OrganisationDrug();
		Organisation organisation = this.organisationDao
				.getOrganisationById(this.userIdentity.getOrganisation()
						.getId());

		Drug drug = this.drugDao.getDrugById(organisationDrugForm.getDrugId());

		organisationDrug.setDrug(drug);
		organisationDrug.setOrganisation(organisation);
		organisationDrug.setPrice(organisationDrugForm.getPrice());
		organisationDrug.setCreatedBy(this.userIdentity.getUsername());

		drug.getDrugOrganisations().add(organisationDrug);
		organisation.getOrganisationDrugs().add(organisationDrug);

		this.drugDao.update(drug);
		this.organisationDrugDao.save(organisationDrug);

		return organisationDrug;
	}

	@Override
	public void delete(OrganisationDrug organisationDrug) {
		// TODO Auto-generated method stub
		this.organisationDrugDao.delete(organisationDrug);
	}

	@Override
	public void update(OrganisationDrugForm organisationDrugForm) {
		// TODO Auto-generated method stub
		Drug drug = this.drugDao.getDrugById(organisationDrugForm.getDrugId());
		OrganisationDrug organisationDrug = this.getOrganisationDrugByDrugAndOrganisation(drug);
		
		organisationDrug.setPrice(organisationDrugForm.getPrice());
		this.organisationDrugDao.update(organisationDrug);
	}

	public OrganisationDrug getOrganisationDrugByDrugAndOrganisation(Drug drug) {

		Organisation organisation = this.userIdentity.getOrganisation();
		OrganisationDrug oDrug = this.organisationDrugDao
				.getOrganisationDrugByDrugAndOrganisation(drug, organisation);

		return oDrug;
	}

}
