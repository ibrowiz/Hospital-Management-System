package org.calminfotech.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.calminfotech.system.daoInterface.BGlobalItemDao;
import org.calminfotech.system.models.BGlobalItem;
import org.calminfotech.system.models.BGlobalCategory;
import org.calminfotech.system.models.OuterRecursive;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BGlobalItemDaoImpl implements BGlobalItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BGlobalItem> fetchAll() {
		// TODO Auto-generated method stub
		List<BGlobalItem> list = sessionFactory.getCurrentSession()
				.createQuery("from BGlobalItem").list();
		return list;
	}

	@Override
	public BGlobalItem getCategoryItemById(int itemId) {
		// TODO Auto-generated method stub

		List<BGlobalItem> list = sessionFactory.getCurrentSession()
				.createQuery("from BGlobalItem where itemId = ? ")
				.setParameter(0, itemId).list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BGlobalItem category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(category);
	}

	@Override
	public void update(BGlobalItem category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(BGlobalItem category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().delete(category);
	}

	@Override
	public List<OuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub

		try {

			/*
			 * Query query = sessionFactory.getCurrentSession().createSQLQuery(
			 * "exec dbo.outerrecursivenew") .addEntity(OuterRecursive.class);
			 */

			System.out.print("I don enter ooooooo: ");
			System.out.print("I don enter ooooooo: ");
			System.out.print("I don enter ooooooo: ");
			System.out.print("I don enter ooooooo: ");
			System.out.print("I don enter ooooooo: ");
			String sql = "exec dbo.outerrecursiveproc";
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
					sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();

			for (Object object : data) {
				Map row = (Map) object;
				System.out.print("Row Id: " + row.get("rowid"));
				System.out.println(", Names: " + row.get("names"));
			}
			// tx.commit();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<BGlobalItem> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<BGlobalItem> list = sessionFactory.getCurrentSession()
				.createQuery("from BGlobalItem where organisationId = ? ").setParameter(0, organisationId).list();
		return list;
	}

}
