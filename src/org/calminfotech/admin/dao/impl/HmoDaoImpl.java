package org.calminfotech.admin.dao.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.calminfotech.admin.daoInterface.HmoDao;
import org.calminfotech.admin.forms.DataTableForm;
import org.calminfotech.system.models.Hmo;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.view.helpers.Button;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoDaoImpl implements HmoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Hmo> fetchAll() {
		System.out.println("This is HMO DAO for fetch All method");
		List<Hmo> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Hmo ORDER BY id DESC").list();

		return list;
	}

	@Override
	public Hmo getHmoById(int id) {
		// TODO Auto-generated method stub
		List<Hmo> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Hmo where id = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(Hmo hmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(hmo);
	}

	@Override
	public void delete(Hmo hmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(hmo);
	}

	@Override
	public void update(Hmo hmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(hmo);
	}

	@Override
	public String dataTableQuery(DataTableForm dataTableForm) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Hmo.class);

		Integer totalRecord = criteria.list().size();

		criteria.createAlias("state", "state").createAlias("lga", "lga");

		criteria.add(Restrictions
				.disjunction()
				.add(Restrictions.ilike("name", dataTableForm.getsSearch()
						+ "%"))
				.add(Restrictions.ilike("phoneNumber",
						dataTableForm.getsSearch() + "%"))
				.add(Restrictions.ilike("state.stateName",
						dataTableForm.getsSearch() + "%"))
				.add(Restrictions.ilike("lga.localGovernmentAreasName",
						dataTableForm.getsSearch() + "%")));

		criteria.setFirstResult(dataTableForm.getiDisplayStart());
		criteria.setMaxResults(dataTableForm.getiDisplayLength());

		List<Hmo> list = criteria.list();
		JSONArray aaData = new JSONArray();

		for (Hmo hmo : list) {
			JSONArray row = new JSONArray();
			row.add(hmo.getName());
			row.add(hmo.getState().getStateName());
			row.add(hmo.getLga().getLocalGovernmentAreasName());
			row.add(hmo.getPhoneNumber());
			row.add(DateUtils.formatDateToString(hmo.getCreatedDate(),
					"dd-MM-yyyy"));
			row.add(Button.renderLink(Button.PRIMARY, Button.SIZE_XS,
					"admin/insurances/hmos/view/" + hmo.getId(),
					Button.FONTAWESOME, "fa-eye", "view HMO"));

			aaData.add(row);
		}

		JSONObject obj = new JSONObject();
		obj.accumulate("sEcho", dataTableForm.getsEcho());
		obj.accumulate("iTotalRecords", totalRecord);
		obj.accumulate("iTotalDisplayRecords", list.size());
		obj.accumulate("aaData", aaData);

		return obj.toString();
	}

}
