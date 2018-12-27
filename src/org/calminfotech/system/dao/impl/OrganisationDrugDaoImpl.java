package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.OrganisationDrugDao;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.OrganisationDrug;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrganisationDrugDaoImpl implements OrganisationDrugDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<OrganisationDrug> fetchAllByOrganisation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(OrganisationDrug.class)
				.createAlias("pk.organisation", "organisation")
				//
				.add(Restrictions.eq("pk.organisation.id", organisation.getId()));
		List<OrganisationDrug> list = criteria.list();
		return list;
	}

	@Override
	public List<OrganisationDrug> fetchAllByDrug(Drug drug) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(OrganisationDrug.class)
				.createAlias("pk.drug", "drug")
				.add(Restrictions.eq("pk.drug.id", drug.getId()));

		List<OrganisationDrug> list = criteria.list();
		return list;
	}

	@Override
	public void save(OrganisationDrug organisationDrug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(organisationDrug);
	}

	@Override
	public void delete(OrganisationDrug organisationDrug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(organisationDrug);

	}

	@Override
	public void update(OrganisationDrug organisationDrug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(organisationDrug);
	}

	public OrganisationDrug getOrganisationDrugByDrugAndOrganisation(Drug drug,
			Organisation organisation) {

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(OrganisationDrug.class)
				.createAlias("pk.drug", "drug")
				//Where conditions
				.add(Restrictions.eq("pk.drug.id", drug.getId()))
				.createAlias("pk.organisation", "organisation")
				.add(Restrictions.eq("pk.organisation.id", organisation.getId()));

		List<OrganisationDrug> list = criteria.list();
		System.out.println(list.size());
		if(list.size() > 0)
			return list.get(0);
		
		return null;
	}

}
