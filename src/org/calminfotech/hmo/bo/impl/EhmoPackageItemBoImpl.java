package org.calminfotech.hmo.bo.impl;

import java.util.List;

import org.calminfotech.hmo.boInterface.EhmoPackageItemBo;
import org.calminfotech.hmo.daoInterface.EhmoPackageItemDao;
import org.calminfotech.hmo.forms.EhmoPackageItemForm;
import org.calminfotech.hmo.models.EhmoPackageItem;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EhmoPackageItemBoImpl implements EhmoPackageItemBo {

	@Autowired
	private EhmoPackageItemDao ehmoItemDao;

	/*@Autowired
	private UserIdentity userIdentity;*/

	@Override
	public List<EhmoPackageItem> fetchAll() {
		// TODO Auto-generated method stub
		return ehmoItemDao.fetchAll();
	}

	@Override
	public EhmoPackageItem getEhmoItemById(int id) {
		// TODO Auto-generated method stub
		return ehmoItemDao.getEhmoItemById(id);
	}

	@Override
	public void save(EhmoPackageItem ehmoItem) {

		ehmoItemDao.save(ehmoItem);
		
		
	}

	@Override
	public void delete(EhmoPackageItem ehmoItem) {
		// TODO Auto-generated method stub
		ehmoItemDao.delete(ehmoItem);
	}

	@Override
	public void update(EhmoPackageItem ehmoItem) {
		/*EhmoItem category = ehmoItemDao.getEhmoItemById(ehmoItem.getItemId());
		ehmoItem.setName(category.getName());
		ehmoItem.setDescription(category.getDescription());
		ehmoItem.setPackageId(category.getPackageId());*/
		//System.out.println("yemiId/"+ehmoItem.getItemId());
        this.ehmoItemDao.update(ehmoItem);
        
	
	}

}
