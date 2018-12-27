package org.calminfotech.hmo.bo.impl;

import java.util.List;
import org.calminfotech.hmo.boInterface.ItemServiceGroupBo;
import org.calminfotech.hmo.daoInterface.ItemServiceGroupDao;
import org.calminfotech.hmo.models.ItemServiceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

	@Service
	@Transactional
	public class ItemServiceGroupBoImpl implements ItemServiceGroupBo {

		@Autowired
		private ItemServiceGroupDao itemServiceGroupDao;

		@Override
		public List <ItemServiceGroup> fetchAll() {
			// TODO Auto-generated method stub
			return itemServiceGroupDao.fetchAll();
		}

		@Override
		public ItemServiceGroup getItemServiceGroupById(int id) {
			// TODO Auto-generated method stub
			return itemServiceGroupDao.getItemServiceGroupById(id);
		}

		@Override
		public void save(ItemServiceGroup itemServiceGroup) {

			/*EhmoCategoryList categoryList = new EhmoCategoryList();
			
		//	multilevelCategory1.setCategoryId(multilevelCategoryForm.getCategoryId());
			categoryList.setRowId(ehmoCategoryList.getRowId());
			categoryList.setNames(ehmoCategoryList.getNames());
			this.ehmoCategoryListDao.save(ehmoCategoryList);
			return ehmoCategoryList;
			*/

			
			itemServiceGroupDao.save(itemServiceGroup);
		}

		@Override
		public void delete(ItemServiceGroup itemServiceGroup) {
			// TODO Auto-generated method stub
			itemServiceGroupDao.delete(itemServiceGroup);
		}

		@Override
		public void update(ItemServiceGroup itemServiceGroup) {
			ItemServiceGroup itemService = itemServiceGroupDao.getItemServiceGroupById(itemServiceGroup.getItemServiceId());
			itemService.setName(itemServiceGroup.getName());
			
	        this.itemServiceGroupDao.update(itemServiceGroup);
		}
	}
