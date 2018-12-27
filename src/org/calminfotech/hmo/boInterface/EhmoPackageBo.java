package org.calminfotech.hmo.boInterface;

import java.util.List;
import org.calminfotech.hmo.models.EhmoPackage;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

public interface EhmoPackageBo {

		public List<EhmoPackage> fetchAll();
		
		public EhmoPackage getEhmoPackageById(int id);
		
		public void save(EhmoPackage ehmoPackage);

		public void delete(EhmoPackage ehmoPackage);

		public void update(EhmoPackage ehmoPackage);

	@Service
	public class EhmoPackageList extends CustomHibernateDaoSupport {

		public List<EhmoPackage> fetchAll() {
			List list = getHibernateTemplate().find("from EhmoPackage");
			return (List<EhmoPackage>) list;
		}

		public EhmoPackage getEhmoPackageById(int id) {

			List list = getHibernateTemplate().find(
					"from EhmoPackage where packageId = ?", id);
			if (list.size() > 0)
				return (EhmoPackage) list.get(0);
			return null;
		}
	}

	

}
