package org.calminfotech.hmo.bo.impl;
import java.util.List;
import org.calminfotech.hmo.boInterface.EhmoCategoryListBo;
import org.calminfotech.hmo.daoInterface.EhmoCategoryListDao;
import org.calminfotech.hmo.models.EhmoCategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

	@Service
	@Transactional
	public class EhmoCategoryListBoImpl implements EhmoCategoryListBo {

		@Autowired
		private EhmoCategoryListDao ehmoCategoryListDao;

		@Override
		public List <EhmoCategoryList> fetchAll() {
			// TODO Auto-generated method stub
			return ehmoCategoryListDao.fetchAll();
		}

		@Override
		public EhmoCategoryList getEhmoCategoryListById(int id) {
			// TODO Auto-generated method stub
			return ehmoCategoryListDao.getEhmoCategoryListById(id);
		}

		@Override
		public void save(EhmoCategoryList ehmoCategoryList) {

			/*EhmoCategoryList categoryList = new EhmoCategoryList();
			
		//	multilevelCategory1.setCategoryId(multilevelCategoryForm.getCategoryId());
			categoryList.setRowId(ehmoCategoryList.getRowId());
			categoryList.setNames(ehmoCategoryList.getNames());
			this.ehmoCategoryListDao.save(ehmoCategoryList);
			return ehmoCategoryList;
			*/

			
			ehmoCategoryListDao.save(ehmoCategoryList);
		}

		@Override
		public void delete(EhmoCategoryList ehmoCategoryList) {
			// TODO Auto-generated method stub
			ehmoCategoryListDao.delete(ehmoCategoryList);
		}

		@Override
		public void update(EhmoCategoryList ehmoCategoryList) {
			EhmoCategoryList categoryList = ehmoCategoryListDao.getEhmoCategoryListById(ehmoCategoryList.getRowId());
			categoryList.setNames(ehmoCategoryList.getNames());
			
	        this.ehmoCategoryListDao.update(ehmoCategoryList);
		}

	}

