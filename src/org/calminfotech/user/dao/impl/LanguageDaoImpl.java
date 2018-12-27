package org.calminfotech.user.dao.impl;

import java.util.List;

import org.calminfotech.user.daoInterface.LanguageDao;
import org.calminfotech.user.models.Language;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LanguageDaoImpl implements LanguageDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Language> fetchAll() {

		List<Language> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Language").list();
		return list;

	}

	@Override
	public Language getLanguageById(int id) {
		List<Language> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Language where id = ?").setParameter(0, id)
				.list();

		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;

	}

	@Override
	public void save(Language language) {

		this.sessionFactory.getCurrentSession().save(language);

	}

	@Override
	public void delete(Language language) {

		this.sessionFactory.getCurrentSession().delete(language);
	}

	@Override
	public void update(Language language) {

		this.sessionFactory.getCurrentSession().update(language);
	}

	@Override
	public List<Language> fetchAllByOrganisation(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Language.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

}
