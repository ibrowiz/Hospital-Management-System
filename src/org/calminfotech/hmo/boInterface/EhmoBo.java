package org.calminfotech.hmo.boInterface;

import java.util.List;

//import org.calminfotech.admin.forms.DataTableForm;
import org.calminfotech.hmo.forms.EhmoForm;
import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

public interface EhmoBo {

	public List<Ehmo> fetchAll();
	
	public List<Ehmo> fetchAllByOrganisation(int organisationId);
	public List<Ehmo> fetchAllByOrganisationEdit(int organisationId);

	public Ehmo getEhmoById(int id);

	public Ehmo save(EhmoForm ehmoForm);

	public void delete(Ehmo ehmo);

	public void update(EhmoForm ehmoForm);

	@Service
	public class EhmoList extends CustomHibernateDaoSupport {

		public List<Ehmo> fetchAll() {
			List list = getHibernateTemplate().find("from Ehmo");
			return (List<Ehmo>) list;
		}

		public Ehmo getEhmoById(int id) {
			List list = getHibernateTemplate().find("from Ehmo where hmoId = ?", id);
			if (list.size() > 0)
				return (Ehmo) list.get(0);
			return null;
		}

	}

	
	
}
